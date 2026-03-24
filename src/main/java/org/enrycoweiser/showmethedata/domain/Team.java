package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "team")
public class Team extends StandardEntity {
    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region", nullable = false)
    private Region region;

    @Override
    public String toString() {
        return code + " - " + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Team team = (Team) o;
        return Objects.equals(getId(), team.getId());
    }
}
