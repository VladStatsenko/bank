package org.statsenko.service.services;

import dto.request.ProfileDto;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.statsenko.entity.Profile;
import org.statsenko.mapper.ProfileMapper;
import org.statsenko.repository.ProfileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private static final ProfileMapper REST_MAPPER = Mappers.getMapper(ProfileMapper.class);

    private final ProfileRepository profileRepository;

    public ProfileDto getProfileById(int id){
        ProfileDto profile = REST_MAPPER.toDto(profileRepository.getById(id));
        return profile;
    }

    public List<ProfileDto> getAllProfile(){
        List<ProfileDto> profileDtoList = REST_MAPPER.toDtoList(profileRepository.findAll());
        return profileDtoList;
    }

    public ProfileDto createProfile(MultipartFile photo,ProfileDto profileDto) throws Exception {
        Profile profile = REST_MAPPER.toEntity(profileDto);
        profile.setPhoto(photo.getOriginalFilename());
        saveUploadedFile(photo,profileDto.getLogin());
        profileRepository.save(profile);
        return profileDto;
    }

    public ProfileDto editProfile(ProfileDto profileDto, int id){
        Profile profile = REST_MAPPER.toEntity(profileDto);
        profile.setProfileId(id);
        profileRepository.save(profile);
        return profileDto;
    }

    public void deleteProfile(int id){
        profileRepository.deleteById(id);
    }

    private String saveUploadedFile(MultipartFile photo ,String profile) throws Exception {
        String folder = "photo";

        String file = folder + "/" + profile;
        String resultPath = folder + "/" + profile;

        Path uploadDir = Paths.get(resultPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path filePath = uploadDir.resolve(Objects.requireNonNull(photo.getOriginalFilename()));
        File resizeFile = new File(String.valueOf(filePath));
        simpleResizeImage(photo, resizeFile);


        return "/" + file + "/" + photo.getOriginalFilename();
    }

    private void simpleResizeImage(MultipartFile newPhoto, File resizeFile) throws Exception {
        Thumbnails.of(newPhoto.getInputStream()).crop(Positions.CENTER_LEFT).size(36, 36).keepAspectRatio(true).toFile(resizeFile);
    }
}
