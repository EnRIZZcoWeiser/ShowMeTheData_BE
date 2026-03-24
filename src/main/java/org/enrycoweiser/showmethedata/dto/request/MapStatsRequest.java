package org.enrycoweiser.showmethedata.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardRequest;

@Getter
@Setter
public class MapStatsRequest extends StandardRequest {
    private String event;
    private String team;
    private String fromDate;
    private String toDate;
}
