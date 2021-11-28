package org.statsenko.service.services;

import dto.request.AccountDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.statsenko.entity.Account;
import org.statsenko.entity.AccountType;
import org.statsenko.entity.Bank;
import org.statsenko.entity.Client;
import org.statsenko.mapper.AccountMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private static final AccountMapper REST_MAPPER = Mappers.getMapper(AccountMapper.class);

    Client client = new Client(1,"Vlad","Ivanov","Ivan", null,"33333",
            null,null,null,null, null);
    Bank bank1 = new Bank(1,"SBER",null,null, null,null);
    AccountType type = new AccountType(1,"DEB","DEBIT CARD","debet card",
            null,null,null);

    Account account1 = new Account(1,"4444 4444 4444 4444",null,null,
            client,bank1,type);
    Account account2 = new Account(1,"5555 4444 4444 4444",null,null,
            client,bank1,type);

    @Test
    void getAllAccounts() {
        List<AccountDto> accountDtoList = REST_MAPPER.toDtoList(List.of(account1,account2));
        assertEquals("4444 4444 4444 4444",accountDtoList.get(0).getNumberAccount());
        assertEquals("5555 4444 4444 4444",accountDtoList.get(1).getNumberAccount());
        assertEquals("Ivanov",accountDtoList.get(0).getClient());
        assertEquals("SBER",accountDtoList.get(1).getBank());
    }

    @Test
    void getClientAccount() {
        List<AccountDto> accountDtoList = REST_MAPPER.toDtoList(List.of(account1,account2));
        assertEquals("Ivanov",accountDtoList.get(0).getClient());
        assertEquals("Ivanov",accountDtoList.get(1).getClient());

    }

    @Test
    void createAccount() {
        AccountDto accountDto = new AccountDto("1111 1111 1111 1111","1","1","1");
        Account account = REST_MAPPER.toEntity(accountDto);

        assertEquals(1, account.getClient().getClientId());
        assertEquals(1,account.getType().getAccountTypeId());
        assertEquals(1,account.getBankAccounts().getBankId());
    }
}