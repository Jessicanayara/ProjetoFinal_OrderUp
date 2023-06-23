package com.order.project_orderup.control;

import com.order.project_orderup.dto.UsuarioDTO;
import com.order.project_orderup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller

public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

   /* @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }*/

    @GetMapping("/home")
    public String showCadForm( Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "home";
    }

    @PostMapping("/login")
    public String login( @ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        UsuarioDTO usuarioEncontrado = usuarioService.findByEmail(usuarioDTO.getEmail());
        if (usuarioEncontrado == null) {
            model.addAttribute("mensagem", "Usuário não encontrado");
            return "home";
        }
        Long id = usuarioEncontrado.getId();
        return "redirect:/" + id + "/perfil";
    }






    @PostMapping("/cadastrar")
    public String cadastrarUsuario(  @Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");

            return "home";
        }

        UsuarioDTO usuarioEmail = usuarioService.findByEmail(usuarioDTO.getEmail());
        if (usuarioEmail != null) {
            model.addAttribute("mensagemErro", "Usuário já existe com este email");
            return "home";
        }

        UsuarioDTO usuarioCPF = usuarioService.findByCpf(usuarioDTO.getCpf());
        if (usuarioCPF != null) {
            model.addAttribute("mensagemErro", "Usuário já existe com este CPF");
            return "home";
        }

        try {
            UsuarioDTO novoUsuario = usuarioService.save(usuarioDTO);
            model.addAttribute("mensagemSucesso", "Usuário cadastrado com sucesso!");
            return "home";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemErro", "Usuário não cadastrado. " + e.getMessage());
            return "home";
        }


    }



    @GetMapping("/{id}/perfil")
    public String getUsuarioById(@PathVariable("id") Long id, Model model) {
        UsuarioDTO usuario = usuarioService.buscar(id);
        model.addAttribute("usuario", usuario);
        return "perfil";
    }



}
