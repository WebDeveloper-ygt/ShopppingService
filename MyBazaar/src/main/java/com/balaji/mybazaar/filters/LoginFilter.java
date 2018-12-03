package com.balaji.mybazaar.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;


public class LoginFilter implements ContainerRequestFilter {

	private static final String AUTH_HEAD_KEY ="Authorization";
	private static final String AUTH_HEAD_PREFIX = "Basic ";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		
		List<String> authHeaders = requestContext.getHeaders().get(AUTH_HEAD_KEY);
		if(authHeaders.size() >0) {
			String authToken = authHeaders.get(0);
			authToken=authToken.replaceFirst(AUTH_HEAD_PREFIX, "");
			String decodeString = Base64.decodeAsString(authToken);
			StringTokenizer tokanizer= new StringTokenizer(decodeString, ":");
			String user=tokanizer.nextToken();
			String pass=tokanizer.nextToken();
			
			
			
		}
		
		requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity("NOT ALLOWED").build());
	}

}
