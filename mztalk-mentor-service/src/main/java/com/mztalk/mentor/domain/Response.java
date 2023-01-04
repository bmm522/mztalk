package com.mztalk.mentor.domain;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mztalk.mentor.domain.CancelHistory;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "imp_uid",
        "merchant_uid",
        "pay_method",
        "channel",
        "pg_provider",
        "emb_pg_provider",
        "pg_tid",
        "pg_id",
        "escrow",
        "apply_num",
        "bank_code",
        "bank_name",
        "card_code",
        "card_name",
        "card_quota",
        "card_number",
        "card_type",
        "vbank_code",
        "vbank_name",
        "vbank_num",
        "vbank_holder",
        "vbank_date",
        "vbank_issued_at",
        "name",
        "amount",
        "cancel_amount",
        "currency",
        "buyer_name",
        "buyer_email",
        "buyer_tel",
        "buyer_addr",
        "buyer_postcode",
        "custom_data",
        "user_agent",
        "status",
        "started_at",
        "paid_at",
        "failed_at",
        "cancelled_at",
        "fail_reason",
        "cancel_reason",
        "receipt_url",
        "cancel_history",
        "cancel_receipt_urls",
        "cash_receipt_issued",
        "customer_uid",
        "customer_uid_usage"
})
@Generated("jsonschema2pojo")
public class Response {

    @JsonProperty("imp_uid")
    private String impUid;
    @JsonProperty("merchant_uid")
    private String merchantUid;
    @JsonProperty("pay_method")
    private String payMethod;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("pg_provider")
    private String pgProvider;
    @JsonProperty("emb_pg_provider")
    private String embPgProvider;
    @JsonProperty("pg_tid")
    private String pgTid;
    @JsonProperty("pg_id")
    private String pgId;
    @JsonProperty("escrow")
    private Boolean escrow;
    @JsonProperty("apply_num")
    private String applyNum;
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonProperty("bank_name")
    private String bankName;
    @JsonProperty("card_code")
    private String cardCode;
    @JsonProperty("card_name")
    private String cardName;
    @JsonProperty("card_quota")
    private Integer cardQuota;
    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("card_type")
    private String cardType;
    @JsonProperty("vbank_code")
    private String vbankCode;
    @JsonProperty("vbank_name")
    private String vbankName;
    @JsonProperty("vbank_num")
    private String vbankNum;
    @JsonProperty("vbank_holder")
    private String vbankHolder;
    @JsonProperty("vbank_date")
    private Integer vbankDate;
    @JsonProperty("vbank_issued_at")
    private Integer vbankIssuedAt;
    @JsonProperty("name")
    private String name;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("cancel_amount")
    private Integer cancelAmount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("buyer_name")
    private String buyerName;
    @JsonProperty("buyer_email")
    private String buyerEmail;
    @JsonProperty("buyer_tel")
    private String buyerTel;
    @JsonProperty("buyer_addr")
    private String buyerAddr;
    @JsonProperty("buyer_postcode")
    private String buyerPostcode;
    @JsonProperty("custom_data")
    private String customData;
    @JsonProperty("user_agent")
    private String userAgent;
    @JsonProperty("status")
    private String status;
    @JsonProperty("started_at")
    private Integer startedAt;
    @JsonProperty("paid_at")
    private Integer paidAt;
    @JsonProperty("failed_at")
    private Integer failedAt;
    @JsonProperty("cancelled_at")
    private Integer cancelledAt;
    @JsonProperty("fail_reason")
    private String failReason;
    @JsonProperty("cancel_reason")
    private String cancelReason;
    @JsonProperty("receipt_url")
    private String receiptUrl;
    @JsonProperty("cancel_history")
    private List<CancelHistory> cancelHistory = null;
    @JsonProperty("cancel_receipt_urls")
    private List<String> cancelReceiptUrls = null;
    @JsonProperty("cash_receipt_issued")
    private Boolean cashReceiptIssued;
    @JsonProperty("customer_uid")
    private String customerUid;
    @JsonProperty("customer_uid_usage")
    private String customerUidUsage;

    @JsonProperty("imp_uid")
    public String getImpUid() {
        return impUid;
    }

    @JsonProperty("imp_uid")
    public void setImpUid(String impUid) {
        this.impUid = impUid;
    }

    @JsonProperty("merchant_uid")
    public String getMerchantUid() {
        return merchantUid;
    }

    @JsonProperty("merchant_uid")
    public void setMerchantUid(String merchantUid) {
        this.merchantUid = merchantUid;
    }

    @JsonProperty("pay_method")
    public String getPayMethod() {
        return payMethod;
    }

    @JsonProperty("pay_method")
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonProperty("pg_provider")
    public String getPgProvider() {
        return pgProvider;
    }

    @JsonProperty("pg_provider")
    public void setPgProvider(String pgProvider) {
        this.pgProvider = pgProvider;
    }

    @JsonProperty("emb_pg_provider")
    public String getEmbPgProvider() {
        return embPgProvider;
    }

    @JsonProperty("emb_pg_provider")
    public void setEmbPgProvider(String embPgProvider) {
        this.embPgProvider = embPgProvider;
    }

    @JsonProperty("pg_tid")
    public String getPgTid() {
        return pgTid;
    }

    @JsonProperty("pg_tid")
    public void setPgTid(String pgTid) {
        this.pgTid = pgTid;
    }

    @JsonProperty("pg_id")
    public String getPgId() {
        return pgId;
    }

    @JsonProperty("pg_id")
    public void setPgId(String pgId) {
        this.pgId = pgId;
    }

    @JsonProperty("escrow")
    public Boolean getEscrow() {
        return escrow;
    }

    @JsonProperty("escrow")
    public void setEscrow(Boolean escrow) {
        this.escrow = escrow;
    }

    @JsonProperty("apply_num")
    public String getApplyNum() {
        return applyNum;
    }

    @JsonProperty("apply_num")
    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    @JsonProperty("bank_code")
    public String getBankCode() {
        return bankCode;
    }

    @JsonProperty("bank_code")
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @JsonProperty("bank_name")
    public String getBankName() {
        return bankName;
    }

    @JsonProperty("bank_name")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @JsonProperty("card_code")
    public String getCardCode() {
        return cardCode;
    }

    @JsonProperty("card_code")
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    @JsonProperty("card_name")
    public String getCardName() {
        return cardName;
    }

    @JsonProperty("card_name")
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @JsonProperty("card_quota")
    public Integer getCardQuota() {
        return cardQuota;
    }

    @JsonProperty("card_quota")
    public void setCardQuota(Integer cardQuota) {
        this.cardQuota = cardQuota;
    }

    @JsonProperty("card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    @JsonProperty("card_number")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @JsonProperty("card_type")
    public String getCardType() {
        return cardType;
    }

    @JsonProperty("card_type")
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @JsonProperty("vbank_code")
    public String getVbankCode() {
        return vbankCode;
    }

    @JsonProperty("vbank_code")
    public void setVbankCode(String vbankCode) {
        this.vbankCode = vbankCode;
    }

    @JsonProperty("vbank_name")
    public String getVbankName() {
        return vbankName;
    }

    @JsonProperty("vbank_name")
    public void setVbankName(String vbankName) {
        this.vbankName = vbankName;
    }

    @JsonProperty("vbank_num")
    public String getVbankNum() {
        return vbankNum;
    }

    @JsonProperty("vbank_num")
    public void setVbankNum(String vbankNum) {
        this.vbankNum = vbankNum;
    }

    @JsonProperty("vbank_holder")
    public String getVbankHolder() {
        return vbankHolder;
    }

    @JsonProperty("vbank_holder")
    public void setVbankHolder(String vbankHolder) {
        this.vbankHolder = vbankHolder;
    }

    @JsonProperty("vbank_date")
    public Integer getVbankDate() {
        return vbankDate;
    }

    @JsonProperty("vbank_date")
    public void setVbankDate(Integer vbankDate) {
        this.vbankDate = vbankDate;
    }

    @JsonProperty("vbank_issued_at")
    public Integer getVbankIssuedAt() {
        return vbankIssuedAt;
    }

    @JsonProperty("vbank_issued_at")
    public void setVbankIssuedAt(Integer vbankIssuedAt) {
        this.vbankIssuedAt = vbankIssuedAt;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("cancel_amount")
    public Integer getCancelAmount() {
        return cancelAmount;
    }

    @JsonProperty("cancel_amount")
    public void setCancelAmount(Integer cancelAmount) {
        this.cancelAmount = cancelAmount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("buyer_name")
    public String getBuyerName() {
        return buyerName;
    }

    @JsonProperty("buyer_name")
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @JsonProperty("buyer_email")
    public String getBuyerEmail() {
        return buyerEmail;
    }

    @JsonProperty("buyer_email")
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    @JsonProperty("buyer_tel")
    public String getBuyerTel() {
        return buyerTel;
    }

    @JsonProperty("buyer_tel")
    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    @JsonProperty("buyer_addr")
    public String getBuyerAddr() {
        return buyerAddr;
    }

    @JsonProperty("buyer_addr")
    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    @JsonProperty("buyer_postcode")
    public String getBuyerPostcode() {
        return buyerPostcode;
    }

    @JsonProperty("buyer_postcode")
    public void setBuyerPostcode(String buyerPostcode) {
        this.buyerPostcode = buyerPostcode;
    }

    @JsonProperty("custom_data")
    public String getCustomData() {
        return customData;
    }

    @JsonProperty("custom_data")
    public void setCustomData(String customData) {
        this.customData = customData;
    }

    @JsonProperty("user_agent")
    public String getUserAgent() {
        return userAgent;
    }

    @JsonProperty("user_agent")
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("started_at")
    public Integer getStartedAt() {
        return startedAt;
    }

    @JsonProperty("started_at")
    public void setStartedAt(Integer startedAt) {
        this.startedAt = startedAt;
    }

    @JsonProperty("paid_at")
    public Integer getPaidAt() {
        return paidAt;
    }

    @JsonProperty("paid_at")
    public void setPaidAt(Integer paidAt) {
        this.paidAt = paidAt;
    }

    @JsonProperty("failed_at")
    public Integer getFailedAt() {
        return failedAt;
    }

    @JsonProperty("failed_at")
    public void setFailedAt(Integer failedAt) {
        this.failedAt = failedAt;
    }

    @JsonProperty("cancelled_at")
    public Integer getCancelledAt() {
        return cancelledAt;
    }

    @JsonProperty("cancelled_at")
    public void setCancelledAt(Integer cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    @JsonProperty("fail_reason")
    public String getFailReason() {
        return failReason;
    }

    @JsonProperty("fail_reason")
    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    @JsonProperty("cancel_reason")
    public String getCancelReason() {
        return cancelReason;
    }

    @JsonProperty("cancel_reason")
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @JsonProperty("receipt_url")
    public String getReceiptUrl() {
        return receiptUrl;
    }

    @JsonProperty("receipt_url")
    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    @JsonProperty("cancel_history")
    public List<CancelHistory> getCancelHistory() {
        return cancelHistory;
    }

    @JsonProperty("cancel_history")
    public void setCancelHistory(List<CancelHistory> cancelHistory) {
        this.cancelHistory = cancelHistory;
    }

    @JsonProperty("cancel_receipt_urls")
    public List<String> getCancelReceiptUrls() {
        return cancelReceiptUrls;
    }

    @JsonProperty("cancel_receipt_urls")
    public void setCancelReceiptUrls(List<String> cancelReceiptUrls) {
        this.cancelReceiptUrls = cancelReceiptUrls;
    }

    @JsonProperty("cash_receipt_issued")
    public Boolean getCashReceiptIssued() {
        return cashReceiptIssued;
    }

    @JsonProperty("cash_receipt_issued")
    public void setCashReceiptIssued(Boolean cashReceiptIssued) {
        this.cashReceiptIssued = cashReceiptIssued;
    }

    @JsonProperty("customer_uid")
    public String getCustomerUid() {
        return customerUid;
    }

    @JsonProperty("customer_uid")
    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    @JsonProperty("customer_uid_usage")
    public String getCustomerUidUsage() {
        return customerUidUsage;
    }

    @JsonProperty("customer_uid_usage")
    public void setCustomerUidUsage(String customerUidUsage) {
        this.customerUidUsage = customerUidUsage;
    }

}
