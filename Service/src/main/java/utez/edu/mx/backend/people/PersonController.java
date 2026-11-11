package utez.edu.mx.backend.people;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utez.edu.mx.backend.kernel.ApiResponse;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        return personService.findAll();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Person person) {
        return personService.save(person);
    }
}
