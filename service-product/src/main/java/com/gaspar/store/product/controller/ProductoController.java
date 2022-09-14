package com.gaspar.store.product.controller;

import com.gaspar.store.product.dto.ProductoRequest;
import com.gaspar.store.product.entity.Categoria;
import com.gaspar.store.product.entity.Producto;
import com.gaspar.store.product.service.CategoriaService;
import com.gaspar.store.product.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    ResponseEntity<List<Producto>> traerProductos(@RequestParam(name = "categoriaId", required = false) Integer categoriaId) {
        List<Producto> productos = null;
        if (categoriaId == null) {
            productos = productoService.listAallProductos();
        } else {
            productos = productoService.findByCategoria(Categoria.builder().id(categoriaId).build());
        }
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    ResponseEntity<Producto> obtenerProducto(@PathVariable("id") Integer idProducto) {
        Producto producto = productoService.getProducto(idProducto);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoRequest producto, BindingResult result) {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,formatMessage(result));
        }
        Categoria categoria = categoriaService.getCategoria(producto.getCategoria_id());
        if (categoria == null) {
            return null;
        }
        Producto p = Producto.builder()
                .nombre(producto.getNombre())
                .categoria(categoria)
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();

        p = productoService.createProducto(p);

        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Integer idProducto,@Valid  @RequestBody ProductoRequest producto, BindingResult result) {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,formatMessage(result));
        }
        Categoria categoria = categoriaService.getCategoria(producto.getCategoria_id());
        if (categoria == null) {
            return null;
        }
        Producto p = Producto.builder()
                .id(idProducto)
                .nombre(producto.getNombre())
                .categoria(categoria)
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
        p = productoService.updateProducto(p);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable("id") Integer idProducto) {
        Producto p = productoService.deleteProducto(idProducto);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }


    @GetMapping("/{id}/stock")
    public ResponseEntity<Producto> actualizarStockProducto(@PathVariable("id") Integer idProducto,
                                                            @RequestParam(value = "cantidad",defaultValue = "0.0") Double cantidad){
        Producto p = productoService.updateStock(idProducto, cantidad);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    String formatMessage(BindingResult result){
        String collect = result.getFieldErrors().stream().map(err -> {
            return err.getField() + "->" + err.getDefaultMessage();
        }).collect(Collectors.joining(","));
        return collect;
    }
}