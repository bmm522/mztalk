package com.mztalk.login.config;

import com.mztalk.login.oauth.SocialLoginSuccessHandler;
import com.mztalk.login.oauth.PrincipalSocialOAuth2UserService;
import com.mztalk.login.auth.LoginAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    private String successUrl = "http://127.0.0.1:5501/main.html";
    @Autowired
   private PrincipalSocialOAuth2UserService principalSocialOAuth2UserService;


    @Autowired
    private SocialLoginSuccessHandler socialLoginSuccessHandler;

    private final CorsFilter corsFilter;
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
               .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new LoginAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalSocialOAuth2UserService)
                .and()
                .successHandler(socialLoginSuccessHandler);

    }


}
