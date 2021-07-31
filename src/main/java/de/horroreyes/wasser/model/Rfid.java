package de.horroreyes.wasser.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Rfid {
    @Id
    private String id;
    @ManyToOne
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rfid rfid = (Rfid) o;

        return Objects.equals(id, rfid.id);
    }

    @Override
    public int hashCode() {
        return 137704617;
    }
}
