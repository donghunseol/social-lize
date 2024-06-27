package com.example.project._core.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/*
    문제: Redis를 사용할시 서버가 재시작이 되어도 sessionUser값이 레디스 서버에 살이있음
    해결: 레디스 서버에 저장되어있는 값을 지워준다. 사용한 모든 세션값은 여기서 지워야한다.
 */
@Component
public class RedisDataCleaner implements CommandLineRunner {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisDataCleaner(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        //redisTemplate.delete("sessionUser"); // 세션 키 삭제
        System.out.println("Session user data has been cleared.");
    }
}