package com.mztalk.mentor.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="FILE")
public class File extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name="file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;
    private String fileName; // 사용자가 올린 파일 네임
    private String objectKey; // 서버에 저장되어있는 파일 네임
    private String fileUrl; // 서버에 저장되어있는 url

    @Builder
    public File(Long id, Application application, String fileName, String objectKey, String fileUrl) {
        this.id = id;
        this.application = application;
        this.fileName = fileName;
        this.objectKey = objectKey;
        this.fileUrl = fileUrl;
    }

    //== 연관관계 편의 메소드==//
    public void addApplication(Application application){
        if(this.application != null){
            this.application.getFiles().remove(this);
        }
        this.application = application;
        application.getFiles().add(this);
    }

}
