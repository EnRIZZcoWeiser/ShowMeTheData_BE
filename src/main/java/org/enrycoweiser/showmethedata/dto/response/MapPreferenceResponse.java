package org.enrycoweiser.showmethedata.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardResponse;

import java.util.List;

@Getter
@Setter
public class MapPreferenceResponse extends StandardResponse {
    private List<MapPreferenceTile> tiles;
}
