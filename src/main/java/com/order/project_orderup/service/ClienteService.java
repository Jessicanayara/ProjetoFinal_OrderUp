package com.order.project_orderup.service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.order.project_orderup.dto.ClienteDTO;
import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.model.Cliente;
import com.order.project_orderup.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;



@Service
public class ClienteService {


    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;
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


    public List<ClienteDTO> lista(UsuarioDTO usuarioDTO) {
        List<Cliente> clientes = clienteRepository.findByUsuarioId(usuarioDTO.getId());
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteDTO buscar(long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public void atualizarCliente(Long usuarioId, Long clienteId, ClienteDTO clienteDTO) {
        // Verificar se o cliente pertence ao usuário
        Cliente cliente = clienteRepository.findByIdAndUsuarioId(clienteId, usuarioId);
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pertence ao usuário.");
        }


        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setCidade(clienteDTO.getCidade());
        cliente.setEstado(clienteDTO.getEstado());

        clienteRepository.save(cliente);
    }

    @Transactional
    public void delete(long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }



}