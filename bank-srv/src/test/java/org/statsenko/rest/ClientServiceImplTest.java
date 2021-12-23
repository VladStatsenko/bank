package org.statsenko.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.response.MessageResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.statsenko.entity.Branch;
import org.statsenko.entity.Client;
import org.statsenko.entity.Profile;
import org.statsenko.mapper.ClientMapper;
import org.statsenko.mapper.filter.ClientFilterMapper;
import org.statsenko.service.services.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ClientControllerImpl.class, ClientService.class})
@WebMvcTest
class ClientServiceImplTest {

    private static final ClientFilterMapper FILTER_MAPPER = Mappers.getMapper(ClientFilterMapper.class);


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ClientMapper REST_MAPPER = Mappers.getMapper(ClientMapper .class);

    @MockBean
    ClientService clientService;

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

    List<Client> clients = new ArrayList<>(List.of(client1,client2));

    @Test
    void getAllClients() throws Exception{
        Mockito.when(clientService.getAllClients()).thenReturn(REST_MAPPER.toDtoList(clients));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Vlad")));
    }

    @Test
    void getClient() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(client1);
        Mockito.when(clientService.getClientById(1)).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.firstName", is("Vlad")))
                .andExpect(jsonPath("$.response.profile.login",is("qwerty")));
    }

    @Test
    void createClient() throws Exception{
        Client client3 = new Client(3,"petr","pterov","petrovich", null,"1212",null,null,null,null, null);

        Mockito.when(clientService.createClient(REST_MAPPER.toDto(client3))).thenReturn(REST_MAPPER.toDto(client3));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(client3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstName", is("petr")));
    }

    @Test
    void editClient() throws Exception{
        Client client3 = new Client(1,"petr","pterov","petrovich", null,"1212",null,null,null,null, null);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(client3);
        Mockito.when(clientService.editClient(REST_MAPPER.toDto(client3),1)).thenReturn(messageResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/client/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(client3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.firstName", is("petr")));
    }

    @Test
    void deleteClient() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse("Client deleted");
        Mockito.when(clientService.deleteClient(1)).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getClientOnBranch() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(List.of(client1,client2));
        Mockito.when(clientService.getClientOnBranch(1)).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/branch/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response[0].firstName", is("Vlad")))
                .andExpect(jsonPath("$.response[1].firstName", is("alex")));
    }
}