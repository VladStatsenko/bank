package org.statsenko.service.rest;

import controllers.NotificationController;
import dto.request.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.NotificationService;

@RequiredArgsConstructor
@RestController
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService service;


    @Override
    public void send(NotificationDto dto) {
        service.send(dto);
    }
}
