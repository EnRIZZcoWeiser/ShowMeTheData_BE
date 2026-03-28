package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "played_map", schema = "backend")
public class PlayedMap extends StandardEntity {
    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private String code;

    @Column(nullable = false, unique = false)
    private Integer number;

    @Column(nullable = false, unique = false)
    private String closed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_match", nullable = false)
    private Match match;

    @OneToMany(mappedBy = "playedMap", fetch = FetchType.LAZY)
    private List<OperatorBan> operatorBans = new ArrayList<>();

    @OneToMany(mappedBy = "playedMap", fetch = FetchType.LAZY)
    private List<Round> rounds = new ArrayList<>();
}
