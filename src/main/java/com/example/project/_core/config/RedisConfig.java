package com.example.project._core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost; // Redis 서버 호스트 이름

    @Value("${spring.data.redis.port}")
    private int redisPort; // Redis 서버 포트 번호

    @Value("${spring.data.redis.password}")
    private String redisPassword; // Redis 서버 비밀번호

    // RedisConnectionFactory 빈을 생성하여 IoC 컨테이너에 등록
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        System.out.println("서버 실행 시 동작 : RedisConnectionFactory");
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        // Redis 서버 설정
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setPassword(redisPassword);

        return new LettuceConnectionFactory(config); // LettuceConnectionFactory 사용하여 RedisConnectionFactory 생성
    }

    // RedisTemplate 빈을 생성하여 IoC 컨테이너에 등록
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory); // RedisConnectionFactory 설정

        // ObjectMapper 설정
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜/시간 모듈 등록
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // 날짜를 타임스탬프로 쓰지 않도록 설정
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // null 값 제외
//        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Object.class)
                .build();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        // GenericJackson2JsonRedisSerializer를 ObjectMapper로 초기화
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        // RedisTemplate의 직렬화기 설정
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        return template;
    }

    // Spring Session 기본 Redis 직렬화기 빈을 생성하여 IoC 컨테이너에 등록
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        // 동일한 ObjectMapper 설정 사용
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Java 8 날짜/시간 모듈 등록
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // 날짜를 타임스탬프로 쓰지 않도록 설정
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // null 값 제외

        return new GenericJackson2JsonRedisSerializer(objectMapper); // GenericJackson2JsonRedisSerializer를 ObjectMapper로 초기화
    }
}
