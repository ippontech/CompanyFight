package fr.ippon.companyfight.web.rest;

import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.service.OrganizationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/organizations")
@RequestScoped
public class OrganizationResource {

    @Inject
    private Logger log;

    @Inject
    private OrganizationService organizationService;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Organization initOrganization(@PathParam("name") String name) {
        log.info("REST request for initializing organization : " + name);
        return organizationService.getOrganization(name);
    }
}
