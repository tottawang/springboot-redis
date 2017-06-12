package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/redis-cache")
public class SampleResource {

  @Autowired
  private SampleRepository sampleRepository;

  @GET
  @Produces("text/plain")
  public String getSample() {
    return sampleRepository.getUsers().toString();
  }

  @POST
  @Path("/evict")
  public void clearCache() {
    sampleRepository.evict();
  }

}
