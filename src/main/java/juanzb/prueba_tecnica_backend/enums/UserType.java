package juanzb.prueba_tecnica_backend.enums;

import java.math.BigDecimal;

public enum UserType {
    OPERATOR(new BigDecimal("20000")),
    SUPERVISOR(new BigDecimal("50000"));

    private final BigDecimal maxIncrement;

    UserType(BigDecimal maxIncrement) {
        this.maxIncrement = maxIncrement;
    }

    public BigDecimal getMaxIncrement() {
        return maxIncrement;
    }
}
