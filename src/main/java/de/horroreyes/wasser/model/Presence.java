package de.horroreyes.wasser.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Presence {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @NonNull
    private Person person;
    @ManyToOne
    @NonNull
    private Place place;
    @NonNull
    private LocalDateTime start;
    private LocalDateTime end;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Presence presence = (Presence) o;

        return Objects.equals(id, presence.id);
    }

    @Override
    public int hashCode() {
        return 560112052;
    }
}
