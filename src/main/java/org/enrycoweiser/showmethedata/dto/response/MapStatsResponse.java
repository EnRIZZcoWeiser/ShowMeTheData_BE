package org.enrycoweiser.showmethedata.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardResponse;

import java.util.List;

@Getter
@Setter
public class MapStatsResponse extends StandardResponse {
    private List<MapStatsTile> attackTiles;
    private List<MapStatsTile> defenceTiles;
}
