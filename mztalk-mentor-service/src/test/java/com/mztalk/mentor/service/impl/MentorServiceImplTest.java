//package com.mztalk.mentor.service.impl;
//
//import com.mztalk.mentor.domain.Status;
//import com.mztalk.mentor.domain.dto.MentorDto;
//import com.mztalk.mentor.domain.entity.Mentor;
//import com.mztalk.mentor.domain.entity.Result;
//import com.mztalk.mentor.repository.MentorRepository;
//import com.mztalk.mentor.service.MentorService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class MentorServiceImplTest {
//
//    @Autowired
//    private MentorService mentorService;
//    @Autowired
//    private MentorRepository mentorRepository;
//
//    @Test
//    public void save() throws Exception {
//        //given
//        MentorDto mentorDto1 = new MentorDto();
//        MentorDto mentorDto2 = new MentorDto();
//
//        //when
//        Long savedId1 = mentorService.save(mentorDto1);
//        Long savedId2 = mentorService.save(mentorDto2);
//
//        //then
//        assertThat(1L).isEqualTo(savedId1);
//        assertThat(2L).isEqualTo(savedId2);
//    }
//
//    @Test
//    public void findById() throws Exception {
//        //given
//        MentorDto mentorDto1 = new MentorDto();
//        MentorDto mentorDto2 = new MentorDto();
//
//        //when
//        Long savedId1 = mentorService.save(mentorDto1);
//        Long savedId2 = mentorService.save(mentorDto2);
//
//        MentorDto findMentorDto1 = mentorService.findById(savedId1);
//        MentorDto findMentorDto2 = mentorService.findById(savedId2);
//
//
//        //then
//        assertThat(1L).isEqualTo(savedId1);
//        assertThat(2L).isEqualTo(savedId2);
////        assertThat(savedId1).isEqualTo(findMentorDto1.getId());
////        assertThat(savedId2).isEqualTo(findMentorDto2.getId());
////    }
//
//    @Test
//    public void findAll() throws Exception {
//        //given
//        MentorDto mentorDto1 = new MentorDto();
//        MentorDto mentorDto2 = new MentorDto();
//
//        //when
//        Long savedId1 = mentorService.save(mentorDto1);
//        Long savedId2 = mentorService.save(mentorDto2);
//
//        List<Mentor> list = mentorRepository.findAll();
//        //then
//        assertThat(list.size()).isEqualTo(2);
//        assertThat(list.get(0).getId()).isEqualTo(savedId1);
//        assertThat(list.get(1).getId()).isEqualTo(savedId2);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        //given
//        MentorDto mentorDto1 = new MentorDto();
//        MentorDto mentorDto2 = new MentorDto();
//
//        //when
//        Long savedId1 = mentorService.save(mentorDto1);
//        Long savedId2 = mentorService.save(mentorDto2);
//
//        Mentor mentor1 = mentorRepository.findById(savedId1).get();
//        Mentor mentor2 = mentorRepository.findById(savedId2).get();
//
//        Long deletedId1 = mentorService.delete(savedId1);
//        Long deletedId2 = mentorService.delete(savedId2);
//        //then
//        assertThat(savedId1).isEqualTo(deletedId1);
//        assertThat(savedId2).isEqualTo(deletedId2);
//        assertThat(mentor1.getStatus()).isEqualTo(Status.NO);
//        assertThat(mentor2.getStatus()).isEqualTo(Status.NO);
//    }
//
//
//
//}