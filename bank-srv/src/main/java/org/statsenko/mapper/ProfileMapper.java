package org.statsenko.mapper;

import dto.request.ProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.statsenko.entity.Profile;

import java.util.List;

@Mapper
public interface ProfileMapper extends ViewMapper<Profile, ProfileDto> {
    @Mapping(source = "client.clientId", target = "clientId")
    @Override
    ProfileDto toDto(Profile entity);

    @Mapping(source = "client.clientId", target = "clientId")
    @Override
    List<ProfileDto> toDtoList(List<Profile> entityList);

    @Mapping(source = "clientId", target = "client.clientId")
    @Override
    Profile toEntity(ProfileDto dto);
}
