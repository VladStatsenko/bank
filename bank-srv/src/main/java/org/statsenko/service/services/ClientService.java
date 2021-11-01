package org.statsenko.service.services;

import dto.request.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Client;
import org.statsenko.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public List<ClientDto> getAllClients(){
        List<ClientDto> clientDtoList = clientRepository.findAll()
                .stream().map(ClientDto::new).collect(Collectors.toList());
        return clientDtoList;
    }

    public ClientDto getClientById(int id){
        ClientDto client = clientRepository.findById(id)
                .map(ClientDto::new).get();
        return client;
    }

    public ClientDto createClient(ClientDto clientDto){
        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setMidName(clientDto.getMidName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setTIN(clientDto.getTin());
        client.getProfile().setClient(client);
        client.getProfile().setLogin(clientDto.getLogin());
        client.getProfile().setPassword(clientDto.getPassword());

        clientRepository.save(client);

        return clientDto;
    }

    public ClientDto editClient(ClientDto clientDto, int id){
        Client client = clientRepository.findById(id).orElse(null);
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setMidName(clientDto.getMidName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setTIN(clientDto.getTin());

        clientRepository.save(client);
        return clientDto;
    }

    public void deleteClient(int id){
        clientRepository.deleteById(id);
    }
}
