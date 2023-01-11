package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.dto.response.EmailAuthResponseDto;
import com.mztalk.login.domain.dto.response.SearchUsernameResponseDto;
import com.mztalk.login.service.NewAccessTokenService;
import com.mztalk.login.service.SelectUserInfoService;
import com.mztalk.login.service.UpdateUserInfoService;
import com.mztalk.login.service.impl.MailServiceByFindPwdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Api(tags = "login-api")
public class LoginApiController {

    private final UpdateUserInfoService updateUserInfoService;

    private final MailServiceByFindPwdService mailServiceByFindPwdService;

    private final SelectUserInfoService selectUserInfoService;

    private final NewAccessTokenService newAccessTokenService;


    @GetMapping("/auth-code")
    @ApiIgnore
    public EmailAuthResponseDto getEmailAuthCodeByFindPwd(@RequestParam("email")String email, @RequestParam("username")String username){
        return mailServiceByFindPwdService.getEmailAuthCodeByFindPwd(email, username);
    }

    @PatchMapping("/password")
    @ApiIgnore
    public int updatePassword(@RequestBody Map<String, String> body){
        return updateUserInfoService.updatePassword(body.get("username"), body.get("password"));
    }

    @ApiOperation(value = "닉네임으로 상태값 변경", notes = "해당 닉네임의 유저의 status를 N으로 변경합니다.")
    @PatchMapping("/status/{nickname}")
    public int updateStatus(@PathVariable("nickname")String nickname){
        return updateUserInfoService.updateStatus(nickname);
    }

    @ApiOperation(value="유저 등급 vip로 변경", notes = "해당 번호의 유저의 등급을 vip로 변경합니다.")
    @PatchMapping("/role/vip/{userNo}")
    public int updateRoleChangeToVip(@PathVariable("userNo")Long id){
        return updateUserInfoService.updateRoleChangeToVip(id);
    }

    @ApiOperation(value="유저 등급 일반으로 변경", notes = "해당 번호의 유저의 등급을 일반등급으로 변경합니다.")
    @PatchMapping("/role/user/{userNo}")
    public int updateRoleChangeToUser(@PathVariable("userNo")Long id){
        return updateUserInfoService.updateRoleChangeToUser(id);
    }

    @ApiOperation(value="유저 정보 가져오기", notes = "해당 번호의 유저의 정보를 가져옵니다.")
    @GetMapping("user-info/{id}")
    public UserInfoDto getUserInfoByUserNo(@PathVariable("id")String id){
        return selectUserInfoService.getUserInfoByUserNo(id);
    }

    @GetMapping("/username/{email}")
    @ApiIgnore
    public SearchUsernameResponseDto searchUsername(@PathVariable("email") String email){
        return selectUserInfoService.searchUsername(email);
    }

    @GetMapping("/access-token")
    @ApiIgnore
    public ConcurrentHashMap<String, String> getNewAccessToken(@RequestParam("refreshToken")String refreshToken){
        return newAccessTokenService.getNewAccessToken(refreshToken);
    }


    // 비밀번호 변경, body : prePassword, newPassword, id
    @ApiOperation(value = "새로운 비밀번호로 변경", notes = "기존의 비밀번호를 새로운 비밀번호로 변경합니다.")
    @PatchMapping("/new-password")
    public int changeNewPassword(@RequestBody Map<String, String> body){
        return updateUserInfoService.changeNewPassword(body);
    }

    @PatchMapping("/user/nickname")
    public int changeNewNickname(@RequestBody Map<String, String> body){
        return updateUserInfoService.changeNewNickname(body);
    }

    @PatchMapping("/user/email")
    public int changeNewEmail(@RequestBody Map<String, String> body){
        return updateUserInfoService.changeNewEmail(body.get("userNo"), body.get("email"));
    }

    @GetMapping("/malicious-user")
    @ApiIgnore
    public Result<?> getMaliciousUser(){
        return selectUserInfoService.getMaliciousUser();
    }

    @PatchMapping("/user/status")
    public long updateUserStatus(@RequestParam("status")String status, @RequestParam("userNo")long id){
        return updateUserInfoService.updateUserStatus(status, id);
    }


}
