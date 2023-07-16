package com.order.project_orderup.service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.model.Usuario;
import com.order.project_orderup.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioEmail = findByEmail(usuarioDTO.getEmail());
        UsuarioDTO usuarioCPF = findByCpf(usuarioDTO.getCpf());
        System.out.println(usuarioCPF);
        if (usuarioEmail != null) {
            throw new IllegalArgumentException("Usuário já existe com este email");
        }

        if (usuarioCPF != null) {
            throw new IllegalArgumentException("Usuário já existe com este CPF");
        }

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(savedUsuario, UsuarioDTO.class);
    }

    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return null;
        }
        return modelMapper.map(usuario, UsuarioDTO.class);
    }


    public UsuarioDTO findByCpf(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if (usuario == null) {
            return null;
        }
        return modelMapper.map(usuario, UsuarioDTO.class);
    }




    public UsuarioDTO buscar(String cpf) {
        Usuario usuario = usuarioRepository.findById(cpf)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }



}
