package com.mztalk.login.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/login")
@Controller
public class TestController {

    @GetMapping("/bank")
    public void bankTest(){
//        System.out.println("code : " + code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        MultiValueMap<String, String>  body = new LinkedMultiValueMap<String, String>();

//        body.add("code", code);
        body.add("client_id", "73168934-87b4-41fa-87ac-6ee7d9ba6b12");
        body.add("client_secret", "93c6ce3c-484d-4e2e-8947-837b35885159");
        body.add("scope", "oob");
        body.add("grant_type", "client_credentials");
//        body.add("redirect_uri", "http://localhost:8000/login/bank");
//        body.add("grant_type", "authorization_code");

        ResponseEntity<String> response = new RestTemplate().exchange(
                "https://testapi.openbanking.or.kr/oauth/2.0/token",
                HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(body, headers),
                String.class
        );

        System.out.println("첫번째 요청 : " + response.getBody());


        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Content-Type", "application/json; charset=UTF-8");
        headers1.add("Authorization", "Bearer "+asString(response.getBody(),  "access_token"));

        MultiValueMap<String, String> body2 = new LinkedMultiValueMap<String, String>();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bank_tran_id",  asString(response.getBody(), "client_use_code")+"U501239456");
        jsonObject.put("bank_code_std", "088");
        jsonObject.put("account_num", "110444225524");
        jsonObject.put("account_holder_info_type", " ");
        jsonObject.put("account_holder_info", "960205");
        jsonObject.put("tran_dtime", "20221224174011");

        body2.add("bank_tran_id", asString(response.getBody(), "client_use_code")+"U501239456");
        body2.add("bank_code_std", "088");
        body2.add("account_num", "110444225524");
        body2.add("account_holder_info_type", "1");
//        body2.add("account_holder_info", "960205");
        body2.add("tran_dtime", "20221224173834237");

        ResponseEntity<String> response1 = new RestTemplate().exchange(
                "https://testapi.openbanking.or.kr/v2.0/inquiry/real_name",
                HttpMethod.POST,
                new HttpEntity<JSONObject>(jsonObject, headers1),
                String.class
        );

        System.out.println("두번째 요청 : " + response1.getBody());


    }




    protected String asString(String data,String dataname) {
        try{

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(data);
            return element.getAsJsonObject().get(dataname).getAsString();
        } catch(Exception e) {
            System.out.println("not JsonObject");
        }
        return "not JsonObject";
    }


}
