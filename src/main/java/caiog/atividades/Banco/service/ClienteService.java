package caiog.atividades.Banco.service;

import caiog.atividades.Banco.dto.ClienteDTO;
import caiog.atividades.Banco.entity.Cliente;
import caiog.atividades.Banco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    public List<ClienteDTO> listarClientes(){
        return  clienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<ClienteDTO> buscarPorID(Long id){
        return clienteRepository.findById(id)
                .map(this::toDTO);
    }

    public ClienteDTO criarCliente(Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return toDTO(clienteSalvo);
    }

    public ClienteDTO atualizarCliente(Long id, Cliente clienteAtualizado){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setCpf(clienteAtualizado.getCpf());
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    cliente.setEndereco(clienteAtualizado.getEndereco());
                    cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    cliente.setSenha(clienteAtualizado.getSenha());

                    Cliente clienteSalvo = clienteRepository.save(cliente);
                    return toDTO(clienteSalvo);
                }).orElseThrow(() -> new RuntimeException("Cliente não existe"));
    }

    public void excluitCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getDataNascimento(),
                cliente.getEndereco());
    }
}
