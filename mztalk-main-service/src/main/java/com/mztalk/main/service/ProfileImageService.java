package com.mztalk.main.service;


import com.mztalk.main.domain.dto.ProfileImageDto;
import com.mztalk.main.domain.entity.ProfileImage;
import com.mztalk.main.repository.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfileImageService {

    private final ProfileImageRepository profileImageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void ChangeProfileImage(ProfileImageDto profileImageDto, String nickname){
        UUID uuid = UUID.randomUUID();

        String imageFileName = uuid + "_" + profileImageDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try {
            Files.write(imageFilePath, profileImageDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProfileImage profileImage = profileImageDto.toEntity(imageFileName, nickname);

        profileImageRepository.save(profileImage);


    }
}
