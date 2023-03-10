package com.mztalk.loginservice.user.application.login.auth;

import com.mztalk.loginservice.user.repository.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;



    private Map<String, Object> userInfoMap;

    public PrincipalDetails(User user){
        this.user = user;
    }

    public PrincipalDetails(User user, Map<String, Object> userInfoMap){
        this.user = user;
        this.userInfoMap = userInfoMap;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return userInfoMap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorityCollection = new ArrayList<GrantedAuthority>();

        grantedAuthorityCollection.add(
                new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return user.getRoleValue();
                    }
                });
        return grantedAuthorityCollection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
