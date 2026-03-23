package org.enrycoweiser.showmethedata.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.enrycoweiser.showmethedata.domain.BanMap;
import org.enrycoweiser.showmethedata.repository.custom.MyBanMapRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BanMapRepositoryImpl implements MyBanMapRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BanMap> retrieveByTeamDates(String team, String fromDate, String toDate) {
        StringBuilder hql = new StringBuilder();
        hql.append("from BanMap b where 1=1 ");
        hql.append("and (b.match.teamA.code = :team or b.match.teamB.code = :team) ");
        if(fromDate != null && fromDate.length() == 8) {
            hql.append("and b.match.date >= :fromDate ");
        }
        if(toDate != null && toDate.length() == 8) {
            hql.append("and b.match.date <= :toDate ");
        }

        /* --- Manage the dates --- */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        /* ------------------------ */

        var query = entityManager.createQuery(hql.toString(), BanMap.class);
        query.setParameter("team", team);
        if(fromDate != null && fromDate.length() == 8) {
            query.setParameter("fromDate", LocalDate.parse(fromDate, formatter));
        }
        if(toDate != null && toDate.length() == 8) {
            query.setParameter("toDate", LocalDate.parse(toDate, formatter));
        }

        return query.getResultList();
    }
}
