package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;

    @Test
    public void saveTest() throws Exception {
        //given
        String uuid = UUID.randomUUID().toString();
        File file1 = new File();
        File file2 = new File();

        file1.setStoreFileName(uuid);
        file2.setStoreFileName("uuid");
        //when
        File savedFile1 = fileRepository.save(file1);
        File savedFile2 = fileRepository.save(file2);

        //then
        assertThat(file1).isEqualTo(savedFile1);
        assertThat(file2).isEqualTo(savedFile2);
        assertThat(savedFile1.getStoreFileName()).isEqualTo(uuid);
        assertThat(savedFile2.getStoreFileName()).isEqualTo("uuid");
    }

    @Test
    public void findTest() throws Exception {
        //given
        File file1 = new File();
        File file2 = new File();

        fileRepository.save(file1);
        fileRepository.save(file2);
        //when
        File findFile1 = fileRepository.findById(file1.getId()).get();
        File findFile2 = fileRepository.findById(file2.getId()).get();

        //then
        assertThat(file1).isEqualTo(findFile1);
        assertThat(file2).isEqualTo(findFile2);
        assertThat(file1.getId()).isEqualTo(3L);
        assertThat(file2.getId()).isEqualTo(4L);
    }

    @Test
    public void findAllTest() throws Exception {
        //given
        File file1 = new File();
        File file2 = new File();

        fileRepository.save(file1);
        fileRepository.save(file2);

        //when
        List<File> fileList = fileRepository.findAll();
        //then
        assertThat(fileList.size()).isEqualTo(2);
        assertThat(fileList.get(0)).isEqualTo(file1);
        assertThat(fileList.get(1)).isEqualTo(file2);
        assertThat(fileList.get(0).getId()).isSameAs(file1.getId());

    }

}