package fr.ippon.companyfight.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NamedQuery(
        name="findAllPersons",
        query="SELECT p FROM Person p"
)
public class Person implements Serializable {

    @Id
    private String login;

    private int followersCount;

    private String avatarUrl;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Repository> repositories;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followers) {
        this.followersCount = followers;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (login != null ? !login.equals(person.login) : person.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", login='" + login + '\'' +
                ", followersCount=" + followersCount +
                "} " + super.toString();
    }
}
