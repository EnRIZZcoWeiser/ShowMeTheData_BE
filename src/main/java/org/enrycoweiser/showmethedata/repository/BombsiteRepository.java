package org.enrycoweiser.showmethedata.repository;

import org.enrycoweiser.showmethedata.domain.Bombsite;
import org.enrycoweiser.showmethedata.repository.custom.MyBombsiteRepository;
import org.enrycoweiser.showmethedata.standard.StandardRepository;

public interface BombsiteRepository extends StandardRepository<Bombsite>, MyBombsiteRepository {
}
