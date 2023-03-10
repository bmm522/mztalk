package com.mztalk.loginservice.user.api;

import com.mztalk.loginservice.global.dto.ClientResponseDto;
import com.mztalk.loginservice.user.api.dto.ClientFirstSocialRequestDto;
import com.mztalk.loginservice.user.application.login.dto.response.JwtFirstSocialResponseDto;
import com.mztalk.loginservice.user.application.login.oauth.UpdateNicknameSocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;


@RequestMapping("/login/social")
@Controller
@RequiredArgsConstructor
@ApiIgnore
public class SocialController {


    private final UpdateNicknameSocialLoginService updateNicknameSocialLoginService;

    @GetMapping("/{social}")
    public String moveSocialLoginForm(@PathVariable("social") String social) throws IOException {
              return "redirect:/oauth2/authorization/"+social;
    }

    @ResponseBody
    @PatchMapping("/nickname")
    public ResponseEntity<?> setFirstLoginForSocialLoginUser(@RequestBody ClientFirstSocialRequestDto clientDto) throws IOException {
        JwtFirstSocialResponseDto serviceDto =  updateNicknameSocialLoginService.setFirstLoginForSocialLoginUser(clientDto);
        return new ResponseEntity<>(ClientResponseDto.builder().code(1).msg("첫 소셜로그인 토큰발급 설정 완료").body(serviceDto), HttpStatus.OK);
    }



}
