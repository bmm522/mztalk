package com.mztalk.mentor.domain.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="FILE")
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
