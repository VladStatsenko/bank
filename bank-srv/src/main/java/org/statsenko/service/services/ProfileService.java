package org.statsenko.service.services;

import dto.request.ProfileDto;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.statsenko.entity.Profile;
import org.statsenko.mapper.ProfileMapper;
import org.statsenko.repository.ProfileRepository;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private static final ProfileMapper REST_MAPPER = Mappers.getMapper(ProfileMapper.class);

    private final ProfileRepository profileRepository;

    @Loggable
    public MessageResponse<ProfileDto> getProfileById(int id){
        MessageResponse message = new MessageResponse();
        if (profileRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Profile with your ID not found");
        }
        else {
            ProfileDto profile = REST_MAPPER.toDto(profileRepository.getById(id));
            message.setResponse(profile);
        }
        return message;
    }

    @Loggable
    public List<ProfileDto> getAllProfile(){
        List<ProfileDto> profileDtoList = REST_MAPPER.toDtoList(profileRepository.findAll());
        return profileDtoList;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse<ProfileDto> createProfile(MultipartFile photo,ProfileDto profileDto) throws Exception {
        MessageResponse message = new MessageResponse();
        if (profileRepository.existsByLogin(profileDto.getLogin())){
            message.setExceptionName("Similar login");
            message.setTechnicalDescription("Bank with this login already exist");
        }
        else {
            Profile profile = REST_MAPPER.toEntity(profileDto);
            profile.setPhoto(photo.getBytes());
            profileRepository.save(profile);
            message.setResponse(profile);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse<ProfileDto> editProfile(ProfileDto profileDto, int id){
        MessageResponse message = new MessageResponse();
        if (profileRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Profile with your ID not found");
        }
        else {
            Profile profile = REST_MAPPER.update(profileDto, profileRepository.getById(id));
            profileRepository.save(profile);
            message.setResponse(profileDto);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse deleteProfile(int id){
        MessageResponse message = new MessageResponse();
        if (profileRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Profile with your ID not found");
        }
        else {
            profileRepository.deleteById(id);
            message.setResponse("Profile deleted");
        }
        return message;
    }
}
