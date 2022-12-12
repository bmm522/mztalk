package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.dto.ImageDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name="IMAGE")
public class Image extends com.mztalk.mentor.domain.entity.BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name="image_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    private String uploadFileName;
    private String storeFileName;
    private String url;

    @Builder
    public Image(Long id, Application application, String uploadFileName, String storeFileName, String url) {
        this.id = id;
        this.application = application;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.url = url;
    }

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

    //== 연관관계 편의 메소드==//
    public void addApplication(Application application) {
        this.application = application;
        application.addImage(this);
    }


}
