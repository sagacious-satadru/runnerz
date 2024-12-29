package dev.satadru.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository // an annotation used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects
public class RunRepository {
//    private List<Run> runs = new ArrayList<>();
    private static final Logger log = Logger.getLogger(RunRepository.class.getName());
    private final JdbcClient jdbcClient;
    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM Run WHERE id =:id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

//    public void create(Run run)
//    {
//        var updated = jdbcClient.sql("INSERT INTO Run(id, title, startedOn, completedOn, miles, location) VALUES (?, ?, ?, ?, ?, ?)")
//                .param(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location()))
//                .update(); // update returns the no of rows that were affected by the query
//        Assert.state(updated == 1, "Failed to insert run" + run.title());
//    }

    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id, title, started_on, completed_on, miles, location) VALUES (:id, :title, :startedOn, :completedOn, :miles, :location)")
                .param("id", run.id())
                .param("title", run.title())
                .param("startedOn", run.startedOn())
                .param("completedOn", run.completedOn())
                .param("miles", run.miles())
                .param("location", run.location().toString())
                .update();
        Assert.state(updated == 1, "Failed to insert run " + run.title());
    }
//    public void update(Run run, Integer id) {
//        var updated = jdbcClient.sql("UPDATE Run SET title = ?, startedOn = ?, completedOn = ?, miles = ?, location = ? WHERE id = ?")
//                .param(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
//                .update();
//        Assert.state(updated == 1, "Failed to update run " + id);
//    }
    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE Run SET title = :title, started_on = :startedOn, completed_on = :completedOn, miles = :miles, location = :location WHERE id = :id")
                .param("title", run.title())
                .param("startedOn", run.startedOn())
                .param("completedOn", run.completedOn())
                .param("miles", run.miles())
                .param("location", run.location().toString())
                .param("id", id)
                .update();
        Assert.state(updated == 1, "Failed to update run " + id);
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Run WHERE id = :id")
                .param("id", id)
                .update();
        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM Run")
                .query(Integer.class)
                .single();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("SELECT * FROM Run WHERE location =:location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

}
