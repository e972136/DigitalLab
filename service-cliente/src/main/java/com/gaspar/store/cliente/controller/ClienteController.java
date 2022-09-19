package com.gaspar.store.cliente.controller;

import com.gaspar.store.cliente.entity.Cliente;
import com.gaspar.store.cliente.entity.Region;
import com.gaspar.store.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listadoClientes(@RequestParam(name="regionId",required= false) Integer regionId){
        List<Cliente> all;
        if(regionId==null){
            all = service.findTodosClientes();
        }else {
            Region x = Region.builder().id(regionId).build();
            all = service.findPorRegion(x);
        }

        if(all.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(all);

    }

    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable("idCliente") Integer idCliente){
        Cliente cliente = service.getCliente(idCliente);
        if(cliente==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }


    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Cliente clienteCreado = service.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    @PutMapping(value = "/{idCliente}")
    public  ResponseEntity<Cliente> actualizarCliente(@PathVariable("idCliente") Integer idCliente, @RequestBody Cliente cliente){

        cliente.setId(idCliente);
        Cliente db = service.setCliente(cliente);
        System.out.println("db:"+db);
        if(db==null){

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(db);
    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable("idCliente") Integer idCliente){

        Cliente db = service.getCliente(idCliente);
        if(db==null){
            return ResponseEntity.notFound().build();
        }
        db=service.deleteCliente(db);
        return ResponseEntity.ok(db);
    }


    String formatMessage(BindingResult result){
        String collect = result.getFieldErrors().stream().map(err -> {
            return err.getField() + "->" + err.getDefaultMessage();
        }).collect(Collectors.joining(","));
        return collect;
    }
}
