package org.fungover.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
public class HelloResource {
    @GET
    @Path("/hello-world")
    @Produces("text/plain")
    public String hello() {
        return "Hello Mars";
    }

    @GET
    @Path("/persons")
    @Produces(MediaType.APPLICATION_JSON)
    public Persons allPersons() {
        return new Persons(List.of(new Person("Kalle", 12),
                new Person("Anna", 3)), "Updated");
    }

    @GET
    @Path("/persons/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onePerson(@PathParam("id") String id) {
        if (id.equals("1"))
            return Response.ok().entity(new Person("Kalle", 12)).build();
        if (id.equals("2"))
            return Response.ok().entity(new Person("Anna", 3)).build();
        return Response.status(Response.Status.NOT_FOUND).header("Custom-error","Try again").build();
    }


    @POST
    @Path("/persons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String names(Person persons) {
        return persons.name();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.TEXT_PLAIN)
    public String search(@QueryParam("text") String text, @QueryParam("pages") int pages) {
        return text + " " + pages;
    }
}
