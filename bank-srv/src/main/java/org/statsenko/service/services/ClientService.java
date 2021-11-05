package org.statsenko.service.services;

import dto.request.ClientDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Client;
import org.statsenko.mapper.ClientMapper;
import org.statsenko.repository.ClientRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private static final ClientMapper REST_MAPPER = Mappers.getMapper(ClientMapper.class);

    @Autowired
    private final ClientRepository clientRepository;

    public List<ClientDto> getAllClients(){
        List<ClientDto> clientDtoList = REST_MAPPER.toDtoList(clientRepository.findAll());
        return clientDtoList;
    }

    public ClientDto getClientById(int id){
        ClientDto client = REST_MAPPER.toDto(clientRepository.getById(id));
        return client;
    }

    public ClientDto createClient(ClientDto clientDto){
        Client client = REST_MAPPER.toEntity(clientDto);

        clientRepository.save(client);

        return clientDto;
    }

    public ClientDto editClient(ClientDto clientDto, int id){
        Client client = REST_MAPPER.toEntity(clientDto);
        client.setClientId(id);

        clientRepository.save(client);
        return clientDto;
    }

    public void deleteClient(int id){
        clientRepository.deleteById(id);
    }
}
