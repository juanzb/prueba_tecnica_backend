package juanzb.prueba_tecnica_backend.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record InvoiceCreateDto(

    @NotEmpty(message = "El nombre del cliente no puede estar vacío")
    String client,

    @Nullable
    String description,
 
    @NotEmpty(message = "La factura debe tener al menos un producto")
    @Valid 
    List<DetailDto> details

) {
    public record DetailDto(
        @NotBlank(message = "El nombre del producto no puede estar vacío")
        String productName,

        @NotNull(message = "El precio unitario es requerido")
        @Positive(message = "El precio unitario debe ser positivo")
        BigDecimal unitPrice,

        @NotNull(message = "La cantidad es requerida")
        @Positive(message = "La cantidad debe ser positiva")
        Integer quantity
    ) {}
}
