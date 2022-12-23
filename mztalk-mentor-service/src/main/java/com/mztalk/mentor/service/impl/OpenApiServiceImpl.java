package com.mztalk.mentor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mztalk.mentor.domain.dto.OpenApiAccessTokenDto;
import com.mztalk.mentor.domain.entity.AccountInfo;
import com.mztalk.mentor.domain.entity.OpenApiAccessToken;
import com.mztalk.mentor.repository.AccessTokenRepository;
import com.mztalk.mentor.repository.AccountInfoRepository;
import com.mztalk.mentor.service.OpenApiService;
import lombok.RequiredArgsConstructor;
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
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OpenApiServiceImpl implements OpenApiService {

    private final AccessTokenRepository accessTokenRepository;
    private final AccountInfoRepository accountInfoRepository;
    private final String CLIENT_ID = "6de69c1e-ef0f-4246-9b44-a50023552eb0";
    private final String CLIENT_SECRET = "9d2be733-f761-4fb0-89d4-4d3c71249463";
    private Long number = 000000001L;

    @Override
    public void requestOpenApiAccessToken() {
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
        accessTokenRepository.save(openApiAccessTokenDto.toEntity());
    }

    @Override
    @Transactional
    public boolean requestMatchAccountRealName(ConcurrentHashMap<String,String> accountMap) {
        String bankCode = accountMap.get("bankCode");
        String bankAccount = accountMap.get("bankAccount");
        String birthday = accountMap.get("birthday");

        if(birthday.length() != 6){return false;}

        RestTemplate accountRestTemplate = new RestTemplate();

        HttpHeaders accountHeaders = new HttpHeaders();
        accountHeaders.add("Content-type","application/json; charset=UTF-8");
//        accountHeaders.add("Authorization", tokenType + " " + accessToken);

        MultiValueMap<String, String> accountBody = new LinkedMultiValueMap<>();
        accountBody.add("bank_tran_id","M202202375U" + (++number));
        accountBody.add("bank_code_std",bankCode);
        accountBody.add("account_num",bankAccount);
        accountBody.add("account_holder_info_type","");
        accountBody.add("account_holder_info",birthday);
        accountBody.add("tran_dtime", LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        HttpEntity<MultiValueMap<String, String>> accountRequest= new HttpEntity<>(accountBody, accountHeaders);

        ResponseEntity<String> accountResponse = accountRestTemplate.exchange(
                "https://testapi.openbanking.or.kr/v2.0/inquiry/real_name",
                HttpMethod.POST,
                accountRequest,
                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        AccountInfo accountInfo = null;
        try {
            accountInfo = objectMapper.readValue(accountResponse.getBody(), AccountInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("rsp_message : " + accountInfo.getRsp_message());
        System.out.println("bank_code_std : " + accountInfo.getBank_code_std());
        System.out.println("bank_name : " + accountInfo.getBank_name());
        System.out.println("account_num : " + accountInfo.getAccount_num());
        System.out.println("account_holder_info : " + accountInfo.getAccount_holder_info());
        System.out.println("account_holder_name : " + accountInfo.getAccount_holder_name());

        if(accountInfo.getRsp_message().equals("")){
            return true;
        }
        return false;
    }

    @Override
    public boolean getCrdiAccountInfo(Long crdiSeq) {
        return false;
    }


}
