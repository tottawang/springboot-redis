package com.sample.conf;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

  @Bean
  public KeyGenerator usersKeyGenerator() {
    return (o, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append("users");
      return sb.toString();
    };
  }

}
