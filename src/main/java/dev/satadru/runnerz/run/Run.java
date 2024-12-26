package dev.satadru.runnerz.run;

import dev.satadru.runnerz.run.Location;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(Integer id,
                  @NotEmpty
                  String title,
                  LocalDateTime startedOn,
                  LocalDateTime completedOn,
                  @Positive
                  Integer miles,
                  Location location) {
    public Run {
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed date must be after started date!!");
        }
        // since we're using the @Positive annotation with the miles field, we don't need to check for negative values
//        if (miles < 0) {
//            throw new IllegalArgumentException("Miles must be greater than 0!!");
//        }
    }
}

