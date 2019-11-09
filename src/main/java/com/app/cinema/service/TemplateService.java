package com.app.cinema.service;

import com.app.cinema.model.Template;
import com.app.cinema.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateService {

    private final TemplateRepository templateRepository;

    //update, save -add, delete, getById, getPage - to findALll

    public Byte[] generateTicket(Long reservationId) {
        Template ticket = templateRepository.findByName("TICKET");
        return null;
    }
}
