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
import org.statsenko.entity.Bank;
import org.statsenko.entity.Branch;
import org.statsenko.entity.Client;
import org.statsenko.mapper.BranchMapper;
import org.statsenko.service.services.BranchService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {BranchControllerImpl.class, BranchService.class})
@WebMvcTest
class BranchServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    BranchMapper REST_MAPPER = Mappers.getMapper(BranchMapper.class);

    @MockBean
    BranchService branchService;

    Bank bank1 = new Bank(1,"SBER",null,null, null,null);
    Bank bank2 = new Bank(2,"ALFA",null,null, null,null);
    Branch branch1 = new Branch(1,"SBER FILIAL","MOSCOW",null,null,
            bank1,null);
    Branch branch2 = new Branch(2,"ALFA FILIAL","SPB",null,null,bank2,
            null);

    Client client = new Client(1,"Vlad","Ivanov","Ivan", null,"33333",
            null,null,null,null,List.of(branch1,branch2));


    List<Branch> branch = new ArrayList<>(Arrays.asList(branch1,branch2));

    @Test
    void getAllBranch() throws Exception {
        Mockito.when(branchService.allBranch()).thenReturn(REST_MAPPER.toDtoList(branch));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].branchName", is("SBER FILIAL")));
    }

    @Test
    void getBranchById() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(branch1);
        Mockito.when(branchService.getBranchById(branch1.getBranchId())).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.branchName", is("SBER FILIAL")))
                .andExpect(jsonPath("$.response.main.bankName",is("SBER")));
    }

    @Test
    void createBranch() throws Exception{
        Branch branch3 = new Branch(3,"SBER TMB","TAMBOV",null,null,null,null);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(REST_MAPPER.toDto(branch3));
        Mockito.when(branchService.createBranch(REST_MAPPER.toDto(branch3))).thenReturn(messageResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/branch")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(branch3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.branchName", is("SBER TMB")));
    }

    @Test
    void editBranch() throws Exception{
        Branch branch = new Branch(1,"SBERBANK FILIAL","TAMBOV",null,null,null,null);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(REST_MAPPER.toDto(branch));
        Mockito.when(branchService.editBranch(REST_MAPPER.toDto(branch), branch.getBranchId())).thenReturn(messageResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/branch/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(branch));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.branchName", is("SBERBANK FILIAL")));
    }

    @Test
    void deleteBranch() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse("Branch deleted");
        Mockito.when(branchService.deleteBranch(branch1.getBranchId())).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/branch/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getBranchOnClient() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(List.of(branch1,branch2));
        Mockito.when(branchService.getBranchOnClient(client.getClientId())).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response[0].branchName", is("SBER FILIAL")))
                .andExpect(jsonPath("$.response[1].branchName", is("ALFA FILIAL")));
    }

    @Test
    void getAllBranchOfBank() throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(List.of(branch1));
        Mockito.when(branchService.getAllBranchOfBank(1)).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch/bank/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response[0].branchName", is("SBER FILIAL")));
    }
}