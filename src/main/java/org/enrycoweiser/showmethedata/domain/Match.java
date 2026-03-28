package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "match", schema = "backend")
public class Match extends StandardEntity {
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String phase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team_a", nullable = false)
    private Team teamA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team_b", nullable = false)
    private Team teamB;

    @OneToOne
    @JoinColumn(name = "id_ban_map", unique = true, nullable = true)
    private BanMap banMap;
}
