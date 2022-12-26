package com.mztalk.login.domain.entity;


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
    private Long reportNo;

    private String reportTitle;
    private String reportContent;
    private String boardId;
    private String serviceName;
    private String path;

    @JoinColumn(name = "id")
    @ManyToOne
    private User user;

    private Date reportTime;

    @PrePersist
    public void onPrePersist(){
        this.reportTime = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
