package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PersonRepository {

    @Inject
    private EntityManager em;

    public Person createOrUpdatePerson(Person person) {
        return em.merge(person);
    }

    public List<Person> findAll() {
        return em.createNamedQuery("findAllPersons").getResultList();
    }

    public Person findPerson(String login) {
        return em.find(Person.class, login);
    }
}
