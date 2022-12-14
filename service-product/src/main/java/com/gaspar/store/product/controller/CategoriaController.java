package com.gaspar.store.product.controller;

import com.gaspar.store.product.entity.Categoria;
import com.gaspar.store.product.entity.Producto;
import com.gaspar.store.product.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    ResponseEntity<List<Categoria>> traerCategorias(){
        List<Categoria> categorias = service.listAallCategorias();
        if(categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);

    }
}
