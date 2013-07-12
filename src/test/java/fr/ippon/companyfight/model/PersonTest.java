package fr.ippon.companyfight.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PersonTest {


    @Test
    public void shouldValidateAPerson() {

        Person person = new Person();

        assertNotNull(person);
    }
}
