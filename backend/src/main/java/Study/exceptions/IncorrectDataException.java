package Study.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class IncorrectDataException extends RuntimeException {
    private HttpStatus status;
    
    public IncorrectDataException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
