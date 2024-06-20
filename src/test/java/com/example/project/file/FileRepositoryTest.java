package com.example.project.file;

import com.example.project._core.enums.AlbumEnum;
import com.example.project.album.Album;
import com.example.project.album.AlbumRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findBySocialId_test(){
        // given
        Integer socialId = 1;

        // when
        List<File> files = fileRepository.findBySocialId(socialId);

        // eye
        System.out.println("files/first/id : " + files.getFirst().getId());

        // then
        Assertions.assertThat(files.getFirst().getId()).isEqualTo(1);
        Assertions.assertThat(files.getFirst().getName()).isEqualTo("file1");
    }
}
