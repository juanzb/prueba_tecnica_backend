package juanzb.prueba_tecnica_backend.controller;

import jakarta.validation.Valid;
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
    public ResponseEntity<List<Invoice>> getAll() {
        return ResponseEntity.ok(invoiceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody InvoiceCreateDto data) {
        Invoice savedInvoice = invoiceService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
    }

    @PutMapping("/{id}/recalculate")
    public ResponseEntity<Invoice> recalculateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceRecalculateDto data) {
        Invoice updatedInvoice = invoiceService.recalculateInvoice(id, data);
        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
