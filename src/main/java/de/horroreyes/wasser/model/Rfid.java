package de.horroreyes.wasser.model;

import java.util.Objects;

import org.hibernate.Hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
