package com.mztalk.resource.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;

    private String objectKey;

    private String fileName;

    private String fileUrl;
    private long id;

    @CreatedDate
    private Timestamp createDate;

}
