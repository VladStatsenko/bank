package org.statsenko.service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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
import org.statsenko.repository.BranchRepository;
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

    @MockBean
    BranchRepository branchRepository;

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
        Mockito.when(branchRepository.findAll()).thenReturn(branch);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].branchName", is("SBER FILIAL")));
    }

    @Test
    void getBranchById() throws Exception{
        Mockito.when(branchRepository.getById(branch1.getBranchId())).thenReturn((branch1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.branchName", is("SBER FILIAL")))
                .andExpect(jsonPath("$.main",is("SBER")));
    }

    @Test
    void createBranch() throws Exception{
        Branch branch3 = new Branch(3,"SBER TMB","TAMBOV",null,null,null,null);

        Mockito.when(branchRepository.save(branch3)).thenReturn(branch3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/branch")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(branch3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.branchName", is("SBER TMB")));
    }

    @Test
    void editBranch() throws Exception{
        Branch branch = new Branch(1,"SBERBANK FILIAL","TAMBOV",null,null,null,null);

        Mockito.when(branchRepository.save(branch)).thenReturn(branch);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/branch/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(branch));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.branchName", is("SBERBANK FILIAL")));
    }

    @Test
    void deleteBranch() throws Exception{
        Mockito.when(branchRepository.getById(branch1.getBranchId())).thenReturn(branch1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/branch/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getBranchOnClient() throws Exception{
        Mockito.when(branchRepository.findBranchByClient(1)).thenReturn(List.of(branch1,branch2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].branchName", is("SBER FILIAL")))
                .andExpect(jsonPath("$[1].branchName", is("ALFA FILIAL")));
    }

    @Test
    void getAllBranchOfBank() throws Exception {
        Mockito.when(branchRepository.findBranchByBank(1)).thenReturn(List.of(branch1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/branch/bank/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].branchName", is("SBER FILIAL")));
    }
}