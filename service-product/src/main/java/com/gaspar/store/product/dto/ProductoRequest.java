package com.gaspar.store.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {
    String nombre;
    String descripcion;
    Double stock;
    BigDecimal precio;
    Integer categoria_id;
}
