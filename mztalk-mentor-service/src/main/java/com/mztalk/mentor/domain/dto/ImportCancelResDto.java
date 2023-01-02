package com.mztalk.mentor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImportCancelResDto {

    private String code;
    private String message;
    private HashMap<String,String> response;
    private HashMap<String,String> cancel_history;
    private String cancel_reason;
    private HashMap<String,String> cancel_receipt_urls;
    private String cancelled_at;
    private String card_code;
    private String card_name;
    private String card_number;
    private String card_quota;
    private String card_type;
    private String cash_receipt_issued;
    private String channel;
    private String currency;
    private String custom_data;
    private String customer_uid;
    private String customer_uid_usage;
    private String emb_pg_provider;
    private String escrow;
    private String fail_reason;
    private String failed_at;
    private String imp_uid;
    private String merchant_uid;
    private String name;
    private String paid_at;
    private String pay_method;
    private String pg_id;
    private String pg_provider;
    private String pg_tid;
    private String receipt_url;
    private String started_at;
    private String status;
    private String user_agent;
    private String vbank_code;
    private String vbank_date;
    private String vbank_holder;
    private String vbank_issued_at;
    private String vbank_name;
    private String vbank_num;


}
