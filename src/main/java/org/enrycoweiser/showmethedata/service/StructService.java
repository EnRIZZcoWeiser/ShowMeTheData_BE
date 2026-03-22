package org.enrycoweiser.showmethedata.service;

import jakarta.transaction.Transactional;
import org.enrycoweiser.showmethedata.domain.Event;
import org.enrycoweiser.showmethedata.domain.Team;
import org.enrycoweiser.showmethedata.dto.response.struct.RegionTeamResponse;
import org.enrycoweiser.showmethedata.dto.response.struct.TierEventResponse;
import org.enrycoweiser.showmethedata.repository.EventRepository;
import org.enrycoweiser.showmethedata.repository.TeamRepository;
import org.enrycoweiser.showmethedata.statics.StructStatic;
import org.enrycoweiser.showmethedata.utils.ErrorUtil;
import org.enrycoweiser.showmethedata.utils.TransactionUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StructService {

    private final EventRepository eventRepository;
    private final TeamRepository teamRepository;

    public StructService(EventRepository eventRepository,
                         TeamRepository teamRepository) {
        this.eventRepository = eventRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TierEventResponse getTierEvent() {
        TierEventResponse response;

        try {
            response = new TierEventResponse();

            List<Event> events = eventRepository.retrieveAll();
            Map<String, List<String>> tierEvent = StructStatic.getTierEvent(events);
            response.setTierEvent(tierEvent);

            return response;
        } catch (Exception e) {
            TransactionUtil.rollback();

            response = new TierEventResponse();
            response.createErrorResponse(ErrorUtil.API_001_MSG, ErrorUtil.API_001);
            return response;
        }
    }

    @Transactional
    public RegionTeamResponse getRegionTeam() {
        RegionTeamResponse response;

        try {
            response = new RegionTeamResponse();

            List<Team> teams = teamRepository.retrieveAll();
            Map<String, List<String>> regionTeam = StructStatic.getRegionTeam(teams);
            response.setRegionTeam(regionTeam);

            return response;
        } catch (Exception e) {
            TransactionUtil.rollback();

            response = new RegionTeamResponse();
            response.createErrorResponse(ErrorUtil.API_001_MSG, ErrorUtil.API_001);
            return response;
        }
    }
}
