package org.enrycoweiser.showmethedata.repository.custom;

import org.enrycoweiser.showmethedata.domain.Bombsite;

import java.util.List;

public interface MyBombsiteRepository {
    public abstract List<Bombsite> retrieveActiveBombsite();
}
