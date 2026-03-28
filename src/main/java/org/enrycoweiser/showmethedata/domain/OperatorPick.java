package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

@Getter
@Setter
@Entity
@Table(name = "operator_pick", schema = "backend")
public class OperatorPick extends StandardEntity {
    @Column(nullable = false, unique = false)
    private String player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_round", nullable = false)
    private Round round;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operator", nullable = false)
    private Operator operator;
}
