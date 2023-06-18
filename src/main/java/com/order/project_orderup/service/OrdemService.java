package com.order.project_orderup.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


import com.order.project_orderup.dto.OrdemDTO;
import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.model.Ordem;
import com.order.project_orderup.repository.OrdemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdemService {
    private OrdemRepository ordemRepository;
    private ModelMapper modelMapper;

    public OrdemService(OrdemRepository ordemRepository, ModelMapper modelMapper) {
        this.ordemRepository = ordemRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public OrdemDTO save(OrdemDTO ordemDTO) {
        Ordem ordem = modelMapper.map(ordemDTO, Ordem.class);
        Ordem savedOrdem = ordemRepository.save(ordem);
        return modelMapper.map(savedOrdem, OrdemDTO.class);
    }

    public List<OrdemDTO> lista(UsuarioDTO usuarioId) {
        List<Ordem> ordens = ordemRepository.findByUsuarioId(usuarioId.getId());
        return ordens.stream()
                .map(ordem -> modelMapper.map(ordem, OrdemDTO.class))
                .collect(Collectors.toList());
    }



    //////////
    public void atualizarOrdem(Long usuarioId, Long ordemId, OrdemDTO ordemDTO) {
        // Verificar se a ordem pertence ao usuário
        Ordem ordem = ordemRepository.findByIdAndUsuarioId(ordemId, usuarioId);
        if (ordem == null) {
            throw new IllegalArgumentException("A ordem não pertence ao usuário.");
        }

        ordem.setResponsavel(ordemDTO.getResponsavel());
        ordem.setData(ordemDTO.getData());

        ordemRepository.save(ordem);
    }




    @Transactional
    public void delete(long id,Long ordemId) {
        Ordem ordem = ordemRepository.findByIdAndUsuarioId(ordemId, id);
        if (ordem == null) {
            throw new IllegalArgumentException("A ordem não pertence ao usuário.");
        }
        ordemRepository.delete(ordem);
    }
}
