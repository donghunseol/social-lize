package com.example.project.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    private static final Map<String, String> dayNameMap = new HashMap<>();

    static {
        dayNameMap.put("Sunday", "일요일");
        dayNameMap.put("Monday", "월요일");
        dayNameMap.put("Tuesday", "화요일");
        dayNameMap.put("Wednesday", "수요일");
        dayNameMap.put("Thursday", "목요일");
        dayNameMap.put("Friday", "금요일");
        dayNameMap.put("Saturday", "토요일");
    }

    @Test
    public void getArticleCountBySocialIdAndUserId_test(){
        Integer count = boardRepository.getArticleCountByBoardSocialIdAndUserId(1,1);
        System.out.println("count = " + count);

    }

    @Test
    public void findBySocialId_test(){
        List<Board> board = boardRepository.findByBoardSocialId(1);

        for (int i = 0; i < board.size(); i++) {
            System.out.println(board.get(i).getUserId().getNickname());
        }
    }

    @Test
    public void findPostCountsByDay_test() {
        Integer socialId = 4;
        List<Object[]> board = boardRepository.findPostCountsByDayOfWeek(socialId);

        // 결과 출력
        for (Object[] result : board) {
            String dayOfWeek = (String) result[0];
            Long count = (Long) result[1];
            String date = dayNameMap.get(dayOfWeek);
            System.out.println(date + " " + count);
        }
    }
}
