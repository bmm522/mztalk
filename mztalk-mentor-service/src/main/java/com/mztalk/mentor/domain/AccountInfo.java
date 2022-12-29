package com.mztalk.mentor.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class AccountInfo {

    private Long id;
    private String api_tran_id;
    private String api_tran_dtm;
    private String rsp_code;
    private String rsp_message;
    private String bank_tran_id;
    private String bank_tran_date;
    private String bank_code_tran;
    private String bank_rsp_code;
    private String bank_rsp_message;
    private String bank_code_std;
    private String bank_code_sub;
    private String bank_name;
    private String savings_bank_name;
    private String account_num;
    private String account_seq;
    private String account_holder_info_type;
    private String account_holder_info;
    private String account_holder_name;
    private String account_type;

    @Builder
    public AccountInfo(String api_tran_id, String api_tran_dtm, String rsp_code, String rsp_message, String bank_tran_id, String bank_tran_date, String bank_code_tran, String bank_rsp_code, String bank_rsp_message, String bank_code_std, String bank_code_sub, String bank_name, String savings_bank_name, String account_num, String account_seq, String account_holder_info_type, String account_holder_info, String account_holder_name, String account_type) {
        this.api_tran_id = api_tran_id;
        this.api_tran_dtm = api_tran_dtm;
        this.rsp_code = rsp_code;
        this.rsp_message = rsp_message;
        this.bank_tran_id = bank_tran_id;
        this.bank_tran_date = bank_tran_date;
        this.bank_code_tran = bank_code_tran;
        this.bank_rsp_code = bank_rsp_code;
        this.bank_rsp_message = bank_rsp_message;
        this.bank_code_std = bank_code_std;
        this.bank_code_sub = bank_code_sub;
        this.bank_name = bank_name;
        this.savings_bank_name = savings_bank_name;
        this.account_num = account_num;
        this.account_seq = account_seq;
        this.account_holder_info_type = account_holder_info_type;
        this.account_holder_info = account_holder_info;
        this.account_holder_name = account_holder_name;
        this.account_type = account_type;
    }
}
