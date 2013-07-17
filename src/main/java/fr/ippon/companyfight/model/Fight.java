package fr.ippon.companyfight.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fight {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long fightId;

    @ManyToOne
    private Organization organization1;

    @ManyToOne
    private Organization organization2;

    public long getFightId() {
        return fightId;
    }

    public void setFightId(long fightId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fight fight = (Fight) o;

        if (fightId != fight.fightId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (fightId ^ (fightId >>> 32));
    }

    @Override
    public String toString() {
        return "Fight{" +
                "fightId='" + fightId + '\'' +
                ", organization1=" + organization1.getId() +
                ", organization2=" + organization2.getId() +
                "}";
    }
}
