package com.order.project_orderup.control;


import com.order.project_orderup.dto.*;

import com.order.project_orderup.service.ClienteService;
import com.order.project_orderup.service.OrdemService;
import com.order.project_orderup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class OrdemControl {


    private OrdemService ordemService;
    private UsuarioService usuarioService;
    private ClienteService clienteService;
    private RedirectAttributes redirectAttributes;

    @Autowired
    public OrdemControl(OrdemService ordemService, UsuarioService usuarioService , ClienteService clienteService) {
        this.ordemService = ordemService;
        this.usuarioService = usuarioService;
        this.clienteService= clienteService;

    }

    @GetMapping("/{id}/ordemservice")
    public String order(@PathVariable("id") String id,Model model,@ModelAttribute("ordem") OrdemDTO ordemDTO) {
        model.addAttribute("ordem", ordemDTO);
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        List<ClienteUpdateDTO> clientesDTO = clienteService.lista(usuarioDTO);

        model.addAttribute("clientes", clientesDTO);
        return "ordemservice";
    }

    @PostMapping("/{id}/ordemservice")
    public ModelAndView createOrdem(@Valid @ModelAttribute("ordem") OrdemDTO ordemDTO, BindingResult bindingResult, @PathVariable("id") String id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            ClienteUpdateDTO clienteEncontrado = clienteService.findByNomeAndUsuarioCpf(ordemDTO.getCliente().getNome(), usuarioDTO.getCpf());

            if (bindingResult.hasErrors()) {
                modelAndView.addObject("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");
                return modelAndView;
            }

            if (clienteEncontrado == null) {
                modelAndView.addObject("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");
                return modelAndView;
            }

            ordemDTO.setUsuario(usuarioDTO);
            ordemDTO.setCliente(clienteEncontrado);

            ordemService.save(ordemDTO);


            modelAndView.addObject("ordem", ordemDTO);
            modelAndView.addObject("mensagemSalvo", "Salvo com sucesso!");
            System.out.println("Mensagem de sucesso: " + modelAndView.getModel().get("mensagemSalvo"));

            modelAndView.setViewName("ordemservice");
            Thread.sleep(2000);


            return modelAndView;

        } catch (Exception e) {
            modelAndView.addObject("mensagemErro", "Erro de validação. Verifique os campos e tente novamente.");
            return modelAndView;
        }
    }


    @GetMapping("/{id}/ordemlist")
    public String getAllOrdens(@PathVariable("id") String id, Model model) {

        UsuarioDTO usuarioDTO = usuarioService.buscar(id);


        List<OrdemUpdateDTO> ordensDTO = ordemService.lista(usuarioDTO);
        if (ordensDTO.isEmpty()) {

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



   @GetMapping("/{id}/{ordemid}/ordemupdate")
    public String viewordem(@PathVariable("id") String id, @PathVariable("ordemid") Long ordemId, Model model) {
       OrdemUpdateDTO ordemUpdateDTO = ordemService.obterOrdem(id, ordemId);
       model.addAttribute("ordem", ordemUpdateDTO);
       return "ordemupdate";
    }


    @PostMapping ("/{id}/{ordemid}/deleteorder")
    public String deleteOrdemById(@PathVariable("id") String id,@PathVariable("ordemid") Long ordemId) {
        UsuarioDTO usuarioDTO = usuarioService.buscar(id);
        ordemService.delete(id,ordemId);
        return "redirect:/" + id + "/ordemlist";
    }



}