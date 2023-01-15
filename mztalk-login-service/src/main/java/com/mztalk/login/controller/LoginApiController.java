package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.dto.request.ChangeNewEmailRequestDto;
import com.mztalk.login.domain.dto.request.ChangeNewNicknameRequestDto;
import com.mztalk.login.domain.dto.request.ChangeNewPasswordReqeustDto;
import com.mztalk.login.domain.dto.request.UpdatePasswordRequestDto;
import com.mztalk.login.domain.dto.response.EmailAuthResponseDto;
import com.mztalk.login.domain.dto.response.JwtResponseDto;
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
    public int updatePassword(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto){
        return updateUserInfoService.updatePassword(updatePasswordRequestDto);
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

    @ApiOperation(value = "이메일로 아이디 찾기", notes = "해당 이메일로 등록된 유저의 아이디를 가져옵니다.")
    @GetMapping("/username/{email}")
    public SearchUsernameResponseDto searchUsername(@PathVariable("email") String email){
        return selectUserInfoService.searchUsername(email);
    }

    @ApiOperation(value = "토큰 재발급", notes = "토큰 유효시간이 끝났을 시, 리프레시 토큰을 이용해서 재발급해줍니다.")
    @GetMapping("/access-token")
    @ApiIgnore
    public JwtResponseDto getNewAccessToken(@RequestParam("refreshToken")String refreshToken){
        return newAccessTokenService.getNewAccessToken(refreshToken);
    }


    // 비밀번호 변경, body : prePassword, newPassword, id
    @ApiOperation(value = "새로운 비밀번호로 변경", notes = "기존의 비밀번호를 새로운 비밀번호로 변경합니다.")
    @PatchMapping("/new-password")
    public int changeNewPassword(@RequestBody ChangeNewPasswordReqeustDto changeNewPasswordReqeustDto){
        return updateUserInfoService.changeNewPassword(changeNewPasswordReqeustDto);
    }

    @PatchMapping("/user/nickname")
    public int changeNewNickname(@RequestBody ChangeNewNicknameRequestDto changeNewNicknameRequestDto){
        return updateUserInfoService.changeNewNickname(changeNewNicknameRequestDto);
    }

    @PatchMapping("/user/email")
    public int changeNewEmail(@RequestBody ChangeNewEmailRequestDto changeNewEmailRequestDto){
        return updateUserInfoService.changeNewEmail(changeNewEmailRequestDto.getUserNo(),changeNewEmailRequestDto.getEmail());
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
