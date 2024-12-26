package dev.satadru.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController // this annotation is essentially saying - "I am a class that responds to a request, i.e. returns responses"
@RequestMapping("/api/runs") // this annotation is used to map web requests onto specific handler classes and/or handler methods
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    //    List<Run> runs = new ArrayList<>(); // the Run repository isn't available to the public, it is the controller which takes in the requests (a request like - "I need all the runs (say)"), and then it fetches the data from the repository and returns it
    @GetMapping("")
    // this annotation is used to map the HTTP GET requests onto specific handler methods  (this is a RESTful API)
    List<Run> findAll() {
        return runRepository.findAll();
    }


    @GetMapping("/{id}")
        // this annotation is used to map the HTTP GET requests onto specific handler methods  (this is a RESTful API)
    Run findById(@PathVariable Integer id) {
        var run = runRepository.findById(id);
        if (run.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
            throw new RunNotFoundException(id);
        }
        return run.get();
    }

    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    // this annotation is used to map the HTTP POST requests onto specific handler methods  (this is a RESTful API)
    void create(@Valid @RequestBody Run run) { // In the `RunController` when someone creates a new run, use the @Valid annotation to go ahead and validate it. This ensures that before we even call the `runRepository`'s `create` method, Spring is going to check and validate if the `Run` object that's getting passed in is valid based on the rules that we've written/provided. If the rules are not satisfied, then it won't go onto the next line where we've written `runRepository.create(run)`; - its' going to instead throw a `400` bad request, letting the user know that something went wrong.
        runRepository.create(run);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT) // this annotation is used to set the HTTP status code in the HTTP response - this is used when we have no content to send back
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run, id);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }
}
