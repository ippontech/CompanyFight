package fr.ippon.companyfight.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.ippon.companyfight.model.Organization;

@ApplicationScoped
public class OrganizationRepository {

    @PersistenceContext
    private EntityManager em;

    public Organization createOrUpdateOrganization(Organization organization) {
        return em.merge(organization);
    }

    public Organization findOrganization(String name) {
        return em.find(Organization.class, name);
    }

    public List<Organization> findAll() {
    	return em.createNamedQuery("findAllOrganizations").getResultList();
    }

    public void deleteOrganization(Organization organization) {
        em.remove(organization);
    }
}
