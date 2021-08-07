package de.horroreyes.wasser.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.Hibernate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue()
    private long id;

    @NonNull
    @NotEmpty(message = "username is required")
    @Column(unique = true)
    private String username;

    @NonNull
    @NotEmpty(message = "email is required")
    @Column(unique = true)
    private String email;

    @NonNull
    @NotEmpty(message = "password is required")
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}
