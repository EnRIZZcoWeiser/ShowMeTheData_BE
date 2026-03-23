package org.enrycoweiser.showmethedata.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardRequest;

@Getter
@Setter
public class MapPreferenceRequest extends StandardRequest {
    private String team;
    private String fromDate;
    private String toDate;
}
