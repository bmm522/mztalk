package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.request.ChangeNewPasswordReqeustDto;
import com.mztalk.login.domain.dto.request.UpdatePasswordRequestDto;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.exception.ChangeFailException;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.UpdateUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class UpdateUserInfoServiceImpl implements UpdateUserInfoService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
       return userRepository.updatePassword(updatePasswordRequestDto.getUsername(), bCryptPasswordEncoder.encode(updatePasswordRequestDto.getPassword()));
    }

    @Override
    public int updateStatus(String nickname) {
        return userRepository.updateStatus(nickname);
    }

    @Override
    public int updateRoleChangeToVip(Long id) {
        return userRepository.updateRoleChangeToVip(id);
    }

    @Override
    public int updateRoleChangeToUser(Long id) {
        return userRepository.updateRoleChangeToUser(id);
    }

    @Override
    public int changeNewPassword(ChangeNewPasswordReqeustDto changeNewPasswordReqeustDto) {

        if(bCryptPasswordEncoder.matches(changeNewPasswordReqeustDto.getPrePassword(),userRepository.findByPasswordWithId(Long.parseLong(changeNewPasswordReqeustDto.getNewPassword())))){
            try {
                return userRepository.changedPassword(bCryptPasswordEncoder.encode(changeNewPasswordReqeustDto.getNewPassword()),Long.parseLong(changeNewPasswordReqeustDto.getId()));
            } catch (RuntimeException e){
                new ChangeFailException("기존 비밀번호 정보가 일치하지 않습니다.");
                return 0;
            }

        }
        return 0;
    }

    @Override
    public int changeNewNickname(Map<String, String> body) {
        return userRepository.updateNickname(Long.parseLong(body.get("userNo")),body.get("nickname"));
    }

    @Override
    public int changeNewEmail(String userNo, String email) {
        return userRepository.updateEmail(Long.parseLong(userNo), email);
    }

    @Override
    public long updateUserStatus(String status, long id) {
        return userRepository.updateUserStatus(status, id);
    }


}
