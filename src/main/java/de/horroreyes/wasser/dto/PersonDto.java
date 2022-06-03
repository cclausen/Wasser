package de.horroreyes.wasser.dto;

import de.horroreyes.wasser.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private Long id;
    private String firstname;
    private String lastname;
    private Status status;
    private boolean presence;
}
