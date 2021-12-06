package org.statsenko.service.services;

import dto.request.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.statsenko.entity.Profile;
import org.statsenko.mapper.ProfileMapper;
import org.statsenko.repository.ProfileRepository;
import org.statsenko.service.aspect.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private static final ProfileMapper REST_MAPPER = Mappers.getMapper(ProfileMapper.class);

    private final ProfileRepository profileRepository;

    @Loggable
    public ProfileDto getProfileById(int id){
        ProfileDto profile = REST_MAPPER.toDto(profileRepository.getById(id));
        return profile;
    }

    @Loggable
    public List<ProfileDto> getAllProfile(){
        List<ProfileDto> profileDtoList = REST_MAPPER.toDtoList(profileRepository.findAll());
        return profileDtoList;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public ProfileDto createProfile(MultipartFile photo,ProfileDto profileDto) throws Exception {
        Profile profile = REST_MAPPER.toEntity(profileDto);
        profile.setPhoto(photo.getBytes());
        profileRepository.save(profile);
        return profileDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public ProfileDto editProfile(ProfileDto profileDto, int id){
        Profile profile = REST_MAPPER.update(profileDto, profileRepository.getById(id));
        profileRepository.save(profile);
        return profileDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProfile(int id){
        profileRepository.deleteById(id);
    }
}
