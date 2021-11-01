package org.statsenko.service.rest;

import controllers.ClientController;
import dto.request.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.ClientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClientServiceImpl implements ClientController {

    private final ClientService clientService;

    @Override
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @Override
    public ResponseEntity<ClientDto> getClient(int id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @Override
    public ResponseEntity<ClientDto> createClient(ClientDto clientDto) {
        return ResponseEntity.ok(clientService.createClient(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> editClient(ClientDto clientDto, int id) {
        return ResponseEntity.ok(clientService.editClient(clientDto, id));
    }

    @Override
    public ResponseEntity<HttpStatus> deleteClient(int id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
