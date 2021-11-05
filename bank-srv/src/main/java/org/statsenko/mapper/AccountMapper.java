package org.statsenko.mapper;

import dto.request.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.statsenko.entity.Account;

import java.util.List;

@Mapper
public interface AccountMapper extends ViewMapper<Account, AccountDto> {
    @Mapping(source = "account.clientId", target = "clientId")
    @Mapping(source = "bankAccounts.bankId", target = "bankId")
    @Mapping(source = "type.accountTypeId", target = "typeId")
    @Override
    AccountDto toDto(Account entity);

    @Mapping(source = "account.clientId", target = "clientId")
    @Mapping(source = "bankAccounts.bankId", target = "bankId")
    @Mapping(source = "type.accountTypeId", target = "typeId")
    @Override
    List<AccountDto> toDtoList(List<Account> entityList);

    @Mapping( source = "clientId",target = "account.clientId")
    @Mapping(source = "bankId",target = "bankAccounts.bankId")
    @Mapping(source = "typeId",target = "type.accountTypeId")
    @Override
    Account toEntity(AccountDto dto);
}
