package de.horroreyes.wasser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Place {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NonNull
    @Column(unique = true)
    private String name;
    @NotNull
    @NonNull
    @NotBlank
    private String district;
    @NotNull
    @NonNull
    @NotBlank
    private String officialName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Place place = (Place) o;

        return Objects.equals(id, place.id);
    }

    @Override
    public int hashCode() {
        return 1945780767;
    }
}
