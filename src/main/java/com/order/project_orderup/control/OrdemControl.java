package com.order.project_orderup.control;


import com.order.project_orderup.dto.OrdemDTO;
import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.service.OrdemService;
import com.order.project_orderup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class OrdemControl {


    private OrdemService ordemService;
    private UsuarioService usuarioService;

    @Autowired
    public OrdemControl(OrdemService ordemService, UsuarioService usuarioService) {
        this.ordemService = ordemService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}/orderservice")
    public String order(@PathVariable("id") Long id) {
        return "orderservice";
    }

    @PostMapping("/{id}/orderservice")
    public String createOrdem(@Valid @PathVariable("id") Long id, Model model, OrdemDTO ordemDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemDTO.setUsuario(usuarioDTO);
        ordemService.save(ordemDTO);
        model.addAttribute("ordemDTO", ordemDTO);
        return "orderservice";
    }

    ///////////////
    @GetMapping("/{id}/ordemlist")
    public String getAllOrdens(@PathVariable("id") Long id, Model model) {

        UsuarioDTO usuarioDTO = usuarioService.buscar(id);


        List<OrdemDTO> ordensDTO = ordemService.lista(usuarioDTO);

        model.addAttribute("ordens", ordensDTO);
        return "ordemlist";
    }


    ////corrigir
    @GetMapping("/{id}/{ordem_id}/buscar")
    public String getOrdemById(@PathVariable("id") Long id,@PathVariable("id") long ordem_id, Model model) {
        OrdemDTO ordemDTO = ordemService.buscar(ordem_id);

        model.addAttribute("ordem", ordemDTO);
        return "";
    }

    @PutMapping("/{id}/{ordem_id}/update")
    public String updateOrdem(@PathVariable("id") Long id, @PathVariable("ordem_id") Long ordemId, OrdemDTO ordemDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemDTO.setUsuario(usuarioDTO);
        ordemService.atualizarOrdem(id, ordemId, ordemDTO);
        return "";
    }


    @DeleteMapping("/{id}/{ordem_id}/update")
    public String deleteOrdemById(@PathVariable("id") long id) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemService.delete(id);
        return "";
    }



}