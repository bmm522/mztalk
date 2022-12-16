package com.mztalk.main.controller;


import com.mztalk.main.domain.dto.ProfileImageDto;
import com.mztalk.main.handler.exception.CustomValidationException;
import com.mztalk.main.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProfileImageApiController {

    private final ProfileImageService profileImageService;

    @PutMapping("/profileImage/upload")
    public void profileImageUpload(ProfileImageDto profileImageDto, @RequestBody String nickname) {

        if (profileImageDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지않았습니다.", null);
        }

        profileImageService.ChangeProfileImage(profileImageDto, nickname);
    }

}
