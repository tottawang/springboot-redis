package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/redis-cache")
public class SampleResource {

  @GET
  @Produces("text/plain")
  public String getSample() {
    return "test";
  }

}
