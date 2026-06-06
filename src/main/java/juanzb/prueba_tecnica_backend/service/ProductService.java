package juanzb.prueba_tecnica_backend.service;

import org.springframework.stereotype.Service;

import juanzb.prueba_tecnica_backend.entity.Products;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    public List<Products> getStaticCatalog() {
        return List.of(
            new Products(1L, "Product 1", "Description 1", new BigDecimal("10.99")),
            new Products(2L, "Product 2", "Description 2", new BigDecimal("20.99")),
            new Products(3L, "Product 3", "Description 3", new BigDecimal("30.99")),
            new Products(4L, "Product 4", "Description 4", new BigDecimal("40.99")),
            new Products(5L, "Product 5", "Description 5", new BigDecimal("50.99")),
            new Products(6L, "Product 6", "Description 6", new BigDecimal("60.99")),
            new Products(7L, "Product 7", "Description 7", new BigDecimal("70.99")),
            new Products(8L, "Product 8", "Description 8", new BigDecimal("80.99")),
            new Products(9L, "Product 9", "Description 9", new BigDecimal("90.99")),
            new Products(10L, "Product 10", "Description 10", new BigDecimal("100.99"))
        );
    }
}
