package com.gaspar.store.cliente.service;

import com.gaspar.store.cliente.entity.Cliente;
import com.gaspar.store.cliente.entity.Region;

import java.util.List;

public interface ClienteService {
    List<Cliente> findTodosClientes();
    List<Cliente> findPorRegion(Region region);
    Cliente createCliente(Cliente cliente);
    Cliente setCliente(Cliente cliente);
    Cliente deleteCliente(Cliente cliente);
    Cliente getCliente(Integer id);
}
