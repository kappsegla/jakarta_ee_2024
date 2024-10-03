package org.fungover.demo.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
//Used to intercept and process HTTP requests before they reach the JAX-RS resource methods.
public class IPRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
//        String remoteAddr = requestContext.getHeaderString("X-Forwarded-For");
//        if (remoteAddr == null) {
//            remoteAddr = requestContext.getUriInfo().getRequestUri().getHost();
//        }
//        if (!"127.0.0.1".equals(remoteAddr) && !"localhost".equals(remoteAddr)) {
//            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access denied").build());
//        }
    }
}
