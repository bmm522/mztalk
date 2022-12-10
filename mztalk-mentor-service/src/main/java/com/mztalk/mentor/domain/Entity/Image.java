package com.mztalk.mentor.domain.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="IMAGE")
public class Image extends com.mztalk.mentor.domain.entity.BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name="file_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "image")
    private Application application;

    private String uploadFileName;
    private String storeFileName;
    private String url;

    @Builder
    public Image(Application application, String uploadFileName, String storeFileName, String extension,String url) {
        this.application = application;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.url = url;
    }

    public static void addApplication(Application application) {
        Image image = new Image();
        image.application = application;
    }

    public void uploadImage(MultipartFile file){
        this.uploadFileName = file.getOriginalFilename();
        this.storeFileName = UUID.randomUUID().toString()+file.getOriginalFilename();

    }
}
