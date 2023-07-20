package com.order.project_orderup.service;

import java.util.List;

import java.util.stream.Collectors;


import com.order.project_orderup.dto.*;
import com.order.project_orderup.model.Ordem;
import com.order.project_orderup.repository.OrdemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdemService {

    private OrdemRepository ordemRepository;
    private ModelMapper modelMapper;

    private ClienteService clienteService;

    @Autowired
    public OrdemService(OrdemRepository ordemRepository, ModelMapper modelMapper, ClienteService clienteService) {
        this.ordemRepository = ordemRepository;
        this.modelMapper = modelMapper;
        this.clienteService = clienteService;
    }

    @Transactional
    public OrdemDTO save(OrdemDTO ordemDTO) {
        Ordem ordem = modelMapper.map(ordemDTO, Ordem.class);


        Integer ultimoNumeroOrdem = ordemRepository.findMaxNumeroOrdemByUsuario(ordemDTO.getUsuario().getCpf());

        Integer proximoNumeroOrdem = ultimoNumeroOrdem != null ? ultimoNumeroOrdem + 1 : 1;
        ordem.setNumeroOrdem(proximoNumeroOrdem);
        Ordem savedOrdem = ordemRepository.save(ordem);
        return modelMapper.map(savedOrdem, OrdemDTO.class);

    }

    public List<OrdemUpdateDTO> lista(UsuarioDTO usuarioId) {
        List<Ordem> ordens = ordemRepository.findByUsuarioCpf(usuarioId.getCpf());
        return ordens.stream()
                .map(ordem -> {
                    OrdemUpdateDTO ordemUpdateDTO = modelMapper.map(ordem, OrdemUpdateDTO.class);
                    ordemUpdateDTO.setCliente(modelMapper.map(ordem.getCliente(), ClienteUpdateDTO.class));
                    return ordemUpdateDTO;
                })
                .collect(Collectors.toList());
    }





    //////////
    public void atualizarOrdem(String usuarioId, Long ordemId, OrdemUpdateDTO ordemUpdateDTO) {

        Ordem ordem = ordemRepository.findByIdAndUsuarioCpf(ordemId, usuarioId);
        if (ordemUpdateDTO != null){

            ordem.setResponsavel(ordemUpdateDTO.getResponsavel());
            ordem.setData(ordemUpdateDTO.getData());
            ordem.setEquipamento(ordemUpdateDTO.getEquipamento());
            ordem.setMarca(ordemUpdateDTO.getMarca());
            ordem.setNumserie(ordemUpdateDTO.getNumserie());
            ordem.setDiagnostico(ordemUpdateDTO.getDiagnostico());
            ordem.setPecas(ordemUpdateDTO.getPecas());
            ordem.setLaudo(ordemUpdateDTO.getLaudo());
            ordem.setDocumento(ordemUpdateDTO.getDocumento());
            ordem.setAssinatura(ordemUpdateDTO.getAssinatura());
            ordemRepository.save(ordem);

        }


    }

    public OrdemUpdateDTO obterOrdem(String usuarioId, Long ordemId) {
        Ordem ordem = ordemRepository.findByIdAndUsuarioCpf(ordemId, usuarioId);
        return modelMapper.map(ordem, OrdemUpdateDTO.class);

    }





    @Transactional
    public void delete(String usuarioId,Long ordemId) {
        Ordem ordem = ordemRepository.findByIdAndUsuarioCpf(ordemId, usuarioId);
        ordemRepository.delete(ordem);
    }
}
