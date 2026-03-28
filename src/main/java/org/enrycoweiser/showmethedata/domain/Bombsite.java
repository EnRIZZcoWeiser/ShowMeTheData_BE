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
@Table(name = "bombiste", schema = "backend")
public class Bombsite extends StandardEntity {
    @Column(nullable = false)
    private String map;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String active;
}
