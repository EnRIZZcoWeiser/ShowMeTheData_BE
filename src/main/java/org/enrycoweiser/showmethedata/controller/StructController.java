package org.enrycoweiser.showmethedata.controller;

import org.enrycoweiser.showmethedata.dto.response.struct.RegionTeamResponse;
import org.enrycoweiser.showmethedata.dto.response.struct.TierEventResponse;
import org.enrycoweiser.showmethedata.service.StructService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/struct")
public class StructController {

    private final StructService service;

    public StructController(StructService service) {
        this.service = service;
    }

    @GetMapping("/region_team")
    public RegionTeamResponse getRegionTeam() {
        return service.getRegionTeam();
    }

    @GetMapping("/tier_event")
    public TierEventResponse getTierEvent() {
        return service.getTierEvent();
    }
}
