package com.gaspar.store.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {
    @NotEmpty
    String nombre;


    String descripcion;

    @Positive
    Double stock;

    BigDecimal precio;

    @NotNull
    Integer categoria_id;
}
