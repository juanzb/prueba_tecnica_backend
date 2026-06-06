package juanzb.prueba_tecnica_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleBusinessErrors(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        
        HttpStatus status = ex.getMessage().contains("no encontrada") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        
        return ResponseEntity.status(status).body(response);
    }
}
