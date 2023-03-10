package com.mztalk.loginservice.report.repository.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mztalk.loginservice.user.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String reportTitle;
    private String reportContent;
    private String boardId;
    private String serviceName;
    private String path;

    @JoinColumn(name = "id")
    @ManyToOne
    @JsonIgnore
    private User user;

    private Date reportTime;

    private String reportStatus;

    @PrePersist
    public void onPrePersist(){
        this.reportTime = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
