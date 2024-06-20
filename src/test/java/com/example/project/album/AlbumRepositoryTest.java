package com.example.project.album;

import com.example.project._core.enums.AlbumEnum;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findBySocialId_test(){
        // given
        Integer socialId = 1;

        // when
        List<Album> album = albumRepository.findBySocialId(socialId);

        // eye
        System.out.println("album/first/id : " + album.getFirst().getId());

        // then
        Assertions.assertThat(album.getFirst().getId()).isEqualTo(1);
        Assertions.assertThat(album.getFirst().getType()).isEqualTo(AlbumEnum.IMAGE);
    }
}
