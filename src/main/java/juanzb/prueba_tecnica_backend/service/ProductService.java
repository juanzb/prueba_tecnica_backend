package juanzb.prueba_tecnica_backend.service;

import org.springframework.stereotype.Service;
import juanzb.prueba_tecnica_backend.entity.Products;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    public List<Products> getStaticCatalog() {
        return List.of(
            new Products(1L, "Controlador Lógico", "Módulo principal de automatización", new BigDecimal("4000000")),
            new Products(2L, "Sensor de Temperatura", "Sensor digital de alta precisión", new BigDecimal("45000")),
            new Products(3L, "Módulo Relé 4 Canales", "Actuador para control de cargas", new BigDecimal("320000")),
            new Products(4L, "Pantalla Táctil HMI", "Interfaz visual de 7 pulgadas", new BigDecimal("420000")),
            new Products(5L, "Fuente de Poder 24V", "Fuente de alimentación industrial", new BigDecimal("115000")),
            new Products(6L, "Sensor de Movimiento PIR", "Detector de presencia para interiores", new BigDecimal("28500.00"))
        );
    }
}
