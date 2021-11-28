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
import org.statsenko.repository.BankRepository;
import org.statsenko.service.services.BankService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {BankControllerImpl.class, BankService.class})
@WebMvcTest
class BankServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BankRepository bankRepository;

    Bank bank1 = new Bank(1,"SBER",null,null,null,null);
    Bank bank2 = new Bank(2,"ALFA",null,null,null,null);

    List<Bank> bank = new ArrayList<>(Arrays.asList(bank1,bank2));

    @Test
    void getAllBank() throws Exception{


        Mockito.when(bankRepository.findAll()).thenReturn(bank);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/bank")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].bankName", is("SBER")));
    }

    @Test
    void getBankById() throws Exception{
        Mockito.when(bankRepository.getById(bank1.getBankId())).thenReturn((bank1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bank/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.bankName", is("SBER")));
    }

    @Test
    void createBank() throws Exception{
        Bank bank = new Bank(3,"VTB",null,null,null,null);

        Mockito.when(bankRepository.save(bank)).thenReturn(bank);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/bank")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(bank));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.bankName", is("VTB")));
    }

    @Test
    void editBank() throws Exception{
        Bank bank = new Bank(1,"SBERBANK",null,null,null,null);

        Mockito.when(bankRepository.save(bank)).thenReturn(bank);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/bank/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(bank));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.bankName", is("SBERBANK")));

    }

    @Test
    void deleteBank() throws Exception{
        Mockito.when(bankRepository.getById(bank1.getBankId())).thenReturn(bank1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/bank/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBank_notFound() throws Exception{
        Mockito.when(bankRepository.getById(5)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/bank/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}