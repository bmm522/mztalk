package com.mztalk.loginservice.user.application.register.mapper;

import com.mztalk.loginservice.user.application.register.dto.reqeust.ServiceRegisterReqeustDto;
import com.mztalk.loginservice.user.repository.entity.User;

public class ServiceDtoToEntityMapper {

    private static final ServiceDtoToEntityMapper serviceDtoTiEntityMapper = new ServiceDtoToEntityMapper();

    private ServiceDtoToEntityMapper(){}

    public static ServiceDtoToEntityMapper getInstance(){
        return serviceDtoTiEntityMapper;
    }

    public User toEntityWhenRegister(ServiceRegisterReqeustDto dto){
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .role(dto.getRole())
                .provider(dto.getProvider())
                .providerId(dto.getProviderId())
                .status(dto.getStatus())
                .reportCount(dto.getReportCount())
                .build();
    }
}
