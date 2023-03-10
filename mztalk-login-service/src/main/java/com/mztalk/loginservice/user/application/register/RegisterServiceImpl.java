package com.mztalk.loginservice.user.application.register;

import com.mztalk.loginservice.user.application.register.dto.reqeust.ServiceRegisterReqeustDto;
import com.mztalk.loginservice.user.application.register.mapper.ServiceDtoToEntityMapper;
import com.mztalk.loginservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ServiceDtoToEntityMapper mapper = ServiceDtoToEntityMapper.getInstance();

    @Override
    public void registerUser(ServiceRegisterReqeustDto dto) {
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userRepository.save(mapper.toEntityWhenRegister(dto));
    }



}
