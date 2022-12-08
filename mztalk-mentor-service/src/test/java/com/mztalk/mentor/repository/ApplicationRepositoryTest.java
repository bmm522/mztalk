package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void saveTest() throws Exception {
        //given
        Application application1 = Application.builder().build();
        Application application2 = Application.builder().build();

        //when
        Application savedMentor1 = applicationRepository.save(application1);
        Application savedMentor2 = applicationRepository.save(application2);

        //then
        assertThat(application1).isEqualTo(savedMentor1);
        assertThat(application2).isEqualTo(savedMentor2);
    }

    @Test
    public void findTest() throws Exception {
        //given
        Application application = Application.builder().build();
        applicationRepository.save(application);

        //when
        Optional<Application> optionalApplication = applicationRepository.findById(application.getId());
        Application findApplication = optionalApplication.get();
        //then
        assertThat(application).isEqualTo(findApplication);
    }

    @Test
    public void findAllTest() throws Exception {
        //given
        Application application1 = Application.builder().name("memberA").bank("신한은행").build();
        Application application2 = Application.builder().name("memberB").bank("국민은행").build();

        applicationRepository.save(application1);
        applicationRepository.save(application2);

        //when
        List<Application> appList = applicationRepository.findAll();
        Application findApp = appList.get(0);

        //then
        assertThat(appList.size()).isEqualTo(2);
        assertThat(appList.get(0).getName()).isEqualTo("memberA");
        assertThat(appList.get(1).getName()).isEqualTo("memberB");
        assertThat(appList.get(0).getBank()).isEqualTo("신한은행");
        assertThat(appList.get(1).getBank()).isEqualTo("국민은행");
        assertThat(findApp).isEqualTo(application1);

    }

}