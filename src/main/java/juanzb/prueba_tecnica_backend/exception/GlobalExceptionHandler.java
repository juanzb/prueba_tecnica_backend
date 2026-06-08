package juanzb.prueba_tecnica_backend.exception;

import juanzb.prueba_tecnica_backend.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponseDto<Void> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ApiResponseDto<>(false, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto<Void> handleBusinessError(RuntimeException ex) {
        return new ApiResponseDto<>(false, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto<Void> handleFormatError() {
        return new ApiResponseDto<>(false, "Datos inválidos: Verifica que el JSON esté escrito correctamente y que los valores sean válidos.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDto<Void> handleValidationError(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new ApiResponseDto<>(false, "Error de validación: " + mensaje);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseDto<Void> handleGenericError(Exception ex) {
        return new ApiResponseDto<>(false, "Ocurrió un error inesperado en el servidor. Por favor, intente más tarde.");
    }
}
