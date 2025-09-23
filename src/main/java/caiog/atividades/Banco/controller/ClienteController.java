package caiog.atividades.Banco.controller;

import caiog.atividades.Banco.dto.ClienteDTO;
import caiog.atividades.Banco.entity.Cliente;
import caiog.atividades.Banco.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    private List<ClienteDTO> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClienteDTO> encontrarClientePorID(@PathVariable Long id){
        return  clienteService.buscarPorID(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    private ClienteDTO atualizarCliente(@PathVariable Long id,@RequestBody Cliente clienteAtualizado){
        return clienteService.atualizarCliente(id,clienteAtualizado);
    }

    @PostMapping
    private ClienteDTO criarCliente(@RequestBody Cliente clienteNovo){
        return  clienteService.criarCliente(clienteNovo);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        clienteService.excluitCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
