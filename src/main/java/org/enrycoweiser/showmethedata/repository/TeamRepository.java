package org.enrycoweiser.showmethedata.repository;

import lombok.NonNull;
import org.enrycoweiser.showmethedata.domain.Team;
import org.enrycoweiser.showmethedata.standard.StandardRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends StandardRepository<Team> {
    @Override
    @NonNull
    @EntityGraph(attributePaths = {"region"})
    List<Team> findAll();
}
