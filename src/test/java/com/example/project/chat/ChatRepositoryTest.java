package com.example.project.chat;

import com.example.project.social.Social;
import com.example.project.social.SocialRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private SocialRepository socialRepository;

    @Test
    public void findBySocialId_test(){
        // given
        Integer socialId = 1;

        // when
        List<Chat> chatList = chatRepository.findBySocialId(socialId);

        // eye
        chatList.forEach(chat -> {
            System.out.println(chat.getComment());
        });

        // then
        Assertions.assertThat(chatList.size()).isEqualTo(3);
        Assertions.assertThat(chatList.getFirst().getComment()).isEqualTo("소셜에 가입 하신것을 환영합니다.");
    }
}
