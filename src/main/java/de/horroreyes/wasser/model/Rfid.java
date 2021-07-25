package de.horroreyes.wasser.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Rfid {
    @Id
    private String id;
    @ManyToOne
    private Person person;
}
