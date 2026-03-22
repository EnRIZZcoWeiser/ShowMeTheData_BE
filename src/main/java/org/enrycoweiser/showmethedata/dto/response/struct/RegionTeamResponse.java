package org.enrycoweiser.showmethedata.dto.response.struct;

import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardResponse;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RegionTeamResponse extends StandardResponse {
    private Map<String, List<String>> regionTeam;
}
