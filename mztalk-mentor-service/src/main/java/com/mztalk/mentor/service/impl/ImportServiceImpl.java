package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.ImportAccessToken;
import com.mztalk.mentor.domain.dto.ImportCancelReqDto;
import com.mztalk.mentor.domain.dto.ImportCancelResDto;
import com.mztalk.mentor.service.ImportService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImportServiceImpl implements ImportService {

    @Value("${import.apiKey}")
    private String apiKey;

    @Value("${import.apiSecret}")
    private String apiSecret;

    @Override
    public String getToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/json; charset=UTF-8");

        JSONObject body = new JSONObject();
        body.put("imp_key",apiKey);
        body.put("imp_secret",apiSecret);

        ImportAccessToken token = restTemplate.postForObject("https://api.iamport.kr/users/getToken",
                new HttpEntity<>(body.toString(),headers),
                ImportAccessToken.class);
        return token.getResponse().get("access_token");
    }

    @Override
    public ImportCancelResDto cancelPayment(ImportCancelReqDto paymentCancelDto) {
        String accessToken = getToken();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/json; charset=UTF-8");
        headers.add("Authorization",accessToken);

        JSONObject body = new JSONObject();
        body.put("imp_uid",paymentCancelDto.getImp_uid());
        body.put("merchant_uid",paymentCancelDto.getMerchant_uid());
        body.put("amount",paymentCancelDto.getPrice());

        ImportCancelResDto importCancelResDto = restTemplate.postForObject("https://api.iamport.kr/payments/cancel",
                new HttpEntity<>(body.toString(),headers),
                ImportCancelResDto.class);

        return importCancelResDto;
    }


}
