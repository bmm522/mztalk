package com.mztalk.login.service.impl;

import com.mztalk.login.domain.entity.User;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.SearchUsernameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SearchUsernameServiceImpl implements SearchUsernameService {

    private final UserRepository userRepository;
    @Override
    public ConcurrentHashMap<String, Object> searchUsername(String email) {
        User user = userRepository.findByEmail(email);
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

        if(user == null){
            map.put("result", "notExist");
            return map;
        }
        map.put("result", user.getUsername());
        return map;
    }
}
