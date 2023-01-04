package com.mztalk.mentor.domain;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pg_tid",
        "amount",
        "cancelled_at",
        "reason",
        "receipt_url"
})
@Generated("jsonschema2pojo")
public class CancelHistory {

    @JsonProperty("pg_tid")
    private String pgTid;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("cancelled_at")
    private Integer cancelledAt;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("receipt_url")
    private String receiptUrl;

    @JsonProperty("pg_tid")
    public String getPgTid() {
        return pgTid;
    }

    @JsonProperty("pg_tid")
    public void setPgTid(String pgTid) {
        this.pgTid = pgTid;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("cancelled_at")
    public Integer getCancelledAt() {
        return cancelledAt;
    }

    @JsonProperty("cancelled_at")
    public void setCancelledAt(Integer cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("receipt_url")
    public String getReceiptUrl() {
        return receiptUrl;
    }

    @JsonProperty("receipt_url")
    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

}
