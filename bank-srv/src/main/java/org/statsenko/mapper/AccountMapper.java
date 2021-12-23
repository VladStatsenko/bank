package org.statsenko.mapper;

import dto.request.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.statsenko.entity.Account;

import java.util.List;

@Mapper
public interface AccountMapper extends ViewMapper<Account, AccountDto> {

    @Mapping(source = "accountId", target = "id")
    @Mapping(source = "client.lastName", target = "client")
    @Mapping(source = "bankAccounts.bankName", target = "bank")
    @Mapping(source = "type.typeName", target = "type")
    @Override
    AccountDto toDto(Account entity);

    @Mapping(source = "accountId", target = "id")
    @Mapping(source = "client.lastName", target = "client")
    @Mapping(source = "bankAccounts.bankName", target = "bank")
    @Mapping(source = "type.typeName", target = "type")
    @Override
    List<AccountDto> toDtoList(List<Account> entityList);

    @Mapping( source = "client",target = "client.clientId")
    @Mapping(source = "bank",target = "bankAccounts.bankId")
    @Mapping(source = "type",target = "type.accountTypeId")
    @Override
    Account toEntity(AccountDto dto);
}
