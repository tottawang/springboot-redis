package com.sample.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
@ConditionalOnProperty(name = "SPRING_CACHE_TYPE", havingValue = "redis")
public class CacheConfig extends CachingConfigurerSupport {

  public static final String CACHE_NAME_USERS = "users";

  /**
   * Customize cache manager to setup expires on <code>users<code> cache instance.
   */
  @Bean
  public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
    return cacheManager -> {
      final Map<String, Long> expires = new HashMap<>();
      expires.put(CACHE_NAME_USERS, Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(60000)));
      cacheManager.setExpires(expires);
    };
  }

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
