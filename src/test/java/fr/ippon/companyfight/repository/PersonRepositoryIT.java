package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.model.Person;
import fr.ippon.companyfight.model.Repository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PersonRepositoryIT {

    @Inject
    private PersonRepository personRepository;

    @Deployment
    public static JavaArchive jar() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Organization.class, Person.class, Repository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void shouldCreateAPerson() {

        Person person = new Person();
        person.setLogin("test-login");
        person.setFollowersCount(10);
        person.setAvatarUrl("http://avatar.url");

        Repository repo = new Repository();
        repo.setName("test-repo");
        repo.setForks(3);
        repo.setUrl("http://test-repo.url");

        Set<Repository> repos = new HashSet<Repository>();
        repos.add(repo);

        person.setRepositories(repos);

        personRepository.createOrUpdatePerson(person);

        Person testPerson = personRepository.findPerson("test-login");
        assertNotNull(testPerson);
        assertEquals(10, testPerson.getFollowersCount());
        assertEquals("http://avatar.url", testPerson.getAvatarUrl());
        assertEquals(1, testPerson.getRepositories().size());

        Repository testRepo = testPerson.getRepositories().iterator().next();
        assertEquals("test-repo", testRepo.getName());
        assertEquals(3, testRepo.getForks());
        assertEquals("http://test-repo.url", testRepo.getUrl());

    }
}
