package com.sample.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.sample.resources.SampleResource;

@Configuration
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(SampleResource.class);
  }
}
