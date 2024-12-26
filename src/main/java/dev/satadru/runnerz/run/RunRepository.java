package dev.satadru.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // an annotation used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer Id) {
        return runs.stream().filter(run -> run.id().equals(Id)).findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer Id) {
        Optional<Run> existingRun = findById(Id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer Id) {
        runs.removeIf(run -> run.id().equals(Id));
    }

    @PostConstruct // (56:20) an annotation used on a method, which needs to execute after the dependency injection is done, to perform any initialization (like populating the list of runs)
    private void init() {
        runs.add(new Run(1, "Monday Morning Run", LocalDateTime.now(), LocalDateTime.now().plus(45, ChronoUnit.HOURS), 5, Location.OUTDOOR));
        runs.add(new Run(2, "Wednesday Evening Run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.HOURS), 10, Location.OUTDOOR));
    }
}
