package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

@Getter
@Setter
@Entity
@Table(name = "roster", schema = "backend")
public class Roster extends StandardEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", nullable = false)
    private Team team;

    @Column(nullable = false, unique = true)
    private String player1;

    @Column(nullable = false, unique = true)
    private String player2;

    @Column(nullable = false, unique = true)
    private String player3;

    @Column(nullable = false, unique = true)
    private String player4;

    @Column(nullable = false, unique = true)
    private String player5;
}
