package org.fungover.demo.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.assertj.core.api.Assertions;
import org.fungover.demo.exceptionmapper.IllegalPersonLibraryStateException;
import org.fungover.demo.exceptionmapper.IllegalPersonLibraryStateExceptionMapper;
import org.fungover.demo.persons.Person;
import org.fungover.demo.service.ImplPersonService;
import org.fungover.demo.service.PersonService;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.spi.Dispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonResourceTest {

    Dispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        PersonService service = new ImplPersonService();
        PersonResource personResource = new PersonResource(service);
        dispatcher = MockDispatcherFactory.createDispatcher();
        //dispatcher.getRegistry().addPerRequestResource(PersonResource.class);
        dispatcher.getRegistry().addSingletonResource(personResource);

        ExceptionMapper<IllegalPersonLibraryStateException> exMapper =
                new IllegalPersonLibraryStateExceptionMapper();
        dispatcher.getProviderFactory().registerProviderInstance(exMapper);
    }

    @Test
    void callingHelloWorldShouldReturnHelloMars() throws URISyntaxException, UnsupportedEncodingException {
        // Create a mock request and response
        MockHttpRequest request = MockHttpRequest.get("/hello-world");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        // Assert the response status code and content
        assertEquals(200, response.getStatus());
        assertEquals("Hello Mars", response.getContentAsString());
    }

    @Test
    void whenPostingJsonRepresentingPersonThenShouldGet201Created() throws URISyntaxException, JsonProcessingException {
        MockHttpRequest request = MockHttpRequest.post("/persons");
        String json = new ObjectMapper().writeValueAsString(new Person("Kalle", 18));
        request.content(json.getBytes());
        request.contentType(MediaType.APPLICATION_JSON);
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        // Assert the response status code and content
        assertEquals(201, response.getStatus());
    }

    @Test
    void shouldHaveVerifyAnnotationOnPersonParameter() throws NoSuchMethodException {
        // Get the method
        Method method = PersonResource.class.getMethod("names", Person.class, String.class);

        // Get the parameters of the method
        Parameter[] parameters = method.getParameters();

        // Check if the first parameter has the @Verify annotation
        boolean isAnnotationPresent = parameters[0].isAnnotationPresent(Valid.class);

        // Assert using AssertJ
        Assertions.assertThat(isAnnotationPresent).isTrue();
    }
}
