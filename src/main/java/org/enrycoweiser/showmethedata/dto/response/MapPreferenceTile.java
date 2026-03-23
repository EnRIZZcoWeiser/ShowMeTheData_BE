package org.enrycoweiser.showmethedata.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapPreferenceTile {
    private String mapName;
    private int ban;
    private int oppBan;
    private int pick;
    private int oppPick;
    private int decider;
    private double value;
    private int rank;
}
