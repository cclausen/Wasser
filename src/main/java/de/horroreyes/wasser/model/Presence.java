package de.horroreyes.wasser.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.Hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
