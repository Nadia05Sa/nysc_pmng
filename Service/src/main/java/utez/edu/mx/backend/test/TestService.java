package utez.edu.mx.backend.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import utez.edu.mx.backend.kernel.ApiResponse;

@Service
public class TestService {

    @Value("${api.base.url}")
    private String API_URL;

    private final RestClient restClient;

    public TestService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<ApiResponse> getMessage() {
        ApiResponse response = restClient.get()
                .uri(API_URL)
                .retrieve()
                .body(ApiResponse.class);

        return new ResponseEntity<>(response, response.getStatus());
    }

    public ResponseEntity<ApiResponse> retrieve() {
        ApiResponse response = new ApiResponse(
                "Datos recuperados desde este backend",
                "Retrieve correcto",
                false,
                HttpStatus.OK);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
