package juanzb.prueba_tecnica_backend.controller;

import jakarta.validation.Valid;
import juanzb.prueba_tecnica_backend.dto.ApiResponseDto;
import juanzb.prueba_tecnica_backend.dto.InvoiceCreateDto;
import juanzb.prueba_tecnica_backend.dto.InvoiceRecalculateDto;
import juanzb.prueba_tecnica_backend.entity.Invoice;
import juanzb.prueba_tecnica_backend.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Invoice>>> getAll() {
        List<Invoice> invoices = invoiceService.findAll();
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Facturas recuperadas exitosamente.", invoices));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Invoice>> getById(@PathVariable("id") Long id) {
        Invoice invoice = invoiceService.findById(id);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Factura encontrada exitosamente.", invoice));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<Invoice>> createInvoice(@Valid @RequestBody InvoiceCreateDto data) {
        Invoice savedInvoice = invoiceService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(true, "Factura creada exitosamente.", savedInvoice));
    }

    // @PutMapping("/{id}/recalculate")
    // public ResponseEntity<ApiResponse<Invoice>> recalculateInvoice(@PathVariable("id") Long id, @Valid @RequestBody InvoiceRecalculateDto data) {
    //     Invoice updatedInvoice = invoiceService.recalculateInvoice(id, data);
    //     return ResponseEntity.ok(new ApiResponse<>(true, "Factura recalculada exitosamente (sin guardar).", updatedInvoice));
    // }

    @PutMapping("/{id}/recalculate-save")
    public ResponseEntity<ApiResponseDto<Invoice>> recalculateInvoiceToSave(@PathVariable("id") Long id, @Valid @RequestBody InvoiceRecalculateDto data) {
        Invoice updatedInvoice = invoiceService.recalculateInvoiceToSave(id, data);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Factura recalculada y actualizada exitosamente.", updatedInvoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Invoice>> deleteInvoice(@PathVariable("id") Long id) {
        Invoice deletedInvoice = invoiceService.delete(id);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Factura eliminada exitosamente.", deletedInvoice));
    }
}
