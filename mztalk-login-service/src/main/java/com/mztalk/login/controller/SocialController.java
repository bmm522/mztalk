package com.mztalk.login.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@RequestMapping("/login/social")
@Controller
public class SocialController {

    @GetMapping("/setting")
    public void requestTest(@RequestHeader(value = "Authorization") String authorization){
        System.out.println("setting으로 들어옴 : "+ authorization);


    }



    @GetMapping("/google")
    public String moveGoogleLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
            System.out.println("google로그인 요청들어옴");
//        System.out.println(request);
 //       System.out.println(response);
  //      response.sendRedirect("https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=STATE_STRING&redirect_uri=http://localhost:8085/login/oauth2/code/google&client_id=768348317974-dc7t1s5kcgjonuqbiou2gt8jes16hfes.apps.googleusercontent.com");
//        String urlLink = "https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=STATE_STRING&redirect_uri=http://localhost:8085/login/oauth2/code/google&client_id=768348317974-dc7t1s5kcgjonuqbiou2gt8jes16hfes.apps.googleusercontent.com";
//        try {
//            Desktop.getDesktop().browse(new URI(urlLink));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
              return "redirect:/oauth2/authorization/google";
    }


}
