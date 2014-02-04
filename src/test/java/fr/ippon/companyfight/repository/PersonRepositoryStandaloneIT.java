package fr.ippon.companyfight.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.ippon.companyfight.model.Person;

public class PersonRepositoryStandaloneIT {

    private PersonRepository personRepository;

    private EntityManager em;
	private static EntityManagerFactory entityManagerFactory;
	

    @BeforeClass
    public static void createEMF() throws Exception {
    	entityManagerFactory = Persistence.createEntityManagerFactory("primary");
    }
    @AfterClass
    public static void destroyEMF() throws Exception {
    	entityManagerFactory.close();
    }
    
    @Before
    public void preparePersistenceTest() throws Exception {
    	em = entityManagerFactory.createEntityManager();
    	em.getTransaction().begin();

    	personRepository = new PersonRepository();
    	personRepository.em = em;
    }

    @After
    public void rollbackTransaction() throws Exception {
    	em.getTransaction().rollback();
    	em = null;
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
