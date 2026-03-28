package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "region", schema = "backend")
public class Region extends StandardEntity {
    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String active;

    @Override
    public String toString() {
        return code + " - " + fullName;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Region region = (Region) o;
        return Objects.equals(getId(), region.getId());
    }
}
