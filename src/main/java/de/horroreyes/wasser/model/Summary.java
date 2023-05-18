package de.horroreyes.wasser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record Summary(
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime created,
        @NotNull
        Day day,
        @NotNull
        Set<Person> persons,
        @NotNull
        Place place,
        @NotNull
        List<Presence> presences,
        @NotNull
        long totalSeconds,
        @NotNull
        double totalHours,
        @NotNull
        long openPresences
) {
}
