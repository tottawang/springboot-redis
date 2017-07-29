package com.sample.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * Use this config class to config CacheManagerCustomizer properly
 *
 */
@Configuration
public class CacheCustomizerConfig {

  public static final String CACHE_NAME_USERS = "users";

  @Bean
  public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
    return cacheManager -> {
      final Map<String, Long> expires = new HashMap<>();
      expires.put(CACHE_NAME_USERS, Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(60000)));
      cacheManager.setExpires(expires);
    };
  }
}
