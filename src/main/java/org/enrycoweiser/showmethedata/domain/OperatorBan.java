package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

@Getter
@Setter
@Entity
@Table(name = "operator_ban", schema = "backend")
public class OperatorBan extends StandardEntity {
    @Column(nullable = false, unique = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ban1", nullable = false)
    private Operator ban1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ban2", nullable = false)
    private Operator ban2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ban3", nullable = false)
    private Operator ban3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_map", nullable = false)
    private PlayedMap playedMap;
}
