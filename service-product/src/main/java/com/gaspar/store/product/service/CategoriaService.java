package com.gaspar.store.product.service;

import com.gaspar.store.product.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listAallCategorias();
    Categoria getCategoria(Integer id);
    Categoria createCategoria(Categoria producto);
    Categoria updateCategoria(Categoria producto);
    Categoria deleteCategoria(Integer id);

}
