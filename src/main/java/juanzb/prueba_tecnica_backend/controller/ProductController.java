package juanzb.prueba_tecnica_backend.controller;

import juanzb.prueba_tecnica_backend.dto.ApiResponseDto;
import juanzb.prueba_tecnica_backend.entity.Products;
import juanzb.prueba_tecnica_backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Products>>> getStaticCatalog() {
        List<Products> products = productService.getStaticCatalog();
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Catálogo de productos recuperado exitosamente.", products));
    }
}
