package com.mztalk.main.domain.subscribe.service.impl;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeResponseDto;
import com.mztalk.main.domain.subscribe.entity.Subscribe;
import com.mztalk.main.domain.subscribe.repository.SubscribeRepository;
import com.mztalk.main.domain.subscribe.service.SubscribeService;
import com.mztalk.main.handler.exception.CustomApiException;
import com.mztalk.main.status.RoleStatus;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;


    @Override
    public Subscribe save(SubscribeRequestDto subscribeRequestDto) {

        Long own = subscribeRequestDto.getUserNo();

        System.out.println(own);

        //그 사람이 vip인지
        HttpHeaders headerName = new HttpHeaders();
        headerName.add("Content-type", "text/html");

        //유저의이름
        HttpHeaders headersNames = new HttpHeaders();
        headersNames.add("Content-type", "text/html");

        ResponseEntity<String> responseName = new RestTemplate().exchange(
                "http://localhost:8000/login/user-info/" + subscribeRequestDto.getUserNo(),
                HttpMethod.GET,
                new HttpEntity<String>(headerName),
                String.class
        );

        JSONObject ownName = new JSONObject(responseName.getBody());
        System.out.println(ownName);
        String role = ownName.getString("role");

        if(role.matches("ROLE_VIP")){
            System.out.println("?");
            new IllegalArgumentException("이미 VIP회원입니다.");
        }
        System.out.println(subscribeRequestDto.toEntity());
        System.out.println(subscribeRequestDto.toEntity().getUserNo()+"d");
        System.out.println(subscribeRequestDto.toEntity().getVipDate()+"f");

        return subscribeRepository.save(subscribeRequestDto.toEntity());
    }

    @Override
    public int updateStatusByUserNo(Long userNo) {

        int updatedCount = subscribeRepository.updateStatusByUserNo(userNo);

        if (updatedCount > 0) {
            return updatedCount;
        }
        return 0;
    }

    @Override
    public SubscribeResponseDto findByUserNoAndRoleStatus(Long userNo) {
        try {
                return subscribeRepository.findByUserNoAndRoleStatus(userNo, RoleStatus.valueOf("VIP"));
            }catch (Exception e){
                return null;
            }
        }



}
