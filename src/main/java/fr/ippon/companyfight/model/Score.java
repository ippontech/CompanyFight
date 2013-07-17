package fr.ippon.companyfight.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Score {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private String scoreId;

    private int value;

    @Temporal(TemporalType.DATE)
    private Date scoreDate;

    @ManyToOne
    private Organization organization;

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(Date scoreDate) {
        this.scoreDate = scoreDate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (scoreId != null ? !scoreId.equals(score.scoreId) : score.scoreId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return scoreId != null ? scoreId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreId='" + scoreId + '\'' +
                ", value=" + value +
                ", scoreDate=" + scoreDate +
                ", organization=" + organization.getId() +
                "}";
    }
}
