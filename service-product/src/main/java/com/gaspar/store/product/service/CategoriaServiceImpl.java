package com.gaspar.store.product.service;

import com.gaspar.store.product.entity.Categoria;
import com.gaspar.store.product.entity.Producto;
import com.gaspar.store.product.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Categoria> listAallCategorias() {
        return repository.findAll();
    }

    @Override
    public Categoria getCategoria(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria updateCategoria(Categoria categoria) {
        Categoria bd = getCategoria(categoria.getId());
        if(bd==null){
            return null;
        }
        return repository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria deleteCategoria(Integer id) {
        Categoria bd = getCategoria(id);
        if(bd==null){
            return null;
        }
        return bd;
    }
}
