package com.order.project_orderup.control;

import com.order.project_orderup.dto.ClienteDTO;
import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.service.ClienteService;
import com.order.project_orderup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
    public String createCliente(@PathVariable("id") Long id, Model model, ClienteDTO clienteDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        clienteDTO.setUsuario(usuarioDTO);
        clienteService.save(clienteDTO);
        model.addAttribute("clienteDTO", clienteDTO);
        return "clienteservice";
    }

    @GetMapping("/{id}/clientelist")
    public String getAllClientes(@PathVariable("id") Long id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        List<ClienteDTO> clientesDTO = clienteService.lista(usuarioDTO);
        model.addAttribute("clientes", clientesDTO);
        return "clientelist";
    }

    @GetMapping("/{id}/{cliente_id}/buscar")
    public String getClienteById(@PathVariable("id") Long id, @PathVariable("cliente_id") long clienteId, Model model) {
        ClienteDTO clienteDTO = clienteService.buscar(clienteId);
        model.addAttribute("cliente", clienteDTO);
        return "";
    }

    @PutMapping("/{id}/{cliente_id}/update")
    public String updateCliente(@PathVariable("id") Long id, @PathVariable("cliente_id") Long clienteId, ClienteDTO clienteDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        clienteDTO.setUsuario(usuarioDTO);
        clienteService.atualizarCliente(id, clienteId, clienteDTO);
        return "";
    }

    @DeleteMapping("/{id}/{cliente_id}/delete")
    public String deleteClienteById(@PathVariable("id") Long id, @PathVariable("cliente_id") long clienteId) {
        clienteService.delete(clienteId);
        return "";
    }

}