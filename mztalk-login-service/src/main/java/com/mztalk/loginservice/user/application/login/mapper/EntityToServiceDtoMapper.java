package com.mztalk.loginservice.user.application.login.mapper;

import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDto;
import com.mztalk.loginservice.user.repository.entity.User;

public class EntityToServiceDtoMapper {

    private static final EntityToServiceDtoMapper entityToServiceDtoMapper = new EntityToServiceDtoMapper();

    private EntityToServiceDtoMapper(){}

    public static EntityToServiceDtoMapper getInstance(){return entityToServiceDtoMapper;}

    public ServiceUserInfoResponseDto toUserInfoDto(String imageUrl, User user){
        return ServiceUserInfoResponseDto.builder()
                .userId(String.valueOf(user.getId()))
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .role(user.getRoleValue())
                .provider(user.getProviderValue())
                .providerId(user.getProviderId())
                .createDate(user.getCreateDate())
                .status(user.getStatus())
                .imageUrl(imageUrl)
                .reportCount(String.valueOf(user.getReportCount()))
                .build();
    }

    public ServiceUserInfoResponseDto toUserInfoDto(User user){
        return ServiceUserInfoResponseDto.builder()
                .userId(String.valueOf(user.getId()))
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .role(user.getRoleValue())
                .provider(user.getProviderValue())
                .providerId(user.getProviderId())
                .createDate(user.getCreateDate())
                .status(user.getStatus())
                .reportCount(String.valueOf(user.getReportCount()))
                .build();
    }
}
