package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Score;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;

@ApplicationScoped
public class ScoreRepository {

    @PersistenceContext
    private EntityManager em;

}
