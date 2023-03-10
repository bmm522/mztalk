package com.mztalk.loginservice.user.application.register;

import com.mztalk.loginservice.user.application.register.dto.reqeust.ServiceRegisterReqeustDto;

public interface RegisterService {
    void registerUser(ServiceRegisterReqeustDto dto);
}
