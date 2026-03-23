package org.enrycoweiser.showmethedata.service;

import org.enrycoweiser.showmethedata.domain.BanMap;
import org.enrycoweiser.showmethedata.dto.request.MapPreferenceRequest;
import org.enrycoweiser.showmethedata.dto.response.MapPreferenceResponse;
import org.enrycoweiser.showmethedata.dto.response.MapPreferenceTile;
import org.enrycoweiser.showmethedata.repository.BanMapRepository;
import org.enrycoweiser.showmethedata.standard.StandardService;
import org.enrycoweiser.showmethedata.statics.MapPreferenceStatic;
import org.enrycoweiser.showmethedata.utils.ErrorUtil;
import org.enrycoweiser.showmethedata.utils.TransactionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapPreferenceService implements StandardService<MapPreferenceRequest, MapPreferenceResponse> {

    private final BanMapRepository banMapRepository;

    public MapPreferenceService(BanMapRepository banMapRepository) {
        this.banMapRepository = banMapRepository;
    }

    @Override
    public MapPreferenceResponse getAPI(MapPreferenceRequest request) {
        MapPreferenceResponse response;

        try {
            response = new MapPreferenceResponse();

            String team = request.getTeam();
            String fromDate = request.getFromDate();
            String toDate = request.getToDate();

            List<BanMap> banMaps = banMapRepository.retrieveByTeamDates(team, fromDate, toDate);

            List<MapPreferenceTile> tiles = MapPreferenceStatic.getMapPreferenceTiles(banMaps, request.getTeam());
            response.setTiles(tiles);

            return response;
        } catch (Exception e) {
            TransactionUtil.rollback();

            response = new MapPreferenceResponse();
            response.createErrorResponse(ErrorUtil.API_001_MSG, ErrorUtil.API_001);
            return response;
        }


    }
}
