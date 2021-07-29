package de.horroreyes.wasser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
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
}
