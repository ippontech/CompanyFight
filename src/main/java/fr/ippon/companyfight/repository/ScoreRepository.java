package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Fight;
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

    public List<Score> findHighScores() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Score> cq = cb.createQuery(Score.class);
        Root<Score> scoreRoot = cq.from(Score.class);
        cq.orderBy(cb.desc(scoreRoot.get("value")));
        cq.select(scoreRoot);
        TypedQuery<Score> q = em.createQuery(cq);
        q.setMaxResults(30);
        List<Score> highScores = q.getResultList();
        return highScores;
    }
}
