package utez.edu.mx.backend.kernel;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private Object data;
    private String message;
    private boolean error;
    private HttpStatus status;

    public ApiResponse() {
    }

    public ApiResponse(Object data, String message, boolean error, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
