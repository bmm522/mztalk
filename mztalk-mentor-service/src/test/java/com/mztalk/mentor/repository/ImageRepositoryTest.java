//package com.mztalk.mentor.repository;
//
//import com.mztalk.mentor.domain.entity.Image;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class ImageRepositoryTest {
//    @Autowired
//    private ImageRepository imageRepository;
//
//    @Test
//    public void saveTest() throws Exception {
//        //given
//        String uuid = UUID.randomUUID().toString();
//        Image image1 = Image.builder().storeFileName(uuid).build();
//        Image image2 = Image.builder().storeFileName("uuid").build();
//
//        //when
//        Image savedImage1 = imageRepository.save(image1);
//        Image savedImage2 = imageRepository.save(image2);
//
//        //then
//        assertThat(image1).isEqualTo(savedImage1);
//        assertThat(image2).isEqualTo(savedImage2);
//        assertThat(savedImage1.getStoreFileName()).isEqualTo(uuid);
//        assertThat(savedImage2.getStoreFileName()).isEqualTo("uuid");
//    }
//
//    @Test
//    public void findTest() throws Exception {
//        //given
//        Image image1 = Image.builder().build();
//        Image image2 = Image.builder().build();
//
//        imageRepository.save(image1);
//        imageRepository.save(image2);
//
//        //when
//        Image findImage1 = imageRepository.findById(image1.getId()).get();
//        Image findImage2 = imageRepository.findById(image2.getId()).get();
//
//        //then
//        assertThat(image1).isEqualTo(findImage1);
//        assertThat(image2).isEqualTo(findImage2);
//        assertThat(image1.getId()).isEqualTo(3L);
//        assertThat(image2.getId()).isEqualTo(4L);
//    }
//
//    @Test
//    public void findAllTest() throws Exception {
//        //given
//        Image image1 = Image.builder().build();
//        Image image2 = Image.builder().build();
//
//        imageRepository.save(image1);
//        imageRepository.save(image2);
//
//        //when
//        List<Image> imageList = imageRepository.findAll();
//
//        //then
//        assertThat(imageList.size()).isEqualTo(2);
//        assertThat(imageList.get(0)).isEqualTo(image1);
//        assertThat(imageList.get(1)).isEqualTo(image2);
//        assertThat(imageList.get(0).getId()).isSameAs(image1.getId());
//    }
//
//}