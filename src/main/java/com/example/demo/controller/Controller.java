package com.example.demo.controller;

import com.example.demo.entity.Pedido;
import com.example.demo.entity.Endereco;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pedido/v1/")
public class Controller {

    @Autowired
    Repository repository;

    @PostMapping
    public Pedido creat(@RequestBody @Valid Pedido pedido){
        Pedido pedidoSaved = repository.save(pedido);
        return pedidoSaved;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Pedido> getPedidoById(@PathVariable Long id){
        Optional<Pedido> pedidoReturned = repository.findById(id);
        return pedidoReturned;
    }
    @DeleteMapping("/{id}")
    public String deletePedidoById(@PathVariable Long id){
        try{
            Optional<Pedido> pedido = Optional.of(repository.getById(id));
            if(pedido.isPresent()){
                repository.deleteById(id);
                return "Pedido de " + id + " deletado com sucesso";
            }else{
                throw new Exception("Pedido inexistente");
            }
        }catch(Exception e){
            e.printStackTrace();
            return "O pedido de " + id + " não existe para ser deletado!" +
                    " Por favor, entre em contato com o atendimento ...";
        }
    }

    @GetMapping
    public List<Pedido> listPedido(){
        return repository.findAll();
    }

    @PutMapping("/atualize/{id}")
    public String updatePedidoById(@PathVariable Long id){
        Optional<Pedido> velhoPedido = repository.findById(id);
        if(velhoPedido.isPresent()){
            Pedido pedido = velhoPedido.get();
            repository.save(pedido);
            return "Pedido de ID" + pedido.getId() + " atualizado com sucesso!";
        }else{
            return "Pedido de ID " + id + " não existe!";
        }
    }
}