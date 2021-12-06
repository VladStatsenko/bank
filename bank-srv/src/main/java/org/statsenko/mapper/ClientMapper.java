package org.statsenko.mapper;

import dto.request.ClientDto;
import dto.request.ProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.statsenko.entity.Client;
import org.statsenko.entity.Profile;

import java.util.List;

@Mapper
public interface ClientMapper extends ViewMapper<Client, ClientDto> {

    @Mapping(source = "profile.login",target = "login")
    @Override
    ClientDto toDto(Client entity);

    @Mapping(source = "profile.login",target = "login")
    @Override
    List<ClientDto> toDtoList(List<Client> entityList);

    @Mapping(source = "login",target = "profile.profileId")
    @Override
    Client toEntity(ClientDto dto);

    @Mappings(@Mapping(target = "clientId", ignore = true))
    Client update(ClientDto source, @MappingTarget Client target);

}
