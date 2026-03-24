package org.enrycoweiser.showmethedata.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.enrycoweiser.showmethedata.domain.Bombsite;
import org.enrycoweiser.showmethedata.domain.Round;
import org.enrycoweiser.showmethedata.repository.custom.MyBombsiteRepository;

import java.util.List;

public class BombsiteRepositoryImpl implements MyBombsiteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bombsite> retrieveActiveBombsite() {
        String hql = "from Bombsite b where b.active = 'Y' order by b.id";

        var query = entityManager.createQuery(hql, Bombsite.class);

        return query.getResultList();
    }
}
