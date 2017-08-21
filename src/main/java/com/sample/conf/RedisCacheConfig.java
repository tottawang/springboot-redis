package com.sample.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnProperty(name = "SPRING_CACHE_TYPE", havingValue = "redis")
public class RedisCacheConfig extends CachingConfigurerSupport {

  public static final String CACHE_NAME_USERS = "users";

  /**
   * Must use specific name redisTemplate (or use same method name) in order to skip RedisTemplate
   * bean setup by Redis auto configure.
   * 
   * @return
   */
  @Bean(name = "redisTemplate")
  public RedisTemplate<Object, Object> getRedisTemplate(
      JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
