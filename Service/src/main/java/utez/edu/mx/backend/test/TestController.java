package utez.edu.mx.backend.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utez.edu.mx.backend.kernel.ApiResponse;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> test() {
        return testService.getMessage();
    }

    @GetMapping("/retrieve")
    public ResponseEntity<ApiResponse> retrieve() {
        return testService.retrieve();
    }
}
