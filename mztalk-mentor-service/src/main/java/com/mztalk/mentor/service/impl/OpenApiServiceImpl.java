package com.mztalk.mentor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mztalk.mentor.domain.dto.AccountInfoReqDto;
import com.mztalk.mentor.domain.dto.AccountInfoResDto;
import com.mztalk.mentor.domain.dto.OpenApiAccessTokenDto;
import com.mztalk.mentor.domain.OpenApiAccessToken;
import com.mztalk.mentor.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OpenApiServiceImpl implements OpenApiService {

    @Value("${account.clientId}")
    private String CLIENT_ID;

    @Value("${account.clientSecret}")
    private String CLIENT_SECRET;

    @Override
    public OpenApiAccessToken requestOpenApiAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded; charset=UTF-8");

        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("client_id",CLIENT_ID);
        body.add("client_secret",CLIENT_SECRET);
        body.add("scope","oob");
        body.add("grant_type","client_credentials");

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body,headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://testapi.openbanking.or.kr/oauth/2.0/token",
                HttpMethod.POST,
                tokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OpenApiAccessTokenDto openApiAccessTokenDto = null;
        try {
            openApiAccessTokenDto = objectMapper.readValue(response.getBody(), OpenApiAccessTokenDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return openApiAccessTokenDto.toEntity();
    }

    @Override
    @Transactional
    public AccountInfoResDto requestMatchAccountRealName(AccountInfoReqDto accountInfoReqDto) {
        OpenApiAccessToken token = requestOpenApiAccessToken();
        int uniqueNum = (int)((Math.random()+10) * 10000000);

        String tokenType = token.getTokenType();
        String accessToken = token.getAccessToken();

        String bankCode = accountInfoReqDto.getBankCode();
        String bankAccount = accountInfoReqDto.getBankAccount();
        String birthday = accountInfoReqDto.getBirthday();

        RestTemplate accountRestTemplate = new RestTemplate();

        HttpHeaders accountHeaders = new HttpHeaders();
        accountHeaders.add("Content-type","application/json; charset=UTF-8");
        accountHeaders.add("Authorization", tokenType + " " + accessToken);

        JSONObject accountBody = new JSONObject();
        accountBody.put("bank_tran_id",token.getClientUseCode() + "U" + (uniqueNum));
        accountBody.put("bank_code_std",bankCode);
        accountBody.put("account_num",bankAccount);
        accountBody.put("account_holder_info_type","");
        accountBody.put("account_holder_info",birthday);
        accountBody.put("tran_dtime", LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        AccountInfoResDto accountInfoResDto = accountRestTemplate.postForObject("https://testapi.openbanking.or.kr/v2.0/inquiry/real_name",
                new HttpEntity<>(accountBody.toString(), accountHeaders),
                AccountInfoResDto.class);

        return accountInfoResDto;
    }

}
