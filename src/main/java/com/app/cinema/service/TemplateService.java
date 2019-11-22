package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.TemplateDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Movie;
import com.app.cinema.model.Seat;
import com.app.cinema.model.Template;
import com.app.cinema.model.User;
import com.app.cinema.repository.SeatRepository;
import com.app.cinema.repository.TemplateRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.swing.text.html.StyleSheet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateEngine templateEngine;
    private final SeatRepository seatRepository;


    public byte[] generateTicket(Long reservationId) {
        Template ticket = templateRepository.findByName("TICKET");
        List<Seat> byRepertoireIdAndUserName = seatRepository.findByRepertoireIdAndUserUsername(reservationId, SecurityContextHolder.getContext().getAuthentication().getName());
        if (byRepertoireIdAndUserName.isEmpty()) {
            throw new AppException("Seat or User doesn't exist");
        }
        User user = byRepertoireIdAndUserName.get(0).getUser();
        Movie movie = byRepertoireIdAndUserName.get(0).getRepertoire().getMovie();
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("movie", movie);
        context.setVariable("seats",byRepertoireIdAndUserName);
        String process = templateEngine.process(ticket.getBody(), context); // generuje html
        Document document = new Document(); // do generowai pdf
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
//            XMLWorkerHelper.getInstance().parseXHtml()
            htmlWorker.parse(new StringReader(process));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public Page<TemplateDto> findAll(Pageable pageable) {
        Page<Template> templatePage = templateRepository.findAll(pageable);
        List<TemplateDto> templateDtoList = templatePage.getContent()
                .stream()
                .map(ModelMapper::fromTemplateToTemplateDto)
                .collect(Collectors.toList());
        return new PageImpl<>(templateDtoList, templatePage.getPageable(), templatePage.getTotalElements());
    }

    public TemplateDto findOne(Long id) {

        if (id == null) {
            throw new AppException("find one exception - id is null");
        }

        var template = templateRepository
                .findById(id)
                .orElseThrow(() -> new AppException("no template with id " + id));

        return ModelMapper.fromTemplateToTemplateDto(template);
    }

    public TemplateDto add(TemplateDto templateDto) {

        if (templateDto == null) {
            throw new AppException("add template exception - template object is null"
            );
        }
        var template = ModelMapper.fromTemplateDtoToTemplate(templateDto);
        return ModelMapper.fromTemplateToTemplateDto(templateRepository.save(template));
    }

    public TemplateDto update(Long id, TemplateDto templateDto) {

        if (id == null) {
            throw new AppException("update template exception - id is null");
        }
        if (templateDto == null) {
            throw new AppException("update template exception - template object is null");
        }

        var template = templateRepository
                .findById(id)
                .orElseThrow(() -> new AppException("update template exception - no template with id " + id));
        template.setBody(templateDto.getBody());
        template.setName(templateDto.getName());


        return ModelMapper.fromTemplateToTemplateDto(templateRepository.save(template));

    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new AppException("delete exception - id is null");
        }
        templateRepository.deleteById(id);
    }

}
