package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.AccountInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountInfoDto {
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

    public AccountInfo toEntity(){
        return AccountInfo.builder().
                api_tran_id(api_tran_id).
                api_tran_dtm(api_tran_dtm).
                rsp_code(rsp_code).
                rsp_message(rsp_message).
                bank_tran_id(bank_tran_id).
                bank_tran_date(bank_tran_date).
                bank_code_tran(bank_code_tran).
                bank_rsp_code(bank_rsp_code).
                bank_rsp_message(bank_rsp_message).
                bank_code_std(bank_code_std).
                bank_code_sub(bank_code_sub).
                bank_name(bank_name).
                savings_bank_name(savings_bank_name).
                account_num(account_num).
                account_seq(account_seq).
                account_holder_info_type(account_holder_info_type).
                account_holder_info(account_holder_info).
                account_holder_name(account_holder_name).
                account_type(account_type).
                build();
    }
}
