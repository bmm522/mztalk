package com.mztalk.resource.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    private String objectKey;
    private String imageName;
    private String imageUrl;
    private String serviceName;
    private long bNo;

    @CreationTimestamp
    private Timestamp createDate;

    private long imageLevel;

    private String status;

}
