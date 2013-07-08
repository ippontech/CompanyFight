package fr.ippon.companyfight.service;

import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.model.Person;
import fr.ippon.companyfight.repository.OrganizationRepository;
import fr.ippon.companyfight.repository.PersonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class OrganizationService {

    @Inject
    private Logger log;

    @Inject
    private GithubService githubService;

    @Inject
    private OrganizationRepository organizationRepository;

    @Inject
    private PersonRepository personRepository;

    public Organization getOrganization(String name) {
        Organization existingOrganization = organizationRepository.findOrganization(name);
        if (existingOrganization != null) {
            log.info("Organization already exists!");
            return existingOrganization;
        }
        log.info("Initializing an organization from Github");
        Organization organization = githubService.fetchOrganizationFromGithub(name);
        if (organization != null) {
            log.info("Organization fetched from Github");
            for (Person person : organization.getMembers()) {
                personRepository.createOrUpdatePerson(person);
            }
            organizationRepository.createOrganization(organization);
            return organization;
        } else {
            log.info("Could not retrieve organization");
            return null;
        }
    }
}
