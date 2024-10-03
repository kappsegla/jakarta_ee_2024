package org.fungover.demo.exceptionmapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class IllegalPersonLibraryStateExceptionMapper implements ExceptionMapper<IllegalPersonLibraryStateException> {
    private static final Logger logger = LoggerFactory.getLogger(IllegalPersonLibraryStateExceptionMapper.class);

    @Override
    public Response toResponse(IllegalPersonLibraryStateException e) {
        logger.error("Exception of type IllegalPersonLibraryState happened.");
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity("This endpoint doesn't seem to accept your request")
                .build();
    }
}
