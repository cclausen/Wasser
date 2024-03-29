package de.horroreyes.wasser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Duty {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Place place;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int amountOfVisitors;
    private int firstResponder;
    private int reanimations;
    private int aed;
    private int helpPersons;
    private int wasMedical;
    private int wasLifeThreatening;
    private int wasInWater;
    private int wasLifeThreateningForHelper;
    private int wasDead;
    private int wasDrowned;
    private int helpThings;
    private int helpEnvironment;
    private int helpAnimals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Duty duty = (Duty) o;

        return Objects.equals(id, duty.id);
    }

    @Override
    public int hashCode() {
        return 1056311305;
    }

}
