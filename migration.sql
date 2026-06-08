DROP TABLE IF EXISTS `invoice_details`;
DROP TABLE IF EXISTS `invoices`;

CREATE TABLE `invoices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subtotal` decimal(15,2) NOT NULL,
  `tax_amount` decimal(15,2) NOT NULL,
  `total_amount` decimal(15,2) NOT NULL,
  `client` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `invoice_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `total_price` decimal(15,2) NOT NULL,
  `unit_price` decimal(15,2) NOT NULL,
  `invoice_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_invoice_details_invoice_id` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `invoices` (`id`, `subtotal`, `tax_amount`, `total_amount`, `client`, `created_at`, `description`, `updated_at`) 
VALUES (1, 4365000, 829350, 5194350, 'Cliente Uno', '2026-06-07 21:02:32.614362', '', '2026-06-07 21:03:14.157399');

INSERT INTO `invoice_details` (`id`, `product_name`, `quantity`, `total_price`, `unit_price`, `invoice_id`) 
VALUES 
  (111, 'Controlador Logico', 1, 4000000, 4000000, 1),
  (112, 'Modulo Rele 4 Canales', 1, 320000, 320000, 1),
  (113, 'Sensor de Temperatura', 1, 45000, 45000, 1);