package com.mztalk.loginservice.user.api.mapper;

import com.mztalk.loginservice.user.api.dto.*;
import com.mztalk.loginservice.user.application.login.dto.request.*;
import com.mztalk.loginservice.user.application.register.dto.reqeust.ServiceRegisterReqeustDto;
import com.mztalk.loginservice.user.repository.entity.util.Provider;
import com.mztalk.loginservice.user.repository.entity.util.Role;

public class ClientDtoToServiceDtoMapper {

    private static final ClientDtoToServiceDtoMapper clientDtoToServiceDtoMapper = new ClientDtoToServiceDtoMapper();

    private ClientDtoToServiceDtoMapper(){}

    public static ClientDtoToServiceDtoMapper getInstance(){
        return clientDtoToServiceDtoMapper;
    }

    public ServiceRegisterReqeustDto toServiceDtoWhenRegister(ClientRegisterReqeustDto clientRegisterReqeustDto){
        return ServiceRegisterReqeustDto.builder()
                .username(clientRegisterReqeustDto.getUserId())
                .password(clientRegisterReqeustDto.getPassword())
                .nickname(clientRegisterReqeustDto.getNickname())
                .email(clientRegisterReqeustDto.getEmail())
                .role(Role.ROLE_USER)
                .provider(Provider.LOCAL)
                .providerId("NULL")
                .status("Y")
                .reportCount(0L)
                .build();
    }

    public ServiceEmailAuthRequestDto toServiceDtoWhenEamilAuth(String email, String username){
        return ServiceEmailAuthRequestDto.builder()
                .email(email)
                .username(username)
                .build();
    }

    public ServiceUpdatePasswordRequestDto toServiceDtoWhenUpdatePassword(ClientUpdatePasswordRequestDto dto){
        return ServiceUpdatePasswordRequestDto.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public ServiceChangeNewPasswordRequestDto toServiceDtoWhenChangeNewPassword(ClientChangeNewPasswordReqeustDto dto){
        return ServiceChangeNewPasswordRequestDto.builder()
                .id(dto.getId())
                .prePassword(dto.getPrePassword())
                .newPassword(dto.getNewPassword())
                .build();
    }

    public ServiceChangeNewNicknameRequestDto toServiceDtoWhenChangeNewNickname(ClientChangeNewNicknameRequestDto dto){
        return ServiceChangeNewNicknameRequestDto.builder()
                .userNo(dto.getUserNo())
                .nickname(dto.getNickname())
                .build();
    }


    public ServiceChangeNewEmailReqeustDto toServiceDtoWhenChangeNewEamil(ClientChangeNewEmailRequestDto dto) {
        return ServiceChangeNewEmailReqeustDto.builder()
                .id(dto.getUserNo())
                .email(dto.getEmail())
                .build();
    }

    public ServiceUpdateStatusRequestDto toServiceDtoWhenUpdateStatus(long id, String status) {
        return ServiceUpdateStatusRequestDto.builder()
                .id(id)
                .status(status)
                .build();
    }
}
