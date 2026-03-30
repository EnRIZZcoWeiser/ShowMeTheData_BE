package org.enrycoweiser.showmethedata.statics;

import org.enrycoweiser.showmethedata.domain.OperatorBan;
import org.enrycoweiser.showmethedata.domain.Round;
import org.enrycoweiser.showmethedata.domain.Team;
import org.enrycoweiser.showmethedata.dto.response.OperatorStatsTile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorStatsStatic {
    public static void completeTiles(List<Round> rounds, List<OperatorStatsTile> tiles, String team) {
        Map<String, OperatorStatsTile> map = populateHelperMap(tiles);
        populateBans(rounds, map, team);
    }

    protected static Map<String, OperatorStatsTile> populateHelperMap(List<OperatorStatsTile> tiles) {
        Map<String, OperatorStatsTile> map = new HashMap<>();

        for (OperatorStatsTile tile : tiles) {
            map.put(tile.getOperator(), tile);
        }

        return map;
    }

    protected static void populateBans(List<Round> rounds, Map<String, OperatorStatsTile> map, String team) {
        for (Round round : rounds) {
            Integer roundNumber = round.getNumber();
            boolean isFilterByTeam = team != null && !team.isEmpty();

            boolean isMyTeamA = isFilterByTeam && round.getPlayedMap().getMatch().getTeamA().getCode().equals(team);
            String myTeamRole = null;
            if(isFilterByTeam) {
                myTeamRole = isMyTeamA ? round.getTeamARole() : round.getTeamBRole();
            }

            String teamARole = round.getTeamARole();
            String teamBRole = round.getTeamBRole();
            Team teamA = round.getPlayedMap().getMatch().getTeamA();
            Team teamB = round.getPlayedMap().getMatch().getTeamB();

            List<OperatorBan> bans = round.getPlayedMap().getOperatorBans();
            for (OperatorBan ban : bans) {

                if(isFilterByTeam) {
                    String teamBanning = ban.getTeam().getCode();
                    if(team.equals(teamBanning) || !ban.getRole().equals(myTeamRole)) {
                        continue;
                    }
                } else if((ban.getTeam().equals(teamA) && ban.getRole().equals(teamARole)) ||
                            (ban.getTeam().equals(teamB) && ban.getRole().equals(teamBRole))) {
                    continue;
                }

                map.get(ban.getBan1().getName()).addBan();
                map.get(ban.getBan2().getName()).addBan();
                if((roundNumber >= 4 && roundNumber <=6) || roundNumber >= 10) {
                    if(ban.getBan3() != null) {
                        map.get(ban.getBan3().getName()).addBan();
                    }
                }
            }
        }
    }
}
