package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api")
public class SampleResource {

  @Autowired
  private SampleRepository sampleRepository;

  @GET
  @Path("/get-users")
  @Produces("text/plain")
  public String getUsers() {
    return sampleRepository.getUsers().toString();
  }

  @GET
  @Path("/get-empty-users")
  @Produces("text/plain")
  public String getEmptyUsers() {
    return sampleRepository.getEmptyUsers().toString();
  }

  @POST
  @Path("/evict")
  public void clearCache() {
    sampleRepository.evict();
    sampleRepository.evictEmptyUsers();
  }
}
