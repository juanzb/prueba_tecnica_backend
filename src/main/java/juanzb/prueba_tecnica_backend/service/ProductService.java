package juanzb.prueba_tecnica_backend.service;

import org.springframework.stereotype.Service;
import juanzb.prueba_tecnica_backend.entity.Products;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    public List<Products> getStaticCatalog() {
        return List.of(
            new Products(1L, "Controlador Lógico", "Módulo principal de automatización", new BigDecimal("850000.00")),
            new Products(2L, "Sensor de Temperatura", "Sensor digital de alta precisión", new BigDecimal("45000.00")),
            new Products(3L, "Módulo Relé 4 Canales", "Actuador para control de cargas", new BigDecimal("32000.00")),
            new Products(4L, "Pantalla Táctil HMI", "Interfaz visual de 7 pulgadas", new BigDecimal("420000.00")),
            new Products(5L, "Fuente de Poder 24V", "Fuente de alimentación industrial", new BigDecimal("115000.00")),
            new Products(6L, "Sensor de Movimiento PIR", "Detector de presencia para interiores", new BigDecimal("28500.00")),
            new Products(7L, "Cámara IP de Seguridad", "Resolución 1080p con visión nocturna", new BigDecimal("180000.00")),
            new Products(8L, "Medidor de Energía", "Monitor de consumo eléctrico DIN", new BigDecimal("210000.00")),
            new Products(9L, "Tira LED Inteligente", "5 metros con controlador WiFi", new BigDecimal("65000.00")),
            new Products(10L, "Actuador de Válvula", "Control motorizado para tuberías", new BigDecimal("135000.00"))
        );
    }
}
