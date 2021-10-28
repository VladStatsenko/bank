package controllers;

import dto.request.BranchRequest;
import dto.request.ClientRequest;
import dto.response.InfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ClientController {

    @GetMapping(name = "client")
    ResponseEntity<InfoDto> getAllClients();

    @GetMapping(name = "/client/{id}")
    ResponseEntity<InfoDto> getClientById(@PathVariable int id);

    @PostMapping(name = "/client")
    ResponseEntity<InfoDto> createClient(@RequestBody ClientRequest clientRequest);

    @PutMapping(name = "/client/{id}")
    ResponseEntity<InfoDto> editClient(@RequestBody ClientRequest clientRequest, @PathVariable int id);

    @DeleteMapping(name = "/client/{id}")
    ResponseEntity<InfoDto> deleteClient(@PathVariable int id);
}
