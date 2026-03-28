package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

@Getter
@Setter
@Entity
@Table(name = "ban_map", schema = "backend")
public class BanMap extends StandardEntity {
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String first;

    @Column(nullable = false)
    private String second;

    @Column(nullable = false)
    private String third;

    @Column(nullable = false)
    private String fourth;

    @Column(nullable = false)
    private String fifth;

    @Column(nullable = false)
    private String sixth;

    @Column(nullable = false)
    private String seventh;

    @Column(nullable = false)
    private String eighth;

    @Column(nullable = false)
    private String decider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ban_map_type", nullable = false)
    private BanMapType banMapType;

    @OneToOne(mappedBy = "banMap", cascade = CascadeType.ALL, optional = true)
    private Match match;
}
