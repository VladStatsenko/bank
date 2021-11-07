package org.statsenko.mapper;

import dto.request.ProfileDto;
import org.mapstruct.Mapper;
import org.statsenko.entity.Profile;

import java.util.List;

@Mapper
public interface ProfileMapper extends ViewMapper<Profile, ProfileDto> {


    @Override
    ProfileDto toDto(Profile entity);

    @Override
    List<ProfileDto> toDtoList(List<Profile> entityList);

    @Override
    Profile toEntity(ProfileDto dto);
}
