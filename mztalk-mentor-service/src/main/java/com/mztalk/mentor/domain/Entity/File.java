package com.mztalk.mentor.domain.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name="file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="application_id")
    private Application application;

    private String uploadFileName;
    private String storeFileName;
    private String extension;

}
