package com.example.project.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
    private final EntityManager em;

    public List<Object[]> mySocialList(Integer userId) {
        String q = """
                               
                SELECT
                      s.id,
                      s.name,
                      s.image,
                      COUNT(sm2.id) AS member_count
                  FROM
                      social_tb s
                  JOIN
                      social_member_tb sm1 ON s.id = sm1.social_id
                  JOIN
                      social_member_tb sm2 ON s.id = sm2.social_id AND sm2.state = 'APPROVED'
                  WHERE
                      sm1.user_id = ?
                      AND sm1.state = 'APPROVED'
                  GROUP BY
                      s.id, s.name, s.image;
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, 1);

        return query.getResultList();
    }

    public List<Object[]> mySocialPopularList(Integer userId) {
        String q = """
                SELECT
                    b.id,
                    b.title,
                    b.content,
                    u.nickname,
                    COUNT(l.id) AS like_count,
                    (SELECT COUNT(*) FROM reply_tb WHERE board_id = b.id) AS reply_count
                FROM
                    board_tb b
                JOIN
                    user_tb u ON b.user_id = u.id
                LEFT JOIN
                    like_tb l ON b.id = l.board_id
                WHERE
                    b.social_id = (
                        SELECT social_id
                        FROM social_member_tb
                        WHERE user_id = ?
                        AND state = 'APPROVED'
                        LIMIT 1
                    )
                    AND l.id IS NOT NULL
                GROUP BY
                    b.id, b.title, b.content, u.nickname
                ORDER BY
                    like_count DESC
                LIMIT 4;
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, 1);

        return query.getResultList();
    }

    public List<Object[]> popularPostList() {
        String q = """
                SELECT
                    b.id,
                    b.title,
                    b.content,
                    u.nickname,
                    COUNT(l.id) AS like_count,
                    (SELECT COUNT(*) FROM reply_tb WHERE board_id = b.id) AS reply_count
                FROM
                    board_tb b
                JOIN
                    user_tb u ON b.user_id = u.id
                LEFT JOIN
                    like_tb l ON b.id = l.board_id
                GROUP BY
                    b.id, b.title, b.content, u.nickname
                ORDER BY
                    like_count DESC
                LIMIT 4;
                """;

        Query query = em.createNativeQuery(q);

        return query.getResultList();
    }

    public List<Object[]> categorySocialList(Integer userId) {
        String q = """
                SELECT
                    s.id,
                    s.name,
                    s.image,
                    s.info,
                    COUNT(sm.user_id) AS total_members,
                    (SELECT u.nickname
                    FROM social_member_tb sm
                JOIN user_tb u ON sm.user_id = u.id
                WHERE sm.social_id = s.id AND sm.role = 'MANAGER'
                LIMIT 1) AS manager_name
                FROM
                    social_tb s
                LEFT JOIN
                    social_member_tb sm ON s.id = sm.social_id
                WHERE
                    s.id IN (
                       SELECT social_id
                       FROM category_tb
                       WHERE category_name_id = ?
                )
                GROUP BY
                    s.id, s.name, s.image, s.info
                ORDER BY
                    s.id;
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, 1);

        return query.getResultList();
    }

}
