package com.gaspar.store.product.service;

import com.gaspar.store.product.entity.Categoria;
import com.gaspar.store.product.entity.Producto;
import com.gaspar.store.product.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> listAallProductos() {
        return repository.findAll();
    }

    @Override
    public Producto getProducto(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Producto createProducto(Producto producto) {
        producto.setStatus("CREADO");
        producto.setCreado_en(LocalDate.now());
        return repository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        Producto bd = getProducto(producto.getId());
        if(bd==null){
            return null;
        }
        producto.setStatus(bd.getStatus());
        producto.setCreado_en(bd.getCreado_en());
        return repository.save(producto);
    }

    @Override
    @Transactional
    public Producto deleteProducto(Integer id) {
        Producto bd = getProducto(id);
        if(bd==null){
            return null;
        }
        bd.setStatus("DELETED");
        return bd;
    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    @Override
    @Transactional
    public Producto updateStock(Integer id, Double cantidad) {
        Producto bd = getProducto(id);
        if(bd==null){
            return null;
        }
        bd.setStock(cantidad+bd.getStock());
        return bd;
    }
}
