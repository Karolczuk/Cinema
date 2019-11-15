package com.app.cinema.service;

import com.app.cinema.dto.MovieDto;
import com.app.cinema.dto.TemplateDto;
import com.app.cinema.exceptions.AppException;
import com.app.cinema.model.Template;
import com.app.cinema.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateEngine templateEngine;


    public byte[] generateTicket(Long reservationId) {
        Template ticket = templateRepository.findByName("TICKET");
        Context context = new Context();
        String process = templateEngine.process(ticket.getBody(), context);
        return process.getBytes();
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
            throw new AppException("add template exception - template object is null");
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
