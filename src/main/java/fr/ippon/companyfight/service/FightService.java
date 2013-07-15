package fr.ippon.companyfight.service;

import fr.ippon.companyfight.model.Fight;
import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.repository.FightRepository;
import fr.ippon.companyfight.repository.OrganizationRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class FightService {

    @Inject
    private Logger log;

    @Inject
    private OrganizationRepository organizationRepository;

    @Inject
    private FightRepository fightRepository;

    public void saveFight(String organizationName1, String organizationName2) {
        Organization organization1 = organizationRepository.findOrganization(organizationName1);
        Organization organization2 = organizationRepository.findOrganization(organizationName2);
        fightRepository.createFight(organization1, organization2);
        log.info("Fight saved!");
    }

    public List<Fight> findLatestFights() {
        return fightRepository.findLatestFights();
    }
}
