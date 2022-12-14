package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.exception.ApplicationNotFoundException;
import com.mztalk.mentor.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@Transactional(readOnly = true)
@SpringBootTest
class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationRepository applicationRepository;

//    @Test
//    @Transactional
//    public void saveTest() throws Exception {
//        //given
//        ApplicationDto applicationDto = new ApplicationDto();
//        applicationDto.setBank("국민은행");
//        applicationDto.setAccount("1111");
//
//        Application application = Application.builder().
//                bank(applicationDto.getBank()).
//                account(applicationDto.getAccount()).
//                build();
//
//        //when
//        Application savedApplication = applicationRepository.save(application);
//        Long savedId = savedApplication.getId();
//
//        //then
//        assertThat(savedId).isEqualTo(application.getId());
//        assertThat(savedApplication.getBank()).isEqualTo(applicationDto.getBank());
//        assertThat(savedApplication.getAccount()).isEqualTo(applicationDto.getAccount());
//    }
//
//    @Test
//    public void findTest() throws Exception {
//        //given
//        ApplicationDto applicationDto = new ApplicationDto();
//        applicationDto.setName("mentorA");
//
//        applicationService.save(applicationDto);
//        //when
//        Optional<Application> savedApplication = applicationRepository.findById(1L);
//        Application application = savedApplication.orElseThrow(ApplicationNotFoundException::new);
//        //then
//        assertThat(1L).isEqualTo(application.getId());
//        assertThat(applicationDto.getName()).isEqualTo(application.getName());
//    }



}