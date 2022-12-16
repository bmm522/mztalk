package com.mztalk.login.service.impl;

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
    public int updatePassword(String username, String password) {
       return userRepository.updatePassword(username, bCryptPasswordEncoder.encode(password));
    }

    @Override
    public int updateMentorStatus(String nickname) {
        return userRepository.updateMentorStatus(nickname);
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
    public int changeNewPassword(Map<String, String> body) {
//        System.out.println(body);
//        System.out.println(bCryptPasswordEncoder.encode(body.get("prePassword")));
//        System.out.println(bCryptPasswordEncoder.encode(body.get("newPassword")));
//        userRepository.findByPasswordWithId(Long.parseLong(body.get("id")));
        System.out.println("결과 : " + bCryptPasswordEncoder.matches(userRepository.findByPasswordWithId(Long.parseLong(body.get("id"))), body.get("prePassword")));
        if(bCryptPasswordEncoder.matches(body.get("prePassword"),userRepository.findByPasswordWithId(Long.parseLong(body.get("id"))))){
            try {

                return userRepository.changedPassword(bCryptPasswordEncoder.encode(body.get("newPassword")),Long.parseLong(body.get("id")));
            } catch (RuntimeException e){
                new ChangeFailException("기존 비밀번호 정보가 일치하지 않습니다.");
                return 0;
            }

        }
        return 0;
    }


}
