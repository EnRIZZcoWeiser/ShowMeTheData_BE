package org.enrycoweiser.showmethedata.statics;

import org.enrycoweiser.showmethedata.domain.Event;
import org.enrycoweiser.showmethedata.domain.Region;
import org.enrycoweiser.showmethedata.domain.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructStatic {
    public static Map<String, List<String>> getRegionTeam(List<Team> teams) {
        Map<String, List<String>> regionTeam = new HashMap<>();
        for (Team team : teams) {
            Region region = team.getRegion();

            regionTeam.putIfAbsent(region.toString(), new ArrayList<>());
            regionTeam.get(region.toString()).add(team.toString());
        }

        return regionTeam;
    }

    public static Map<String, List<String>> getTierEvent(List<Event> events) {
        Map<String, List<String>> tierEvent = new HashMap<>();
        for (Event event : events) {
            String tier = event.getTier();

            tierEvent.putIfAbsent(tier, new ArrayList<>());
            tierEvent.get(tier).add(event.getCode());
        }

        return tierEvent;
    }
}
