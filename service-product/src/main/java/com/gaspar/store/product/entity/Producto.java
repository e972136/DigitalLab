package com.gaspar.store.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name="tbl_productos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String nombre;

    String descripcion;

    Double stock;

    BigDecimal precio;

    String status;

    //@Temporal(TemporalType.TIMESTAMP)
    LocalDate creado_en;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoria_id")
    Categoria categoria;
}
