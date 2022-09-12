package com.gaspar.store.product.controller;

import com.gaspar.store.product.dto.ProductoRequest;
import com.gaspar.store.product.entity.Categoria;
import com.gaspar.store.product.entity.Producto;
import com.gaspar.store.product.service.CategoriaService;
import com.gaspar.store.product.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/todos")
    List<Producto> traerProductos(){
        return productoService.listAallProductos();
    }

    @PostMapping
    public Producto crearProducto(@RequestBody ProductoRequest producto){
        Categoria categoria = categoriaService.getCategoria(producto.getCategoria_id());
        if(categoria==null){
            return null;
        }
        Producto p=Producto.builder()
                .nombre(producto.getNombre())
                .categoria(categoria)
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();

        return productoService.createProducto(p);
    }
}
