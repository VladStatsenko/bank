package controllers;

import dto.request.ClientDto;
import dto.request.ClientFilterDto;
import dto.response.MessageResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@Api("client controller")
public interface ClientController {

    @GetMapping
    ResponseEntity<List<ClientDto>> getAllClients();

    @GetMapping("/{id}")
    ResponseEntity<MessageResponse<ClientDto>> getClient(@PathVariable int id);

    @PostMapping
    ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto);

    @PutMapping("/{id}")
    ResponseEntity<MessageResponse<ClientDto>> editClient(@RequestBody ClientDto clientDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity<MessageResponse> deleteClient(@PathVariable int id);

    @GetMapping("/branch/{id}")
    ResponseEntity getClientOnBranch(@PathVariable int id);

    @PostMapping("/filter")
    ResponseEntity<List<ClientDto>> getFilteredList(@RequestBody ClientFilterDto filterDto);
}
