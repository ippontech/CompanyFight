package fr.ippon.companyfight.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fight {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private String fightId;

    @ManyToOne
    private Organization organization1;

    @ManyToOne
    private Organization organization2;

    @Temporal(TemporalType.DATE)
    private Date fightDate;

    public String getFightId() {
        return fightId;
    }

    public void setFightId(String fightId) {
        this.fightId = fightId;
    }

    public Organization getOrganization1() {
        return organization1;
    }

    public void setOrganization1(Organization organization1) {
        this.organization1 = organization1;
    }

    public Organization getOrganization2() {
        return organization2;
    }

    public void setOrganization2(Organization organization2) {
        this.organization2 = organization2;
    }

    public Date getFightDate() {
        return fightDate;
    }

    public void setFightDate(Date fightDate) {
        this.fightDate = fightDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fight fight = (Fight) o;

        if (fightId != null ? !fightId.equals(fight.fightId) : fight.fightId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fightId != null ? fightId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Fight{" +
                "fightId='" + fightId + '\'' +
                ", organization1=" + organization1.getId() +
                ", organization2=" + organization2.getId() +
                ", fightDate=" + fightDate +
                "}";
    }
}
