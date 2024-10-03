package org.fungover.demo.resource;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fungover.demo.interceptor.Log;
import org.fungover.demo.persons.Person;
import org.fungover.demo.persons.Persons;
import org.fungover.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@Path("/")
@Log
public class PersonResource {

    // Create a logger
    private static final Logger logger = LoggerFactory.getLogger(PersonResource.class);

    private PersonService personService;

    public PersonResource() {
    }

    //Constructor injection. Prefer over field injection. Better for testing.
    @Inject
    public PersonResource(@Named("Impl") PersonService personService) {
        System.out.println("HelloResource object created");
        this.personService = personService;
    }

    @GET
    @Path("/hello-world")
    @Produces("text/plain")
    public String hello() {
        //logger.info("This is an info message");
        return "Hello Mars";
    }

    @GET
    @Path("/persons")
    @Produces(MediaType.APPLICATION_JSON)
    public Persons allPersons() {
        return new Persons(personService.getAllPersons(), "Updated");
    }

    @GET
    @Path("/persons/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onePerson(@PathParam("id") String id) {
        if (id.equals("1"))
            return Response.ok().entity(new Person("Kalle", 12)).build();
        if (id.equals("2"))
            return Response.ok().entity(new Person("Anna", 3)).build();
        logger.error("Invalid id " + id);
        return Response.status(Response.Status.NOT_FOUND).header("Custom-error", "Try again").build();
    }


    @POST
    @Path("/persons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response names(@Valid Person person) {
//        if( person.name().isBlank() || person.age() < 0 )
//            return Response.status(Response.Status.BAD_REQUEST).build();
        //throw new WebApplicationException(Response.Status.BAD_REQUEST);

        personService.addPerson(person);
        return Response.created(URI.create("/persons/" + personService.getCount())).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.TEXT_PLAIN)
    public String search(@QueryParam("text") String text, @QueryParam("pages") int pages) {
        return text + " " + pages;
    }
}
