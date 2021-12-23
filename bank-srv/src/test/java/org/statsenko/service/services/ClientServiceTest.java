package org.statsenko.service.services;

import dto.request.ClientDto;
import dto.request.ClientFilterDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.statsenko.entity.Branch;
import org.statsenko.entity.Client;
import org.statsenko.entity.Profile;
import org.statsenko.filter.EntityFilter;
import org.statsenko.mapper.ClientMapper;
import org.statsenko.mapper.filter.ClientFilterMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientServiceTest {

    private static final ClientMapper REST_MAPPER = Mappers.getMapper(ClientMapper.class);
    private static final ClientFilterMapper FILTER_MAPPER = Mappers.getMapper(ClientFilterMapper.class);


    Branch branch1 = new Branch(1,"SBER FILIAL","MOSCOW",null,null,
            null,null);
    Profile profile = new Profile(1,null,"qwerty","qwerty","vlad","ivanov",
            null,null,null);
    Client client1 = new Client(1,"Vlad","Ivanov","Ivan", null,"33333",
            profile,null,null,null, List.of(branch1));
    Client client2 = new Client(2,"alex","alex","Ivan", null,"333333",
            null,null,null,null, List.of(branch1));
    Client client3 = new Client(3,"Srgey","Ivanov","Ivann", null,"334333",
            profile,null,null,null, List.of(branch1));

    @Test
    void getAllClients() {
        List<ClientDto> clientDtoList = REST_MAPPER.toDtoList(List.of(client1,client2));

        assertEquals("Ivanov", clientDtoList.get(0).getLastName());
        assertEquals("333333",clientDtoList.get(1).getTin());
    }

    @Test
    void getClientOnBranch() {
        List<ClientDto> clientDtoList = REST_MAPPER.toDtoList(List.of(client1));

        assertEquals("Ivanov", clientDtoList.get(0).getLastName());
    }

    @Test
    void getClientById() {
        ClientDto clientDto = REST_MAPPER.toDto(client1);

        assertEquals("Ivanov", clientDto.getLastName());
    }

    @Test
    void createClient() {
        ClientDto clientDto = new ClientDto("Alexey","Petrov","midName",null,"3333333",null);
        Client client = REST_MAPPER.toEntity(clientDto);

        assertEquals(1,client.getProfile().getProfileId());
        assertEquals("Petrov",client.getLastName());

    }

    @Test
    void editClient() {
        ClientDto clientDto = new ClientDto("Alexey","Petrov","midName",null,"222222",null);
        client1 = REST_MAPPER.toEntity(clientDto);

        assertEquals(1,client1.getProfile().getProfileId());
        assertEquals("Petrov",client1.getLastName());
    }

    @Test
    void getFilteredList(){
        ClientFilterDto filterDto = new ClientFilterDto();
        filterDto.setLastName("Ivanov");

        final EntityFilter filter = FILTER_MAPPER.toFilter(filterDto);
        List<ClientDto> clientDtoList = REST_MAPPER.toDtoList(List.of(client1,client3));

        assertEquals(clientDtoList.get(0).getLastName(),filter.getLastName());
        assertEquals(clientDtoList.get(1).getLastName(),filter.getLastName());
    }
}