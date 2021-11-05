package controllers;

import dto.request.ProfileDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/profile")
@Api("profile controller")
public interface ProfileController {

    @GetMapping
    ResponseEntity<List<ProfileDto>> getAllProfile();

    @GetMapping("/{id}")
    ResponseEntity getProfileById(@PathVariable int id);

    @PostMapping
    ResponseEntity createProfile(@RequestBody ProfileDto profileDto);

    @PutMapping("/{id}")
    ResponseEntity editProfile(@RequestBody ProfileDto profileDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity deleteProfile(@PathVariable int id);
}
