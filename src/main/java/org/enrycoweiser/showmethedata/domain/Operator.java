package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "operator", schema = "backend")
public class Operator extends StandardEntity {
    @Column(nullable = false)
    private String role;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private LocalDate from;

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Operator operator = (Operator) o;
        return Objects.equals(getId(), operator.getId());
    }
}
