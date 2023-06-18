package com.order.project_orderup.control;


import com.order.project_orderup.dto.ClienteDTO;
import com.order.project_orderup.dto.OrdemDTO;
import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.service.ClienteService;
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

    private ClienteService clienteService;
    @Autowired
    public OrdemControl(OrdemService ordemService, UsuarioService usuarioService , ClienteService clienteService) {
        this.ordemService = ordemService;
        this.usuarioService = usuarioService;
        this.clienteService= clienteService;

    }

    @GetMapping("/{id}/orderservice")
    public String order(@PathVariable("id") Long id) {
        return "orderservice";
    }

    @PostMapping("/{id}/orderservice")
    public String createOrdem(@Valid @PathVariable("id") Long id, Model model, OrdemDTO ordemDTO, ClienteDTO clienteDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ClienteDTO clienteEncontrado = clienteService.findByName(clienteDTO.getNome());
        if (clienteEncontrado == null) {
            model.addAttribute("mensagem", "Usuário não encontrado");
            return "orderservice";
        }

        ordemDTO.setUsuario(usuarioDTO);
        ordemDTO.setCliente(clienteEncontrado);
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





    @PutMapping("/{id}/{ordem_id}/updateorder")
    public String updateOrdem(@PathVariable("id") Long id, @PathVariable("ordem_id") Long ordemId, OrdemDTO ordemDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemDTO.setUsuario(usuarioDTO);
        ordemService.atualizarOrdem(id, ordemId, ordemDTO);
        return "ordemlist";
    }


    @DeleteMapping("/{id}/{ordem_id}/deleteorder")
    public String deleteOrdemById(@PathVariable("id") long id,@PathVariable("ordem_id") Long ordemId) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemService.delete(id,ordemId);
        return "ordemlist";
    }



}