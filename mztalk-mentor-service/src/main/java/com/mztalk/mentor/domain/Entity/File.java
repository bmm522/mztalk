package com.mztalk.mentor.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="FILE")
public class File extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="file_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "file")
    private Application application;

    private String uploadFileName;
    private String storeFileName;
    private String extension;

    @Builder
    public File(Application application, String uploadFileName, String storeFileName, String extension) {
        this.application = application;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.extension = extension;
    }
}
