package com.sample.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.sample.domain.User;

@Repository
public class SampleRepository {

  @Cacheable("users")
  public List<User> getUsers() {
    System.out.println("getUsers get called");
    List<User> users = new ArrayList<>();
    users.add(new User(0L, "user0"));
    users.add(new User(1L, "user1"));
    return users;
  }

  @CacheEvict(value = "users")
  public void evict() {}

}
