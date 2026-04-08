package org.enrycoweiser.showmethedata.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.enrycoweiser.showmethedata.domain.Round;
import org.enrycoweiser.showmethedata.repository.custom.MyRoundRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RoundRepositoryImpl implements MyRoundRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Round> retrieveByTeamEventDates(String team, String event, String fromDate, String toDate) {
        StringBuilder hql = new StringBuilder();
        hql.append("from Round r where 1=1 ");
        if(event != null && !event.isEmpty()) {
            hql.append("and r.playedMap.match.event.code = :event ");
        } else {
            hql.append("and r.playedMap.match.event.code in ('EML', 'SAL', 'NAL') ");
        }
        if(team != null && !team.isEmpty()) {
            hql.append("and (r.playedMap.match.teamA.code = :team ");
            hql.append("or r.playedMap.match.teamB.code = :team) ");
        }
        if(fromDate != null && fromDate.length() == 8) {
            hql.append("and r.playedMap.match.date >= :fromDate ");
        }
        if(toDate != null && toDate.length() == 8) {
            hql.append("and r.playedMap.match.date <= :toDate ");
        }

        /* --- Manage the dates --- */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        /* ------------------------ */

        var query = entityManager.createQuery(hql.toString(), Round.class);
        if(event != null && !event.isEmpty()) {
            query.setParameter("event", event);
        }
        if(team != null && !team.isEmpty()) {
            query.setParameter("team", team);
        }
        if(fromDate != null && fromDate.length() == 8) {
            query.setParameter("fromDate", LocalDate.parse(fromDate, formatter));
        }
        if(toDate != null && toDate.length() == 8) {
            query.setParameter("toDate", LocalDate.parse(toDate, formatter));
        }

        return query.getResultList();
    }
}
