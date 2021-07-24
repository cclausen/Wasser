package de.horroreyes.wasser.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {
    @Id @GeneratedValue
    private Long id;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;

}
