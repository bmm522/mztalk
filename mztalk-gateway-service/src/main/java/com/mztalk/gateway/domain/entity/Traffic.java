package com.mztalk.gateway.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Traffic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serviceName;
    private String requestUrl;

    private Date requestTime;

    @PrePersist
    public void onPrePersist(){
        this.requestTime = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public Traffic(String serviceName, String uri){
        this.serviceName = serviceName;
        this.requestUrl = uri;
    }

}
