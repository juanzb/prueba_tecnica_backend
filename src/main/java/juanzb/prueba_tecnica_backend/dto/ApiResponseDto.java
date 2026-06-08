package juanzb.prueba_tecnica_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponseDto() {}

    public ApiResponseDto(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters
    public boolean getSuccess() { return success; }
    public String getMessage() { return message;}
    public T getData() { return data;}

    // Setters
    public void setData(T data) { this.data = data; }
    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
}
