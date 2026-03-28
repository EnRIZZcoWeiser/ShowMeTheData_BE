package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

@Getter
@Setter
@Entity
@Table(name = "ban_map_type", schema = "backend")
public class BanMapType extends StandardEntity {
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
}
