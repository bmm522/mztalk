package com.mztalk.mentor.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountInfo {
    @Id @GeneratedValue
    private Long id;

    private String rsp_message;
    private String bank_code_std;
    private String bank_name;
    private String account_num;
    private String account_holder_info;
    private String account_holder_name;

}
