package org.enrycoweiser.showmethedata.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.enrycoweiser.showmethedata.dto.response.OperatorStatsTile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class QueryDtoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<OperatorStatsTile> getOperatorStatsTile(String team, String event, String fromDate, String toDate) {
        StringBuilder sql = new StringBuilder();
        sql.append("WITH picks AS ( ");
        sql.append("    SELECT ");
        sql.append("        op.id AS operator_id, ");
        sql.append("        COUNT(*) AS picks, ");
        sql.append("        SUM(CASE WHEN p.id_team = r.id_team_win THEN 1 ELSE 0 END) AS wins, ");
        sql.append("        SUM(CASE WHEN r.plant = 'Y' THEN 1 ELSE 0 END) AS plants ");
        sql.append("    FROM backend.operator_pick p ");
        sql.append("    JOIN backend.team t on p.id_team = t.id ");
        sql.append("    JOIN backend.operator op ON op.id = p.id_operator ");
        sql.append("    JOIN backend.round r ON r.id = p.id_round ");
        sql.append("    JOIN backend.played_map pm ON pm.id = r.id_map ");
        sql.append("    JOIN backend.match m ON m.id = pm.id_match ");
        sql.append("    WHERE 1=1 ");

        if(team != null && !team.isEmpty())     sql.append(" AND t.code = :teamCode ");
        if(event != null && !event.isEmpty())   sql.append(" AND m.event.code = :event ");
        if(fromDate != null)                    sql.append(" AND m.date >= :fromDate ");
        if(toDate != null)                      sql.append(" AND m.date <= :toDate ");

        sql.append("    GROUP BY op.id ");
        sql.append("), ");
        sql.append("rounds AS ( ");
        sql.append("    SELECT ");
        sql.append("        op.id AS operator_id, ");
        sql.append("        COUNT(r.id) AS rounds ");
        sql.append("    FROM backend.operator op ");
        sql.append("    JOIN backend.round r ON 1=1 ");
        sql.append("    JOIN backend.played_map pm ON pm.id = r.id_map ");
        sql.append("    JOIN backend.match m ON m.id = pm.id_match ");
        sql.append("    JOIN backend.team ta ON ta.id = m.id_team_a ");
        sql.append("    JOIN backend.team tb ON tb.id = m.id_team_b ");
        sql.append("    WHERE m.date >= op.release ");

        if(team != null && !team.isEmpty()) {
            sql.append("AND ((ta.code = :teamCode AND op.role = r.team_a_role) ");
            sql.append("OR (tb.code = :teamCode AND op.role = r.team_b_role)) ");
        }
        if(event != null)    sql.append(" AND m.event.code = :event ");
        if(fromDate != null) sql.append(" AND m.date >= :fromDate ");
        if(toDate != null)   sql.append(" AND m.date <= :toDate ");

        sql.append("    GROUP BY op.id ");
        sql.append(") ");
        sql.append("SELECT ");
        sql.append("    op.id, op.name, op.role, ");
        sql.append("    COALESCE(p.picks, 0) as picks, ");
        sql.append("    COALESCE(p.wins, 0) as wins, ");
        sql.append("    COALESCE(p.plants, 0) as plants, ");
        sql.append("    COALESCE(r.rounds, 0) as rounds ");
        sql.append("FROM backend.operator op ");
        sql.append("LEFT JOIN picks p ON p.operator_id = op.id ");
        sql.append("LEFT JOIN rounds r ON r.operator_id = op.id ");
        sql.append("ORDER BY op.role, op.name;");

        /* DATE FORMATTER */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate from = null;
        LocalDate to = null;

        Query query = entityManager.createNativeQuery(sql.toString());
        if(team != null && !team.isEmpty()) {
            query.setParameter("teamCode", team);
        }
        if(event != null && !event.isEmpty()) {
            query.setParameter("event", event);
        }
        if(fromDate != null && fromDate.length() == 8) {
            from = LocalDate.parse(fromDate, formatter);
            query.setParameter("fromDate", from);
        }
        if(toDate != null && toDate.length() == 8) {
            to = LocalDate.parse(toDate, formatter);
            query.setParameter("toDate", to);
        }

        List<Object[]> rows = query.getResultList();

        return rows.stream().map(this::mapToOperatorStatsTile).toList();
    }

    private OperatorStatsTile mapToOperatorStatsTile(Object[] r) {
        OperatorStatsTile tile = new OperatorStatsTile();
        tile.setOperator((String) r[1]);
        tile.setRole((String) r[2]);
        tile.setPick(((Number) r[3]).intValue());
        tile.setWin(((Number) r[4]).intValue());
        tile.setPlant(((Number) r[5]).intValue());
        tile.setRound(((Number) r[6]).intValue());

        return tile;
    }
}
