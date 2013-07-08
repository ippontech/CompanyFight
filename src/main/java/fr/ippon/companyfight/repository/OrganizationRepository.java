package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Organization;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class OrganizationRepository {

    @Inject
    private EntityManager em;

    public void createOrganization(Organization organization) {
        em.persist(organization);
    }

    public Organization findOrganization(String name) {
        return em.find(Organization.class, name);
    }

    public void deleteOrganization(Organization organization) {
        em.remove(organization);
    }
}
