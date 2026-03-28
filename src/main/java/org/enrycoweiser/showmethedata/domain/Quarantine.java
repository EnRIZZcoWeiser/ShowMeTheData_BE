package org.enrycoweiser.showmethedata.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.enrycoweiser.showmethedata.standard.StandardEntity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "quarantine", schema = "backend")
public class Quarantine extends StandardEntity {
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operator", nullable = false)
    private Operator operator;
}
