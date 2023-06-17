package com.order.project_orderup.control;

import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//@RestController("/OrderUp")
@Controller

public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String showCadForm( Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "cadastro";
    }

    @PostMapping("/login")
    public String login( @ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        UsuarioDTO usuarioEncontrado = usuarioService.findByEmail(usuarioDTO.getEmail());
        if (usuarioEncontrado == null) {
            model.addAttribute("mensagem", "Usuário não encontrado");
            return "login";
        }
        Long id = usuarioEncontrado.getId();
        return "redirect:/" + id + "/orderservice";
    }






    @PostMapping("/cadastro")
    public String cadastrarUsuario(  @Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");
            return "cadastro";
        }

        UsuarioDTO usuarioEmail = usuarioService.findByEmail(usuarioDTO.getEmail());
        if (usuarioEmail != null) {
            model.addAttribute("mensagemErro", "Usuário já existe com este email");
            return "cadastro";
        }

        UsuarioDTO usuarioCPF = usuarioService.findByCpf(usuarioDTO.getCpf());
        if (usuarioCPF != null) {
            model.addAttribute("mensagemErro", "Usuário já existe com este CPF");
            return "cadastro";
        }

        try {
            UsuarioDTO novoUsuario = usuarioService.save(usuarioDTO);
            model.addAttribute("mensagemSucesso", "Usuário cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemErro", "Usuário não cadastrado. " + e.getMessage());
            return "cadastro";
        }

        return "cadastro";
    }



    @GetMapping("/perfil/{id}")
    public String getUsuarioById(@PathVariable("id") Long id, Model model) {
        UsuarioDTO usuario = usuarioService.buscar(id);
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    @DeleteMapping("/excluir/{id}")
    public String deleteUsuarioById(@PathVariable("id")Long id) {
        usuarioService.delete(id);
        return "excluido";
    }

}
