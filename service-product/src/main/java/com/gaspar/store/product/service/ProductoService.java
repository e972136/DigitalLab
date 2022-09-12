package com.gaspar.store.product.service;

import com.gaspar.store.product.entity.Categoria;
import com.gaspar.store.product.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listAallProductos();
    Producto getProducto(Integer id);
    Producto createProducto(Producto producto);
    Producto updateProducto(Producto producto);
    Producto deleteProducto(Integer id);
    List<Producto> findByCategoria(Categoria categoria);
    Producto updateStock(Integer id, Double cantidad);
}
