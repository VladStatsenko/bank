package org.statsenko.mapper;

import dto.request.ProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.statsenko.entity.Profile;

import java.util.List;

@Mapper
public interface ProfileMapper extends ViewMapper<Profile, ProfileDto> {

    @Mapping(source = "profileId", target = "id")
    @Override
    ProfileDto toDto(Profile entity);
    @Mapping(source = "profileId", target = "id")
    @Override
    List<ProfileDto> toDtoList(List<Profile> entityList);

    @Override
    Profile toEntity(ProfileDto dto);

    @Mappings(@Mapping(target = "profileId", ignore = true))
    Profile update(ProfileDto source, @MappingTarget Profile target);
}
