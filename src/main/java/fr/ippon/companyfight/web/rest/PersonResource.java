package fr.ippon.companyfight.web.rest;

import fr.ippon.companyfight.model.Person;
import fr.ippon.companyfight.repository.PersonRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("/persons")
@RequestScoped
public class PersonResource {

    @Inject
    private Logger log;

    @Inject
    private PersonRepository personRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> listAllMembers() {
        return personRepository.findAll();
    }
}
