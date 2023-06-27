package com.order.project_orderup.service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.order.project_orderup.dto.ClienteDTO;
import com.order.project_orderup.dto.ClienteUpdateDTO;
import com.order.project_orderup.dto.OrdemUpdateDTO;
import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.model.Cliente;
import com.order.project_orderup.model.Ordem;
import com.order.project_orderup.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;



@Service
public class ClienteService {


    private ClienteRepository clienteRepository;

    private ModelMapper modelMapper;
    @Autowired
   public ClienteService(ClienteRepository clienteRepository , ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        ClienteDTO clienteCNPJ= findByCnpj(clienteDTO.getCnpj());
        ClienteDTO clienteCPF = findByCpf(clienteDTO.getCpf());
        System.out.println();
        if (clienteCPF != null) {
            throw new IllegalArgumentException("Usuário já existe com este email");
        }

        if (clienteCNPJ != null) {
            throw new IllegalArgumentException("Usuário já existe com este CPF");
        }

        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente savedCliente = clienteRepository.save(cliente);
        return modelMapper.map(savedCliente, ClienteDTO.class);
    }

    public ClienteDTO findByCnpj(String cnpj) {
        Cliente cliente = clienteRepository.findByCnpj(cnpj);
        if (cliente == null) {
            return null;
        }
        return modelMapper.map(cliente, ClienteDTO.class);
    }


    public ClienteDTO findByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            return null;
        }
        return modelMapper.map(cliente, ClienteDTO.class);
    }


    public List<ClienteUpdateDTO> lista(UsuarioDTO usuarioDTO) {
        List<Cliente> clientes = clienteRepository.findByUsuarioCpf(usuarioDTO.getCpf());
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteUpdateDTO.class))
                .collect(Collectors.toList());
    }

   /* public ClienteUpdateDTO buscar(long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteUpdateDTO.class);
    }*/

    public void atualizarCliente(Long clienteId,String id,  ClienteUpdateDTO clienteUpdateDTO) {

        Cliente cliente = clienteRepository.findByIdAndUsuarioCpf(clienteId,id);

        if (clienteUpdateDTO != null){

        cliente.setEmail(clienteUpdateDTO.getEmail());
        cliente.setEndereco(clienteUpdateDTO.getEndereco());
        cliente.setCidade(clienteUpdateDTO.getCidade());
        cliente.setEstado(clienteUpdateDTO.getEstado());

        clienteRepository.save(cliente);}
    }

    @Transactional
    public void delete( Long clienteId ,String id) {
        Cliente cliente = clienteRepository.findByIdAndUsuarioCpf(clienteId,id);
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pertence ao usuário.");
        }
        clienteRepository.delete(cliente);
    }


    public ClienteUpdateDTO findByNome(String nome) {
        Cliente cliente = clienteRepository.findByNome(nome);
        System.out.println("4" +nome);
        if (cliente == null) {
            return null;
        }
        return modelMapper.map(cliente, ClienteUpdateDTO.class);
    }

    public ClienteUpdateDTO obtercliente( Long clienteId, String id) {
        Cliente cliente = clienteRepository.findByIdAndUsuarioCpf(clienteId,id);
        if (cliente == null) {
            throw new IllegalArgumentException("A ordem não pertence ao usuário.");
        }
        return modelMapper.map(cliente, ClienteUpdateDTO.class);
    }
}