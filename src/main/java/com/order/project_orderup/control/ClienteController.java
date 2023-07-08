package com.order.project_orderup.control;

import com.order.project_orderup.dto.ClienteDTO;
import com.order.project_orderup.dto.ClienteUpdateDTO;
import com.order.project_orderup.dto.OrdemUpdateDTO;
import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.service.ClienteService;
import com.order.project_orderup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ClienteController {

    private  ClienteService clienteService;
    private  UsuarioService usuarioService;

    @Autowired
    public ClienteController(ClienteService clienteService, UsuarioService usuarioService) {
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}/clienteservice")
    public String cliente(@PathVariable("id") String id,Model model, @ModelAttribute("cliente") ClienteDTO clienteDTO) {
        model.addAttribute("cliente", clienteDTO);
        return "clienteservice";
    }

    @PostMapping("/{id}/clienteservice")
    public String createCliente( @PathVariable("id") String id, @Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult bindingResult, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);



        if (bindingResult.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");
            model.addAttribute("id", id);
            return "clienteservice";
        }

        ClienteDTO clienteCpf= clienteService.findByCpf(clienteDTO.getCpf());
        if (clienteCpf != null) {
            model.addAttribute("mensagemErro", "Este cliente já existe");

            return "clienteservice";
        }

        ClienteDTO clienteCNPJ= clienteService.findByCnpj(clienteDTO.getCnpj());
        if (clienteCNPJ != null) {
            model.addAttribute("mensagemErro", "Este cliente já existe");

            return "clienteservice";
        }

        try {
            clienteDTO.setUsuario(usuarioDTO);
            clienteService.save(clienteDTO);
            model.addAttribute("cliente", clienteDTO);
            model.addAttribute("mensagemSucesso", "Cliente cadastrado com sucesso!");
            return "redirect:/" + id + "/clientelist";

        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemErro", "Cliente não cadastrado. " + e.getMessage());

            return "clienteservice";
        }


    }

    @GetMapping("/{id}/clientelist")
    public String getAllClientes(@PathVariable("id") String id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        List<ClienteUpdateDTO> clientesDTO = clienteService.lista(usuarioDTO);
        if (clientesDTO.isEmpty()) {

            model.addAttribute("mensagemErro", "Nenhuma cliente encontrado");
            return "clientelist";
        }
        model.addAttribute("cliente", clientesDTO);
        return "clientelist";
    }


    @PostMapping("/{id}/{clienteid}/clienteupdate")
    public String updateCliente(@PathVariable("id") String id, @PathVariable("clienteid") Long clienteId, @ModelAttribute("cliente") ClienteUpdateDTO clienteUpdateDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        clienteUpdateDTO.setUsuario(usuarioDTO);
        clienteService.atualizarCliente( clienteId, id,  clienteUpdateDTO);
        return "clienteupdate";
    }

    @GetMapping("/{id}/{clienteid}/clienteupdate")
    public String viewcliente(@PathVariable("id") String id, @PathVariable("clienteid") Long clienteId, Model model) {
        ClienteUpdateDTO clienteUpdateDTO = clienteService.obtercliente(clienteId,id);
        model.addAttribute("cliente", clienteUpdateDTO);
        return "clienteupdate";
    }


    @PostMapping("/{id}/{cliente_id}/delete")
    public String deleteClienteById(@PathVariable("id") String id, @PathVariable("cliente_id") long clienteId) {
        clienteService.delete(clienteId, id);
        return "redirect:/" + id + "/clientelist";
    }

}