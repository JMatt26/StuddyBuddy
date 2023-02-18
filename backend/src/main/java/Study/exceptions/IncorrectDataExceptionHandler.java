package Study.exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class IncorrectDataExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<String> handleIncorrectDataException(IncorrectDataException ex) {
        return new ResponseEntity<String>(ex.getMessage(), ex.getStatus());
    }
}