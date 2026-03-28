package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

@Getter
@Setter
@Entity
@Table(name = "round", schema = "backend")
public class Round extends StandardEntity {
    @Column(name = "entry_time", nullable = false, unique = false)
    private Integer entryTime;

    @Column(name = "end_time", nullable = false, unique = false)
    private Integer endTime;

    @Column(name = "plant_time", unique = false)
    private Integer plantTime;

    @Column(unique = false)
    private String plant;

    @Column(name = "team_a_role", unique = false)
    private String teamARole;

    @Column(name = "team_b_role", unique = false)
    private String teamBRole;

    @Column(nullable = false, unique = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_map", nullable = false)
    private PlayedMap playedMap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bombsite", nullable = false)
    private Bombsite bombsite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team_entry", nullable = false)
    private Team entryTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team_win", nullable = false)
    private Team winTeam;
}
