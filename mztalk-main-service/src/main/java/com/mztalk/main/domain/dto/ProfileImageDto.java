package com.mztalk.main.domain.dto;

import com.mztalk.main.domain.entity.ProfileImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImageDto {

    private MultipartFile file;

    public ProfileImage toEntity(String postImageUrl, String nickname){
        return ProfileImage.builder()
                .postImageUrl(postImageUrl)
                .nickname(nickname)
                .build();

    }

}
