package org.enrycoweiser.showmethedata.repository;

import org.enrycoweiser.showmethedata.domain.BanMap;
import org.enrycoweiser.showmethedata.repository.custom.MyBanMapRepository;
import org.enrycoweiser.showmethedata.standard.StandardRepository;

public interface BanMapRepository extends StandardRepository<BanMap>, MyBanMapRepository {

}
