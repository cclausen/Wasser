package de.horroreyes.wasser.dto;

import de.horroreyes.wasser.model.enums.Lifeguard;
import de.horroreyes.wasser.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonDto {
    private Long id;
    private String firstname;
    private String lastname;
    private Status status = Status.ACTIVE;
    private Lifeguard lifeguard;
    private LocalDate lifeguardFrom;
    private LocalDate fitness;
}
