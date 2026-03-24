package org.enrycoweiser.showmethedata.service;

import org.enrycoweiser.showmethedata.domain.Bombsite;
import org.enrycoweiser.showmethedata.domain.Round;
import org.enrycoweiser.showmethedata.dto.request.MapStatsRequest;
import org.enrycoweiser.showmethedata.dto.response.MapStatsResponse;
import org.enrycoweiser.showmethedata.dto.response.MapStatsTile;
import org.enrycoweiser.showmethedata.repository.BombsiteRepository;
import org.enrycoweiser.showmethedata.repository.RoundRepository;
import org.enrycoweiser.showmethedata.repository.custom.MyBombsiteRepository;
import org.enrycoweiser.showmethedata.standard.StandardService;
import org.enrycoweiser.showmethedata.statics.MapStatsStatic;

import java.util.List;

public class MapStatsService implements StandardService<MapStatsRequest, MapStatsResponse> {

    private final RoundRepository roundRepository;
    private final BombsiteRepository bombsiteRepository;

    public MapStatsService(RoundRepository roundRepository,
                           BombsiteRepository bombsiteRepository) {
        this.roundRepository = roundRepository;
        this.bombsiteRepository = bombsiteRepository;
    }

    @Override
    public MapStatsResponse getAPI(MapStatsRequest request) {
        MapStatsResponse response = new MapStatsResponse();

        String team = request.getTeam();
        String event = request.getEvent();
        String fromDate = request.getFromDate();
        String toDate = request.getToDate();

        List<Bombsite> bombsite = bombsiteRepository.retrieveActiveBombsite();
        List<Round> rounds = roundRepository.retrieveByTeamEventDates(team, event, fromDate, toDate);

        List<List<MapStatsTile>> tiles = MapStatsStatic.getMapStatsTiles(rounds, team, bombsite);
        response.setAttackTiles(tiles.get(0));
        response.setDefenceTiles(tiles.get(1));

        return response;
    }
}
