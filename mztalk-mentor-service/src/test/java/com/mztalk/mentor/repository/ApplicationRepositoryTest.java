package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        Application application1 = new Application();
        Application application2 = new Application();

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
        Application application = new Application();
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
        Application application1 = new Application();
        Application application2 = new Application();

        application1.setName("memberA");
        application2.setName("memberB");
        application1.setBank("신한은행");
        application2.setBank("국민은행");

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