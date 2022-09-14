package com.gaspar.store.cliente.repository;

import com.gaspar.store.cliente.entity.Cliente;
import com.gaspar.store.cliente.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    Cliente findByNumeroId(String numeroId);
    List<Cliente> findByApellidoCliente(String apellidoCliente);
    List<Cliente> findByRegion(Region region);
}
