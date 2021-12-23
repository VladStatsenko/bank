package org.statsenko.rest;

import controllers.ClientController;
import dto.request.ClientDto;
import dto.request.ClientFilterDto;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.ClientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;
    @Override
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @Override
    public ResponseEntity<MessageResponse<ClientDto>> getClient(int id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @Override
    public ResponseEntity<ClientDto> createClient(ClientDto clientDto) {
        return ResponseEntity.ok(clientService.createClient(clientDto));
    }

    @Override
    public ResponseEntity<MessageResponse<ClientDto>> editClient(ClientDto clientDto, int id) {
        return ResponseEntity.ok(clientService.editClient(clientDto, id));
    }

    @Override
    public ResponseEntity<MessageResponse> deleteClient(int id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }

    @Override
    public ResponseEntity getClientOnBranch(int id) {
        return ResponseEntity.ok(clientService.getClientOnBranch(id));
    }

    @Override
    public ResponseEntity<List<ClientDto>> getFilteredList(ClientFilterDto filterDto) {
        return ResponseEntity.ok(clientService.getFilteredList(filterDto));
    }
}
