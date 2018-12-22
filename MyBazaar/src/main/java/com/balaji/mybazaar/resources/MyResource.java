package com.balaji.mybazaar.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/myresource")
public class MyResource {

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @GET
    @Path("/echo")
    //@Authenticate
    @Produces(MediaType.TEXT_PLAIN)
    public String postIt(String name) {
        return "Got it! : "+ name;
    }
}
