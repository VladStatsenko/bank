package controllers;

import dto.request.ClientDto;
import dto.request.ClientFilterDto;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@Api("client controller")
public interface ClientController {

    @GetMapping
    ResponseEntity<List<ClientDto>> getAllClients();

    @GetMapping("/{id}")
    ResponseEntity<ClientDto> getClient(@PathVariable int id);

    @PostMapping
    ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto);

    @PutMapping("/{id}")
    ResponseEntity<ClientDto> editClient(@RequestBody ClientDto clientDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteClient(@PathVariable int id);

    @GetMapping("/branch/{id}")
    ResponseEntity getClientOnBranch(@PathVariable int id);

    @PostMapping("/filter")
    ResponseEntity<List<ClientDto>> getFilteredList(@RequestBody ClientFilterDto filterDto);
}
