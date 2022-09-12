package com.gaspar.store.product.repository;

import com.gaspar.store.product.entity.Categoria;
import com.gaspar.store.product.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    public List<Producto> findByCategoria(Categoria categoria);
}
