package com.order.project_orderup.control;

import com.order.project_orderup.dto.ClienteDTO;
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

    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    @Autowired
    public ClienteController(ClienteService clienteService, UsuarioService usuarioService) {
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}/clienteservice")
    public String cliente(@PathVariable("id") Long id) {
        return "clienteservice";
    }

    @PostMapping("/{id}/clienteservice")
    public String createCliente(@Valid @PathVariable("id") Long id, BindingResult bindingResult, Model model, ClienteDTO clienteDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);



        if (bindingResult.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");
            model.addAttribute("id", id);
            return "/{id}/clienteservice";
        }

        ClienteDTO clienteCpf= clienteService.findByCpf(clienteDTO.getCpf());
        if (clienteCpf != null) {
            model.addAttribute("mensagemErro", "Este cliente já existe");
            model.addAttribute("id", id);
            return "/{id}/clienteservice";
        }

        ClienteDTO clienteCNPJ= clienteService.findByCnpj(clienteDTO.getCnpj());
        if (clienteCNPJ != null) {
            model.addAttribute("mensagemErro", "Este cliente já existe");
            model.addAttribute("id", id);
            return "/{id}/clienteservice";
        }

        try {
            clienteDTO.setUsuario(usuarioDTO);
            clienteService.save(clienteDTO);
            model.addAttribute("clienteDTO", clienteDTO);
            model.addAttribute("mensagemSucesso", "Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemErro", "Cliente não cadastrado. " + e.getMessage());
            model.addAttribute("id", id);
            return "/{id}/clienteservice";
        }

        return "/{id}/clienteservice";
    }

    @GetMapping("/{id}/clientelist")
    public String getAllClientes(@PathVariable("id") Long id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        List<ClienteDTO> clientesDTO = clienteService.lista(usuarioDTO);
        model.addAttribute("clientes", clientesDTO);
        return "clientelist";
    }

////testar
    @PutMapping("/{id}/{cliente_id}/update")
    public String updateCliente(@PathVariable("id") Long id, @PathVariable("cliente_id") Long clienteId, ClienteDTO clienteDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        clienteDTO.setUsuario(usuarioDTO);
        clienteService.atualizarCliente(id, clienteId, clienteDTO);
        return "";
    }

    @DeleteMapping("/{id}/{cliente_id}/delete")
    public String deleteClienteById(@PathVariable("id") Long id, @PathVariable("cliente_id") long clienteId) {
        clienteService.delete(id,clienteId);
        return "";
    }

}