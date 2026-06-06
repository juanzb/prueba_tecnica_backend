package juanzb.prueba_tecnica_backend.dto;

import juanzb.prueba_tecnica_backend.enums.UserType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record InvoiceRecalculateDto(
    @NotNull(message = "El nuevo subtotal es requerido.")
    @Positive(message = "El subtotoal debe ser mayor que cero (0).")
    BigDecimal newSubtotal,

    @NotNull(message = "El rol de usuario es requerido.")
    UserType userRole
) {}
