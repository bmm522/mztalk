package com.mztalk.loginservice.user.application.login;

import com.mztalk.loginservice.user.application.login.dto.response.ServiceEmailAuthResponseDto;
import com.mztalk.loginservice.user.application.login.dto.request.ServiceEmailAuthRequestDto;
import com.mztalk.loginservice.user.repository.entity.User;
import com.mztalk.loginservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailServiceByFindPwdService {

    private final MailService mailService;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public ServiceEmailAuthResponseDto getEmailAuthCodeByFindPwd(ServiceEmailAuthRequestDto dto) {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(dto.getUsername()))
                .orElseThrow(()->new RuntimeException("Not Exists Username"));


        if(userOptional.get().getEmail().equals(dto.getEmail())){
            return mailService.getAuthCodeOfEmail(dto.getEmail());
        }

        throw new RuntimeException("Fail Send Email-AuthCode");
    }
}
