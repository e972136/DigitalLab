package com.gaspar.store.cliente.dto;

import com.gaspar.store.cliente.entity.Region;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteRequest {


    @NotEmpty(message = "no puede estar vacio")
    @Size(min=8, max=8, message = "Debe tener 8 caracteres")
    String numeroId;

    @NotNull
    @NotEmpty(message = "no puede estar vacio")
    String nombreCliente;


    @NotNull
    String apellidoCliente;

    @NotNull
    @NotEmpty(message = "no puede estar vacio")
    @Email(message = "debe ser correo")
    String direccion;

    String status;

    @NotNull(message = "debe incluir region")
    Integer region_id;

}
