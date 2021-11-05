package org.statsenko.mapper;

import dto.request.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.statsenko.entity.Client;

import java.util.List;

@Mapper
public interface ClientMapper extends ViewMapper<Client, ClientDto> {

    @Mapping(source = "profile.profileId", target = "profileId")
    @Override
    ClientDto toDto(Client entity);

    @Mapping(source = "profile.profileId", target = "profileId")
    @Override
    List<ClientDto> toDtoList(List<Client> entityList);

    @Mapping(source = "profileId", target = "profile.profileId")
    @Override
    Client toEntity(ClientDto dto);
}
