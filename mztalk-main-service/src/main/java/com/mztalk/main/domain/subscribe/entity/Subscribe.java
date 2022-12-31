package com.mztalk.main.domain.subscribe.entity;

import com.mztalk.main.common.BaseTimeEntity;
import com.mztalk.main.status.RoleStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="pay")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Subscribe extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="paymentId")
    private Long id;

//    private int price; 이건 나중에 할인정책 들어가면

    private String username;

    private Long userNo;

    private int period; //기간

    private RoleStatus roleStatus; //vip여부

}
