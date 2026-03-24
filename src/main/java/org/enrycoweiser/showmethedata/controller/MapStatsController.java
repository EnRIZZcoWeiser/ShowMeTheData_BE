package org.enrycoweiser.showmethedata.controller;

import org.enrycoweiser.showmethedata.dto.request.MapStatsRequest;
import org.enrycoweiser.showmethedata.dto.response.MapStatsResponse;
import org.enrycoweiser.showmethedata.service.MapStatsService;
import org.enrycoweiser.showmethedata.standard.StandardController;

public class MapStatsController implements StandardController<MapStatsRequest, MapStatsResponse> {

    private final MapStatsService service;

    public MapStatsController(MapStatsService service) {
        this.service = service;
    }

    @Override
    public MapStatsResponse getAPI(MapStatsRequest request) {
        return service.getAPI(request);
    }
}
