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
import org.statsenko.entity.Account;
import org.statsenko.entity.AccountType;
import org.statsenko.entity.Bank;
import org.statsenko.entity.Client;
import org.statsenko.repository.AccountRepository;
import org.statsenko.service.services.AccountService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AccountControllerImpl.class, AccountService.class})
@WebMvcTest
class AccountServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AccountRepository accountRepository;

    Client client = new Client(1,"Vlad","Ivanov","Ivan", null,"33333",
            null,null,null,null, null);
    Bank bank1 = new Bank(1,"SBER",null,null, null,null);
    AccountType type = new AccountType(1,"DEB","DEBIT CARD","debet card",
            null,null,null);

    Account account1 = new Account(1,"4444 4444 4444 4444",null,null,
            client,bank1,type);
    Account account2 = new Account(1,"5555 4444 4444 4444",null,null,
            client,bank1,type);

    List<Account> account = new ArrayList<>(List.of(account1,account2));

    @Test
    void getClientAccount() throws Exception{
        Mockito.when(accountRepository.findClientByAccount(1)).thenReturn(List.of(account1,account2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/account/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].numberAccount", is("4444 4444 4444 4444")));

    }

    @Test
    void getAllAccounts() throws Exception {

        Mockito.when(accountRepository.findAll()).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/account")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].numberAccount", is("4444 4444 4444 4444")));
    }

    @Test
    void createAccount() throws Exception{

        Account newAccount = new Account(3,"6666 4444 4444 4444",null,null,null,null,null);

        Mockito.when(accountRepository.save(newAccount)).thenReturn(newAccount);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(newAccount));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.numberAccount", is("6666 4444 4444 4444")));
    }

    @Test
    void deleteAccount() throws Exception{
        Mockito.when(accountRepository.getById(account1.getAccountId())).thenReturn(account1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/account/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}