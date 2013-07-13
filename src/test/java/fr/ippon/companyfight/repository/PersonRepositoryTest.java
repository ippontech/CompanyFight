package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.model.Person;
import fr.ippon.companyfight.model.Repository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PersonRepositoryTest {

    @Inject
    private PersonRepository personRepository;

    @Inject
    UserTransaction utx;

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static Archive<?> jar() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackage(Person.class.getPackage())
                .addPackage(PersonRepository.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                //.addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void preparePersistenceTest() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }

    @Test
    public void shouldCreateAPerson() {

        Person person = new Person();
        person.setLogin("test-login");
        person.setFollowersCount(10);
        person.setAvatarUrl("http://avatar.url");

        personRepository.createOrUpdatePerson(person);

        Person testPerson = personRepository.findPerson("test-login");
        assertNotNull(testPerson);
        assertEquals(10, testPerson.getFollowersCount());
        assertEquals("http://avatar.url", testPerson.getAvatarUrl());
        assertEquals(0, testPerson.getRepositories().size());
    }
}
