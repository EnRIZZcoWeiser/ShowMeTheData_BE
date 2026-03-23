package org.enrycoweiser.showmethedata.controller;

import org.enrycoweiser.showmethedata.dto.request.MapPreferenceRequest;
import org.enrycoweiser.showmethedata.dto.response.MapPreferenceResponse;
import org.enrycoweiser.showmethedata.service.MapPreferenceService;
import org.enrycoweiser.showmethedata.standard.StandardController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/map_preference")
public class MapPreferenceController implements StandardController<MapPreferenceRequest, MapPreferenceResponse> {

    private final MapPreferenceService service;

    public MapPreferenceController(MapPreferenceService service) {
        this.service = service;
    }

    @Override
    public MapPreferenceResponse getAPI(MapPreferenceRequest request) {
        return service.getAPI(request);
    }
}
