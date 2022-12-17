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
public class FileDto {

    private Long id;
    private Application application;
    private String uploadFileName;
    private String storeFileName;
    private String url;

    public FileDto(File file) {
        this.id = file.getId();
        this.application = file.getApplication();
        this.uploadFileName = file.getUploadFileName();
        this.storeFileName = file.getStoreFileName();
        this.url = file.getUrl();
    }

    public File toEntity(){
        File build = File.builder()
                .id(id)
                .application(application)
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .url(url)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, Application application, String uploadFileName, String storeFileName, String url) {
        this.id = id;
        this.application = application;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.url = url;
    }

    //이미지 저장 메소드
    public FileDto saveFile(MultipartFile file, HttpServletRequest request){
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

        FileDto fileDto = FileDto.builder()
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .url(savePath)
                .build();
        return fileDto;
    }
}
