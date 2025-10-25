package edu.eci.arsw.redis.connection;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PSRedisTemplate extends StringRedisTemplate {

    @Inject
    public PSRedisTemplate(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}
