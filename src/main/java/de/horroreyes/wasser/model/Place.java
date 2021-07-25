package de.horroreyes.wasser.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Place {
    @Id
    private Long id;
    private String name;

}
