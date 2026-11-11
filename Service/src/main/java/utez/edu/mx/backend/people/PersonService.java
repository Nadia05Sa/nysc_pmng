package utez.edu.mx.backend.people;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utez.edu.mx.backend.kernel.ApiResponse;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll() {
        List<Person> people = personRepository.findAll();
        ApiResponse response = new ApiResponse(
                people,
                "Personas recuperadas",
                false,
                HttpStatus.OK);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Transactional
    public ResponseEntity<ApiResponse> save(Person person) {
        if (person.getName() == null || person.getName().isBlank()) {
            ApiResponse response = new ApiResponse(
                    null,
                    "El nombre es obligatorio",
                    true,
                    HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, response.getStatus());
        }

        person.setId(null);
        Person saved = personRepository.save(person);
        ApiResponse response = new ApiResponse(
                saved,
                "Persona registrada",
                false,
                HttpStatus.CREATED);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
