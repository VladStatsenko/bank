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
import org.statsenko.mapper.BankMapper;
import org.statsenko.service.services.BankService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {BankControllerImpl.class, BankService.class})
@WebMvcTest
class BankServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    BankMapper REST_MAPPER = Mappers.getMapper(BankMapper.class);

    @MockBean
    BankService bankService;

    Bank bank1 = new Bank(1,"SBER",null,null,null,null);
    Bank bank2 = new Bank(2,"ALFA",null,null,null,null);

    List<Bank> allBank = new ArrayList<>(Arrays.asList(bank1,bank2));

    @Test
    void getAllBank() throws Exception{

        Mockito.when(bankService.getAllBank()).thenReturn(REST_MAPPER.toDtoList(allBank));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/bank")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].bankName", is("SBER")))
                .andDo(print());
    }

    @Test
    void getBankById() throws Exception{

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(REST_MAPPER.toDto(bank1));

        Mockito.when(bankService.getBankById(bank1.getBankId())).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bank/1",bank1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.bankName",is("SBER")))
                .andDo(print());
    }

    @Test
    void createBank() throws Exception{
        Bank bank = new Bank(3,"VTB",null,null,null,null);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(REST_MAPPER.toDto(bank));

        Mockito.when(bankService.createBank(REST_MAPPER.toDto(bank))).thenReturn(messageResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/bank")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(bank));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void editBank() throws Exception{
        bank1.setBankName("SBERBANK");
        Mockito.when(bankService.editBank(REST_MAPPER.toDto(bank1),1)).thenReturn(REST_MAPPER.toDto(bank1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/bank/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(bank1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteBank() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse("Bank deleted");
        Mockito.when(bankService.deleteBank(bank1.getBankId())).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/bank/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBank_notFound() throws Exception{
        MessageResponse message = new MessageResponse();
        message.setExceptionName("Not found");
        message.setTechnicalDescription("Bank with your ID not found");
        Mockito.when(bankService.deleteBank(bank1.getBankId())).thenReturn(message);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/bank/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}