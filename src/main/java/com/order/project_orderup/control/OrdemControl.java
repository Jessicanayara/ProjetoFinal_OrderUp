package com.order.project_orderup.control;


import com.order.project_orderup.dto.*;
import com.order.project_orderup.model.Ordem;
import com.order.project_orderup.repository.ClienteRepository;
import com.order.project_orderup.repository.OrdemRepository;
import com.order.project_orderup.service.ClienteService;
import com.order.project_orderup.service.OrdemService;
import com.order.project_orderup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.validation.BindingResult;
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

    @GetMapping("/{id}/ordemservice")
    public String order(@PathVariable("id") String id,Model model,@ModelAttribute("ordem") OrdemDTO ordemDTO) {
        model.addAttribute("ordem", ordemDTO);

        return "ordemservice";
    }

    @PostMapping("/{id}/ordemservice")
    public String createOrdem(@Valid @ModelAttribute("ordem") OrdemDTO ordemDTO, BindingResult bindingResult, @PathVariable("id") String id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);

        ClienteUpdateDTO clienteEncontrado = clienteService.findByNome(ordemDTO.getCliente().getNome());
        if (bindingResult.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");

            return "ordemservice";
        }

        if (clienteEncontrado == null) {
            model.addAttribute("mensagem", "Cliente não encontrado");
            return "ordemservice";
        }

        ordemDTO.setUsuario(usuarioDTO);
        ordemDTO.setCliente(clienteEncontrado);

        ordemService.save(ordemDTO);

        model.addAttribute("ordem", ordemDTO);
        return "ordemservice";
    }

    ///////////////
    @GetMapping("/{id}/ordemlist")
    public String getAllOrdens(@PathVariable("id") String id, Model model) {

        UsuarioDTO usuarioDTO = usuarioService.buscar(id);


        List<OrdemUpdateDTO> ordensDTO = ordemService.lista(usuarioDTO);
        if (ordensDTO.isEmpty()) {
            System.out.println("entrou no empty");
            model.addAttribute("mensagemErro", "Nenhuma ordem encontrada");
            return "ordemlist";
        }

        model.addAttribute("ordem", ordensDTO);
        return "ordemlist";
    }





    @PostMapping("/{id}/{ordemid}/ordemupdate")
    public String updateOrdem(@PathVariable("id") String id, @PathVariable("ordemid") Long ordemId, @ModelAttribute("ordem") OrdemUpdateDTO ordemUpdateDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemUpdateDTO.setUsuario(usuarioDTO);
        ordemService.atualizarOrdem(id, ordemId, ordemUpdateDTO);
        return "redirect:/" + id + "/ordemlist";
    }

    @GetMapping("/{id}/buscar-cliente")
    public ResponseEntity<List<ClienteUpdateDTO>> buscarCliente(@PathVariable("id") String id) {
        List<ClienteUpdateDTO> clientesEncontrados = clienteService.buscarPorUsuario(id);
        return ResponseEntity.ok(clientesEncontrados);
    }

   @GetMapping("/{id}/{ordemid}/ordemupdate")
    public String viewordem(@PathVariable("id") String id, @PathVariable("ordemid") Long ordemId, Model model) {
       OrdemUpdateDTO ordemUpdateDTO = ordemService.obterOrdem(id, ordemId);
       model.addAttribute("ordem", ordemUpdateDTO);
       return "ordemupdate";
    }


    @GetMapping("/{id}/{ordemid}/deleteorder")
    public String deleteOrdemById(@PathVariable("id") String id,@PathVariable("ordemid") Long ordemId) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemService.delete(id,ordemId);
        return "redirect:/" + usuarioDTO + "/ordemlist";
    }



}