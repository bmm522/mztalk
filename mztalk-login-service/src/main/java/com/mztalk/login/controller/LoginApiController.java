package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.service.NewAccessTokenService;
import com.mztalk.login.service.SelectUserInfoService;
import com.mztalk.login.service.UpdateUserInfoService;
import com.mztalk.login.service.impl.MailServiceByFindPwdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginApiController {

    private final UpdateUserInfoService updateUserInfoService;

    private final MailServiceByFindPwdService mailServiceByFindPwdService;

    private final SelectUserInfoService selectUserInfoService;

    private final NewAccessTokenService newAccessTokenService;

    @GetMapping("/auth-code")
    public ConcurrentHashMap<String, Object> getEmailAuthCodeByFindPwd(@RequestParam("email")String email, @RequestParam("username")String username){
        return mailServiceByFindPwdService.getEmailAuthCodeByFindPwd(email, username);
    }

    @PatchMapping("/password")
    public int updatePassword(@RequestBody Map<String, String> body){
        return updateUserInfoService.updatePassword(body.get("username"), body.get("password"));
    }

    @PatchMapping("/mentor-status/{nickname}")
    public int updateMentorStatus(@PathVariable("nickname")String nickname){
        return updateUserInfoService.updateMentorStatus(nickname);
    }

    @PatchMapping("/status/{nickname}")
    public int updateStatus(@PathVariable("nickname")String nickname){
        return updateUserInfoService.updateStatus(nickname);
    }

    @PatchMapping("/role/vip/{userNo}")
    public int updateRoleChangeToVip(@PathVariable("userNo")Long id){
        return updateUserInfoService.updateRoleChangeToVip(id);
    }

    @PatchMapping("/role/user/{userNo}")
    public int updateRoleChangeToUser(@PathVariable("userNo")Long id){
        return updateUserInfoService.updateRoleChangeToUser(id);
    }
    @GetMapping("user-info/{id}")
    public UserInfoDto getUserInfoByUserNo(@PathVariable("id")String id){
        return selectUserInfoService.getUserInfoByUserNo(id);
    }
    @GetMapping("user/{nickname}")
    public UserInfoDto getUserInfoBynickname(@PathVariable("nickname")String nickname){
        return selectUserInfoService.getUserInfoByNickname(nickname);
    }



    @GetMapping("/username/{email}")
    public ConcurrentHashMap<String, Object> searchUsername(@PathVariable("email") String email){
        return selectUserInfoService.searchUsername(email);
    }

    @GetMapping("/access-token")
    public ConcurrentHashMap<String, String> getNewAccessToken(@RequestParam("refreshToken")String refreshToken){
        System.out.println("newAccess : " + refreshToken);
        return newAccessTokenService.getNewAccessToken(refreshToken);
    }


    // 비밀번호 변경, body : prePassword, newPassword, id
    @PatchMapping("/new-password")
    public int changeNewPassword(@RequestBody Map<String, String> body){
        return updateUserInfoService.changeNewPassword(body);
    }


}
