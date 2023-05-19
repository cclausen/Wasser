package de.horroreyes.wasser.model;

import de.horroreyes.wasser.model.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NonNull
    private Roles name;

}
