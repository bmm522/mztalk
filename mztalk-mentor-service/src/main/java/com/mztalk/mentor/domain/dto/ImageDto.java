package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Image;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

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

    //이미지 저장 메소드
    public ImageDto saveFile(MultipartFile file, HttpServletRequest request){
        String root = request.getSession().getServletContext().getRealPath("resources");
        String savePath = root + "\\uploadFiles";

        File folder = new File(savePath);
        if(!folder.exists()) {
            folder.mkdirs();
        }

        uploadFileName = file.getOriginalFilename();
        storeFileName = UUID.randomUUID().toString()+file.getOriginalFilename();
        String renamePath = folder + "\\" + storeFileName;

        try {
            file.transferTo(new File(renamePath));
        } catch (Exception e) {
            e.getStackTrace();
        }

        ImageDto imageDto = ImageDto.builder()
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .url(savePath)
                .build();
        return imageDto;
    }
}
