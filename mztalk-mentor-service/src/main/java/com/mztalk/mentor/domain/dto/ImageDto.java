package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Image;
import lombok.*;

@Data
@NoArgsConstructor
public class ImageDto {

    private Long id;
    private Application application;
    private String uploadFileName;
    private String storeFileName;
    private String url;

    public ImageDto(Image image) {
        this.id = image.getId();
        this.application = image.getApplication();
        this.uploadFileName = image.getUploadFileName();
        this.storeFileName = image.getStoreFileName();
        this.url = image.getUrl();
    }

    public Image toEntity(){
        Image build = Image.builder()
                .id(id)
                .application(application)
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .url(url)
                .build();
        return build;
    }

    @Builder
    public ImageDto(Long id, Application application, String uploadFileName, String storeFileName, String url) {
        this.id = id;
        this.application = application;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.url = url;
    }
}
