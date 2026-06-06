package juanzb.prueba_tecnica_backend.entity;

import java.math.BigDecimal;

public class Products {
  private Long id;
  private String name;  
  private String description;
  private BigDecimal unitPrice;

  public Products( Long id, String name, String description, BigDecimal unitPrice) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.unitPrice = unitPrice;
  }

  public Long getId() { return id; }
  public String getName() { return name; }
  public String getDescription() { return description; }
  public BigDecimal getUnitPrice() { return unitPrice; }

}
