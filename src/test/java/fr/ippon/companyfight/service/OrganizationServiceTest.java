package fr.ippon.companyfight.service;

import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.model.Person;
import fr.ippon.companyfight.model.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrganizationServiceTest {

    @Test
    public void calculateScore() {
        OrganizationService service = new OrganizationService();

        Organization organization = new Organization();
        Repository repository = new Repository();
        repository.setName("test-repo");
        repository.setForks(3);
        organization.addRepository(repository);

        int score = service.calculateScore(organization);
        assertEquals(10 + 5*3, score);

        Person person1 = new Person();
        person1.setLogin("test-person-1");
        person1.setFollowersCount(2);
        organization.addMember(person1);

        score = service.calculateScore(organization);
        assertEquals(10*2 + 5*3 + 2*2, score);

        Person person2 = new Person();
        person2.setLogin("test-person-2");
        person2.setFollowersCount(10);
        organization.addMember(person2);

        score = service.calculateScore(organization);
        assertEquals(10*3 + 5*3 + 2*12, score);

    }
}
