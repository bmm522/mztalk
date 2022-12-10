package com.mztalk.login.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@RequestMapping("/login/social")
@Controller
public class SocialController {

    @GetMapping("/{social}")
    public String moveGoogleLoginForm(@PathVariable("social") String social) throws IOException {
              return "redirect:/oauth2/authorization/"+social;
    }



}
