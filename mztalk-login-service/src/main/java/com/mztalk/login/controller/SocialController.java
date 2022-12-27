package com.mztalk.login.controller;

import com.mztalk.login.oauth.UpdateNicknameSocialLoginService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


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
    public ConcurrentHashMap<String, String> setFirstLoginForSocialLoginUser(@RequestBody Map<String, String> body) throws IOException {
        return updateNicknameSocialLoginService.setFirstLoginForSocialLoginUser(body);
    }



}
