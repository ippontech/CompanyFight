package fr.ippon.companyfight.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.hibernate.LazyInitializationException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.ippon.companyfight.model.Organization;

@RunWith(Arquillian.class)
@ApplyScriptBefore
public class OrganizationRepositoryIT {

    @Inject
    private OrganizationRepository organizationRepository;

    @Deployment
    public static Archive<?> jar() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackage(Organization.class.getPackage())
                .addPackage(OrganizationRepository.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("scripts/before-fr.ippon.companyfight.repository.OrganizationRepositoryIT.sql", "scripts/before-fr.ippon.companyfight.repository.OrganizationRepositoryIT.sql")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void lazyLoaded() {
    	final List<Organization> organizations = organizationRepository.findAll();
    	assertNotNull(organizations);

    	// Find 'ippontech' organization
    	final Organization organization = (Organization) CollectionUtils.find(organizations, new Predicate(){
													    	    public boolean evaluate(Object o) {
													    	      return "ippontech".equalsIgnoreCase(((Organization) o).getId());
													    	    }
													    	  });
    	// Should not be null
    	assertNotNull(organization);
    	// Should have 15 repositories referenced
    	assertEquals(15, organization.getRepositories().size());
    }

    @Test(expected=LazyInitializationException.class)
    @Transactional(TransactionMode.DISABLED)
    public void lazyNotLoaded() {
    	final List<Organization> organizations = organizationRepository.findAll();
    	assertNotNull(organizations);
    	// try to get what we are not allowed to...
    	// Feel free to add a breakpoint to method 'initialize()' of 'org.hibernate.collection.internal.AbstractPersistentCollection' to understand what happened
    	organizations.get(0).getRepositories().size();
    }

}
