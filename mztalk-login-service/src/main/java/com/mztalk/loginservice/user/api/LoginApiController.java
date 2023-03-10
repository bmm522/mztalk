package com.mztalk.loginservice.user.api;

import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDto;
import com.mztalk.loginservice.user.api.dto.ClientChangeNewEmailRequestDto;
import com.mztalk.loginservice.user.api.dto.ClientChangeNewNicknameRequestDto;
import com.mztalk.loginservice.user.api.dto.ClientChangeNewPasswordReqeustDto;
import com.mztalk.loginservice.global.dto.ClientResponseDto;
import com.mztalk.loginservice.user.api.dto.ClientUpdatePasswordRequestDto;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceEmailAuthResponseDto;
import com.mztalk.loginservice.user.application.login.dto.response.JwtResponseDto;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceSearchUsernameResponseDto;
import com.mztalk.loginservice.user.api.mapper.ClientDtoToServiceDtoMapper;
import com.mztalk.loginservice.user.application.login.dto.request.*;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDtos;
import com.mztalk.loginservice.user.application.login.NewAccessTokenService;
import com.mztalk.loginservice.user.application.login.SelectUserInfoService;
import com.mztalk.loginservice.user.application.login.UpdateUserInfoService;
import com.mztalk.loginservice.user.application.login.MailServiceByFindPwdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Api(tags = "login-api")
public class LoginApiController {

    private final UpdateUserInfoService updateUserInfoService;

    private final MailServiceByFindPwdService mailServiceByFindPwdService;

    private final SelectUserInfoService selectUserInfoService;

    private final NewAccessTokenService newAccessTokenService;

    private final ClientDtoToServiceDtoMapper mapper = ClientDtoToServiceDtoMapper.getInstance();

    @GetMapping("/auth-code")
    @ApiIgnore
    public ResponseEntity<?> getEmailAuthCodeByFindPwd(@RequestParam("email")String email,
                                                                 @RequestParam("username")String username){
        ServiceEmailAuthRequestDto requestDto = mapper.toServiceDtoWhenEamilAuth(email, username);
        ServiceEmailAuthResponseDto responseDto = mailServiceByFindPwdService.getEmailAuthCodeByFindPwd(requestDto);
         return new ResponseEntity<>(success("이메일로 인증번호 전송 성공", responseDto), HttpStatus.OK);
    }

    @PatchMapping("/password")
    @ApiIgnore
    public ResponseEntity<?> updatePassword(@RequestBody ClientUpdatePasswordRequestDto clientDto){
        ServiceUpdatePasswordRequestDto serviceDto = mapper.toServiceDtoWhenUpdatePassword(clientDto);
        updateUserInfoService.updatePassword(serviceDto);
        return new ResponseEntity<>(updateSuccess("비밀번호 변경 성공"), HttpStatus.OK);
    }

    @ApiOperation(value = "닉네임으로 상태값 변경", notes = "해당 닉네임의 유저의 status를 N으로 변경합니다.")
    @PatchMapping("/status/{nickname}")
    public ResponseEntity<?> updateStatus(@PathVariable("nickname")String nickname){
        updateUserInfoService.updateStatus(nickname);
        return new ResponseEntity<>(updateSuccess("닉네임으로 status 변경 성공"), HttpStatus.OK);
    }

    @ApiOperation(value="유저 등급 vip로 변경", notes = "해당 번호의 유저의 등급을 vip로 변경합니다.")
    @PatchMapping("/role/vip/{userNo}")
    public ResponseEntity<?> updateRoleChangeToVip(@PathVariable("userNo")Long id){
        updateUserInfoService.updateRoleChangeToVip(id);
        return new ResponseEntity<>(updateSuccess("번호로 유저의 등급 vip로 변경 성공"),HttpStatus.OK);
    }

    @ApiOperation(value="유저 등급 일반으로 변경", notes = "해당 번호의 유저의 등급을 일반등급으로 변경합니다.")
    @PatchMapping("/role/user/{userNo}")
    public ResponseEntity<?> updateRoleChangeToUser(@PathVariable("userNo")Long id){
        updateUserInfoService.updateRoleChangeToUser(id);
        return new ResponseEntity<>(updateSuccess("번호로 유저의 등급 일반으로 변경 성공"),HttpStatus.OK);
    }

    @ApiOperation(value="유저 정보 가져오기", notes = "해당 번호의 유저의 정보를 가져옵니다.")
    @GetMapping("user-info/{id}")
    public ResponseEntity<?> getUserInfoByUserNo(@PathVariable("id")String id){
        ServiceUserInfoResponseDto dto =  selectUserInfoService.getUserInfoByUserNo(id);
        return new ResponseEntity<>(success("유저 정보 가져오기 성공", dto), HttpStatus.OK);
    }

    @ApiOperation(value = "이메일로 아이디 찾기", notes = "해당 이메일로 등록된 유저의 아이디를 가져옵니다.")
    @GetMapping("/username/{email}")
    public ResponseEntity<?> searchUsername(@PathVariable("email") String email){
        ServiceSearchUsernameResponseDto dto =  selectUserInfoService.searchUsername(email);
        return new ResponseEntity<>(success("이메일로 등록된 유저의 아이디 가져오기 성공", dto), HttpStatus.OK);
    }


    // 비밀번호 변경, body : prePassword, newPassword, id
    @ApiOperation(value = "새로운 비밀번호로 변경", notes = "기존의 비밀번호를 새로운 비밀번호로 변경합니다.")
    @PatchMapping("/new-password")
    public ResponseEntity<?> changeNewPassword(@RequestBody ClientChangeNewPasswordReqeustDto clientDto){
        ServiceChangeNewPasswordRequestDto serviceDto = mapper.toServiceDtoWhenChangeNewPassword(clientDto);
        updateUserInfoService.changeNewPassword(serviceDto);
        return new ResponseEntity<>(updateSuccess("비밀번호 변경 성공"), HttpStatus.OK);
    }

    @ApiOperation(value = "닉네임 변경", notes = "해당 유저 번호의 유저의 닉네임을 변경합니다.")
    @PatchMapping("/user/nickname")
    public ResponseEntity<?> changeNewNickname(@RequestBody ClientChangeNewNicknameRequestDto clientDto){
        ServiceChangeNewNicknameRequestDto serviceDto = mapper.toServiceDtoWhenChangeNewNickname(clientDto);
        updateUserInfoService.changeNewNickname(serviceDto);
        return new ResponseEntity<>(updateSuccess("번호로 유저의 닉네임 변경 성공"),HttpStatus.OK);
    }

    @ApiOperation(value = "이메일 변경", notes = "해당 유저 번호의 유저 이메일을 변경합니다.")
    @PatchMapping("/user/email")
    public ResponseEntity<?> changeNewEmail(@RequestBody ClientChangeNewEmailRequestDto clientDto){
        ServiceChangeNewEmailReqeustDto serviceDto = mapper.toServiceDtoWhenChangeNewEamil(clientDto);
        updateUserInfoService.changeNewEmail(serviceDto);
        return new ResponseEntity<>(updateSuccess("번호로 유저의 이메일 변경 성공"),HttpStatus.OK);
    }

    @ApiOperation(value = "악성 유저 리스트 가져오기", notes = "신고 횟수가 3회 이상인 악성 유저의 리스트를 가져옵니다.")
    @GetMapping("/malicious-user")
    public ResponseEntity<?> getMaliciousUser(){
        ServiceUserInfoResponseDtos dtos =  selectUserInfoService.getMaliciousUser();
        return new ResponseEntity<>(success("악성 유저 리스트 가져오기 성공", dtos), HttpStatus.OK);
    }

    @ApiOperation(value = "유저 상태 변경", notes = "해당 유저의 status를 변경합니다.")
    @PatchMapping("/user/status")
    public ResponseEntity<?> updateUserStatus(@RequestParam("status")String status,
                                              @RequestParam("userNo")long id){
        ServiceUpdateStatusRequestDto serviceDto = mapper.toServiceDtoWhenUpdateStatus(id, status);
        updateUserInfoService.updateUserStatus(serviceDto);
        return new ResponseEntity<>(updateSuccess("번호로 유저의 status 변경 성공"),HttpStatus.OK);
    }

    @ApiOperation(value = "토큰 재발급", notes = "토큰 유효시간이 끝났을 시, 리프레시 토큰을 이용해서 재발급해줍니다.")
    @GetMapping("/access-token")
    @ApiIgnore
    public ResponseEntity<?> getNewAccessToken(@RequestParam("refreshToken")String refreshToken){
        JwtResponseDto dto =  newAccessTokenService.getNewAccessToken(refreshToken);
        return new ResponseEntity<>(success("토큰 재발급 성공", dto), HttpStatus.OK);

    }


    private ClientResponseDto<?> success(String msg, Object dto){

        return ClientResponseDto.builder()
                .code(1)
                .msg(msg)
                .body(dto)
                .build();
    }

    private ClientResponseDto<?> updateSuccess(String msg){

        return ClientResponseDto.builder()
                .code(1)
                .msg(msg)
                .build();
    }
}
