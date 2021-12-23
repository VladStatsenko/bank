package org.statsenko.service.services;

import dto.request.ClientDto;
import dto.request.ClientFilterDto;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Client;
import org.statsenko.filter.EntityFilter;
import org.statsenko.mapper.ClientMapper;
import org.statsenko.mapper.filter.ClientFilterMapper;
import org.statsenko.repository.ClientRepository;
import org.statsenko.repository.filter.EntitySpecification;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private static final ClientMapper REST_MAPPER = Mappers.getMapper(ClientMapper.class);
    private static final ClientFilterMapper FILTER_MAPPER = Mappers.getMapper(ClientFilterMapper.class);

    @Autowired
    private final ClientRepository clientRepository;

    @Loggable
    public List<ClientDto> getAllClients(){
        List<ClientDto> clientDtoList = REST_MAPPER.toDtoList(clientRepository.findAll());
        return clientDtoList;
    }

    @Loggable
    public MessageResponse<List<ClientDto>> getClientOnBranch(int id){
        MessageResponse message = new MessageResponse();
        if (clientRepository.findClientByBranch(id).isEmpty()){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Branch with your ID not found");
        }
        else {
            List<ClientDto> clientDtoList = REST_MAPPER.toDtoList(clientRepository.findClientByBranch(id));
            message.setResponse(clientDtoList);
        }
        return message;
    }

    @Loggable
    public MessageResponse<ClientDto> getClientById(int id){
        MessageResponse message = new MessageResponse();
        if (clientRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Client with your ID not found");
        }
        else {
            ClientDto client = REST_MAPPER.toDto(clientRepository.getById(id));
            message.setResponse(client);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public ClientDto createClient(ClientDto clientDto){
        Client client = REST_MAPPER.toEntity(clientDto);

        clientRepository.save(client);

        return clientDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse<ClientDto> editClient(ClientDto clientDto, int id){
        MessageResponse message = new MessageResponse();
        if (clientRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Client with your ID not found");
        }
        else {
            Client client = REST_MAPPER.update(clientDto, clientRepository.getById(id));
            clientRepository.save(client);
            message.setResponse(client);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse deleteClient(int id){
        MessageResponse message = new MessageResponse();
        if (clientRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Client with your ID not found");
        }
        else {
            clientRepository.deleteById(id);
            message.setResponse("Client deleted");
        }
        return message;
    }

    @Loggable
    public List<ClientDto> getFilteredList(ClientFilterDto filterDto){

        final EntityFilter filter = FILTER_MAPPER.toFilter(filterDto);
        List<ClientDto> clientDtoList = REST_MAPPER.toDtoList(clientRepository.findAll(new EntitySpecification(filter)));
        return clientDtoList;
    }
}
