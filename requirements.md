# Descripción del Problema

Se requiere construir una funcionalidad que permita recalcular el valor total de una
factura ya existente, distribuyendo proporcionalmente la diferencia entre sus
productos (detalles).
Cuando un usuario digite un nuevo Subtotal General para la factura (ya sea mayor o
menor al actual), el sistema debe recalcular el valor de cada producto del detalle
para que la sumatoria total coincida exactamente con el nuevo valor ingresado,
aplicando luego los impuestos correspondientes (IVA).
Reglas:

### 1. Modificación proporcional:

- Si una factura de subtotal $80,000 se modifica a $60,000, la reducción
  del 25% se debe aplicar proporcionalmente al valor de cada producto
  en el detalle.
- Las modificaciones pueden ser hacia arriba (incremento) o hacia abajo
  (descuento).

### 2. Restricciones y Topes por Tipo de Usuario:

- Usuario Tipo A (Operador): Solo puede incrementar el subtotal de la
  factura hasta un máximo de $20,000 por encima del valor original.
- Usuario Tipo B (Supervisor): Puede incrementar el subtotal de la
  factura hasta un máximo de $50,000 por encima del valor original.

<br/>

# Especificaciones Técnicas

### Backend (Java / Spring Boot)

#### Arquitectura:

- Estructura limpia en capas (Controller, Service, Repository,
  Model/Entity).

#### Base de Datos

- Relacional (puedes usar H2 en memoria o PostgreSQL/MySQL
  para facilidad de despliegue). Se valorará el mapeo adecuado con
  JPA/Hibernate.

#### Validaciones

- Realizar validaciones en ambos frentes del proyecto. Retornar
  códigos de estado HTTP correctos.
