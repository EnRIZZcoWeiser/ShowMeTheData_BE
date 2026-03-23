package org.enrycoweiser.showmethedata.repository.custom;

import org.enrycoweiser.showmethedata.domain.BanMap;
import java.util.List;

public interface MyBanMapRepository {
    public abstract List<BanMap> retrieveByTeamDates(String team, String fromDate, String toDate);
}
