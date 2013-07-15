package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Fight;
import fr.ippon.companyfight.model.Organization;
import fr.ippon.companyfight.model.Score;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class ScoreRepository {

    @PersistenceContext
    private EntityManager em;

    public void createScore(Score score) {
        score.setScoreDate(Calendar.getInstance().getTime());
        em.persist(score);
    }
}
