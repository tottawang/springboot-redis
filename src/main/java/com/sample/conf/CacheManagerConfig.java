package com.sample.conf;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CacheManagerConfig extends CachingConfigurerSupport {

  /**
   * Cache object for emptyUsers will be loaded at runtime
   */
  private static final String CACHE_NAME_EMPTY_USERS = "emptyUsers";

  @Bean
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setConnectionFactory(cf);
    return redisTemplate;
  }

  @Bean
  public KeyGenerator usersKeyGenerator() {
    return (o, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append("users");
      return sb.toString();
    };
  }

}
