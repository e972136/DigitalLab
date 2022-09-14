package com.gaspar.store.cliente.service;

import com.gaspar.store.cliente.entity.Cliente;
import com.gaspar.store.cliente.entity.Region;
import com.gaspar.store.cliente.repository.ClienteRepository;

import java.util.List;

public class ClienteImple implements ClienteService{
    private final ClienteRepository repositorio;

    public ClienteImple(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Cliente> findTodosClientes() {
        return repositorio.findAll();
    }

    @Override
    public List<Cliente> findPorRegion(Region region) {
        return repositorio.findByRegion(region);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        Cliente bd = repositorio.findByNumeroId(cliente.getNumeroId());
        if(bd!=null){
            return bd;
        }
        cliente.setStatus("CREADO");
        Cliente save = repositorio.save(cliente);
        return save;
    }

    @Override
    public Cliente setCliente(Cliente cliente) {
        Cliente bd = repositorio.findById(cliente.getId()).orElse(null);
        if(bd==null){
            return bd;
        }
        bd.establecerDatos(cliente);
        return repositorio.save(bd);
    }

    @Override
    public Cliente deleteCliente(Cliente cliente) {
        Cliente bd = repositorio.findByNumeroId(cliente.getNumeroId());
        if(bd==null){
            return bd;
        }
        bd.setStatus("DELETED");
        return bd;
    }

    @Override
    public Cliente getCliente(Integer id) {
        Cliente bd = repositorio.findById(id).orElse(null);
        return bd;
    }
}
