package fr.ippon.companyfight.service;

import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.model.Person;
import fr.ippon.companyfight.repository.OrganizationRepository;
import fr.ippon.companyfight.repository.PersonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
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
            Calendar testCalendar = Calendar.getInstance();
            testCalendar.add(Calendar.MONTH, -1);
            if (existingOrganization.getUpdatedAt().after(testCalendar.getTime())) {
                return existingOrganization;
            } else {
                log.info("The organization is more than one month old, refreshing it!");
                organizationRepository.deleteOrganization(existingOrganization);
            }
        }
        log.info("Initializing an organization from Github");
        Organization organization = githubService.fetchOrganizationFromGithub(name);
        if (organization != null) {
            log.info("Organization fetched from Github");
            for (Person person : organization.getMembers()) {
                personRepository.createOrUpdatePerson(person);
            }
            organization.setUpdatedAt(Calendar.getInstance().getTime());
            organizationRepository.createOrganization(organization);
            return organization;
        } else {
            log.info("Could not retrieve organization");
            return null;
        }
    }
}
