package fr.ippon.companyfight.web.rest;

import fr.ippon.companyfight.service.FightService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.logging.Logger;

@Path("/fight")
@RequestScoped
public class FightResource {

    @Inject
    private Logger log;

    @Inject
    private FightService fightService;

    @POST
    @Path("/{company1}/{company2}")
    public void initOrganization(@PathParam("company1") String company1, @PathParam("company2") String company2) {
        log.info("REST request to save fight between " + company1 + " and " + company2);
        fightService.saveFight(company1, company2);
    }
}
