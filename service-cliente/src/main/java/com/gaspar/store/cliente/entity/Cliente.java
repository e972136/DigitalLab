package com.gaspar.store.cliente.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "no puede estar vacio")
    @Size(min=8, max=8, message = "Debe tener 8 caracteres")
    @Column(unique = true, length = 8, nullable = false)
    String numeroId;

    @NotEmpty(message = "no puede estar vacio")
    @Column(nullable = false)
    String nombreCliente;


    @Column(nullable = false)
    String apellidoCliente;

    @NotEmpty(message = "no puede estar vacio")
    @Email(message = "debe ser correo")
    @Column(unique = true,nullable = false)
    String direccion;

    String status;

    @NotNull(message = "debe incluir region")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    Region region;


    public void establecerDatos(Cliente otro) {
        this.numeroId = otro.numeroId;
        this.nombreCliente = otro.nombreCliente;
        this.apellidoCliente = otro.apellidoCliente;
        this.direccion = otro.direccion;
        this.status = "UPDATED";
        this.region = otro.region;
    }
}
