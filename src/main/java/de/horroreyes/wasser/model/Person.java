package de.horroreyes.wasser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.horroreyes.wasser.model.enums.Lifeguard;
import de.horroreyes.wasser.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NonNull
    private String firstname;
    @NotNull
    @NonNull
    private String lastname;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Lifeguard lifeguard;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lifeguardFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fitness;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Person person = (Person) o;

        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return 1422108840;
    }
}
