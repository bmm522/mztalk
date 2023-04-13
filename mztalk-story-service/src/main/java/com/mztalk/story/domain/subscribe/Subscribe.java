package com.mztalk.story.domain.subscribe;

import com.mztalk.story.status.RoleStatus;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="subscribe")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Subscribe  {

    @Id
    @GeneratedValue
    @Column(name="Id")
    private Long id;
//    private int price; 이건 나중에 할인정책 들어가면
    private String username;

    private Long userNo;

    @Enumerated(EnumType.STRING)
    private RoleStatus roleStatus; //vip여부

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime vipDate;

    @CreationTimestamp
    private Timestamp createDate;

    private int price;



}
