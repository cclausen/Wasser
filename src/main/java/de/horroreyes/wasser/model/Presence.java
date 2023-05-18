package de.horroreyes.wasser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

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
    @NotNull
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    @NonNull
    @NotNull
    private Person person;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @NonNull
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Presence presence = (Presence) o;

        return Objects.equals(id, presence.id);
    }

    @Override
    public int hashCode() {
        return 560112052;
    }
}
