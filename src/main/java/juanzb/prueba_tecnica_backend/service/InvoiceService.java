package juanzb.prueba_tecnica_backend.service;

import juanzb.prueba_tecnica_backend.dto.InvoiceCreateDto;
import juanzb.prueba_tecnica_backend.dto.InvoiceRecalculateDto;
import juanzb.prueba_tecnica_backend.entity.Invoice;
import juanzb.prueba_tecnica_backend.entity.InvoiceDetail;
import juanzb.prueba_tecnica_backend.exception.ResourceNotFoundException;
import juanzb.prueba_tecnica_backend.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final EntityManager entityManager;
    private static final BigDecimal TAX_RATE = new BigDecimal("0.19");

    public InvoiceService(InvoiceRepository invoiceRepository, EntityManager entityManager) {
        this.invoiceRepository = invoiceRepository;
        this.entityManager = entityManager;
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(Long id) {
        return invoiceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada"));
    }

    @Transactional
    public Invoice create(InvoiceCreateDto dataInvoice) {
        Invoice invoice = new Invoice();
        
        if (dataInvoice.details() != null) {
            BigDecimal subtotal = new BigDecimal("0");
            for (InvoiceCreateDto.DetailDto detailDto : dataInvoice.details()) {
                InvoiceDetail detail = new InvoiceDetail();
                detail.setInvoice(invoice); 
                detail.setProductName(detailDto.productName());
                detail.setQuantity(detailDto.quantity());
                detail.setUnitPrice(detailDto.unitPrice());
                
                BigDecimal totalPrice = detailDto.unitPrice()
                        .multiply(new BigDecimal(detailDto.quantity()))
                        .setScale(2, RoundingMode.HALF_UP);
                detail.setTotalPrice(totalPrice);
                
                invoice.getDetails().add(detail);
                subtotal = subtotal.add(totalPrice);
            }

            invoice.setClient(dataInvoice.client());
            invoice.setDescription(dataInvoice.description());
            invoice.setSubtotal(subtotal);
            invoice.setTaxAmount(subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP));
            invoice.setTotalAmount(subtotal.add(invoice.getTaxAmount()));
        }

        return invoiceRepository.save(invoice);
    }

    public Invoice delete(Long id) {
        Invoice invoiceTodelete = this.findById(id);
        invoiceRepository.deleteById(id);
        return invoiceTodelete;
    }

    public Invoice recalculateInvoice(Long id, InvoiceRecalculateDto dataInvoice) {
        Invoice invoice = this.findById(id);        
        invoice.getDetails().size();
        entityManager.detach(invoice);

        BigDecimal oldSubtotal = invoice.getSubtotal();
        BigDecimal newSubtotal = dataInvoice.newSubtotal();
        
        if (newSubtotal.compareTo(oldSubtotal) > 0) {
            BigDecimal difference = newSubtotal.subtract(oldSubtotal);
            BigDecimal maxAllowed = dataInvoice.userRole().getMaxIncrement();
    
            if (difference.compareTo(maxAllowed) > 0) {
                throw new RuntimeException("El incremento del subtotal excede el máximo permitido para el usuario de tipo " + dataInvoice.userRole().name());
            }
        }

        BigDecimal factor = newSubtotal.divide(oldSubtotal, 8, RoundingMode.HALF_UP);

        for (InvoiceDetail detail : invoice.getDetails()) {
            BigDecimal newTotalPrice = detail.getTotalPrice().multiply(factor).setScale(2, RoundingMode.HALF_UP);
            detail.setTotalPrice(newTotalPrice);

            BigDecimal newUnitPrice = newTotalPrice.divide(new BigDecimal(detail.getQuantity()), 2, RoundingMode.HALF_UP);
            if (newUnitPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("El precio recalculado de un detalle no puede ser menor o igual a cero. Ingrese un valor de subtotal mayor.");
            }
            detail.setUnitPrice(newUnitPrice);
        }

        invoice.setSubtotal(newSubtotal);
        BigDecimal newTaxAmount = newSubtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
        invoice.setTaxAmount(newTaxAmount);
        invoice.setTotalAmount(newSubtotal.add(newTaxAmount));

        return invoice;
    }

    @Transactional
    public Invoice recalculateInvoiceToSave(Long id, InvoiceRecalculateDto dataInvoice) {
        Invoice invoice = this.recalculateInvoice(id, dataInvoice);
        return invoiceRepository.save(invoice);
    }
}
