package com.balaji.mybazaar.secure;

import java.io.IOException;
import java.security.Key;

import javax.annotation.Priority;
import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.swing.JWindow;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import io.jsonwebtoken.Jwts;

//@Provider
@Authenticate
@Priority(Priorities.AUTHENTICATION)
public class AuthenticateRequestFilter implements ContainerRequestFilter {

	Logger LOGGER = Logger.getLogger(AuthenticateRequestFilter.class);
	@Inject
	private KeyGenerator keyGenerator;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub

		String authString = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		String token = authString.substring("Bearer".length()).trim();
		
		try {
			Key key = keyGenerator.generateKey();
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			LOGGER.info("Valid token :  "+ token);
		}catch(Exception ex) {
			LOGGER.info("Valid token :  "+ token);
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
		}
	}

}
