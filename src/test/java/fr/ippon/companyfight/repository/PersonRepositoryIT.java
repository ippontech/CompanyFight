package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Person;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PersonRepositoryIT {

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
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void preparePersistenceTest() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void rollbackTransaction() throws Exception {
        utx.rollback();
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

    @Test
    public void shouldGetAllPersons() {
        List<Person> persons = personRepository.findAll();
        assertEquals(0, persons.size());

        create10Persons();

        persons = personRepository.findAll();
        assertEquals(10, persons.size());
    }

    @Test
    public void shouldGetPersonsOrderedByFollowersCount() {
        List<Person> persons = personRepository.findPersonsOrderedByFollowersCount();
        assertEquals(0, persons.size());

        create10Persons();

        Person person = new Person();
        person.setLogin("test-login-11");
        person.setFollowersCount(10);
        person.setAvatarUrl("http://avatar.url");
        personRepository.createOrUpdatePerson(person);

        person = new Person();
        person.setLogin("test-login-12");
        person.setFollowersCount(0);
        person.setAvatarUrl("http://avatar.url");
        personRepository.createOrUpdatePerson(person);

        persons = personRepository.findPersonsOrderedByFollowersCount();
        assertEquals(12, persons.size());
        // test-login-11 should be the first user
        assertEquals("test-login-11", persons.iterator().next().getLogin());

        // test-login-12 should be the last user
        assertEquals("test-login-12", persons.get(persons.size() - 1).getLogin());
    }

    private void create10Persons() {
        // Create 10 persons
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setLogin("test-login-"+i);
            person.setFollowersCount(5);
            person.setAvatarUrl("http://avatar.url");
            personRepository.createOrUpdatePerson(person);
        }
    }
}
