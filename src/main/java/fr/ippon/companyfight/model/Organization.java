package fr.ippon.companyfight.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "findAllOrganizations",
            query = "SELECT o FROM Organization o"
    )
})
public class Organization implements Serializable {

    @Id
    private String id;

    private int followersCount;

    private String avatarUrl;

    @Transient
    private int score;

    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @ManyToMany
    private Set<Person> members = new HashSet<Person>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Repository> repositories = new HashSet<Repository>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Score> scores = new HashSet<Score>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Person> getMembers() {
        return members;
    }

    public void setMembers(Set<Person> members) {
        this.members = members;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void addMember(Person person) {
        members.add(person);
    }

    public void addRepository(Repository repository) {
        repositories.add(repository);
    }

    public void addScore(Score score) {
        scores.add(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id='" + id + '\'' +
                ", followersCount=" + followersCount +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", updatedAt=" + updatedAt +
                "}";
    }
}
