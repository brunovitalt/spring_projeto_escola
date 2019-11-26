package com.VA2.projetoescola.controller;

import com.VA2.projetoescola.model.Aluno;
import com.VA2.projetoescola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository  alunoRepository;

    @GetMapping("/adicionar")
    public ModelAndView adicionar() {
        ModelAndView mv = new ModelAndView("AdicionaAluno");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        mv.addObject("aluno",new Aluno());
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar (@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            ModelAndView mv2 = new ModelAndView("AdicionaAluno");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            mv2.addObject("nomeUsuarioLogado",authentication.getName());
            mv2.addObject("aluno",new Aluno());
            return mv2;
        }
        ModelAndView mv = new ModelAndView("redirect:/alunos/listar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        if(aluno.getId() != null){
            attributes.addFlashAttribute("mensagem", "Aluno editado com sucesso.");
        } else {
            attributes.addFlashAttribute("mensagem", "Aluno adicionado com sucesso.");
        }
        this.alunoRepository.save(aluno);
        return mv;
    }

    @GetMapping("/listar")
    public ModelAndView listar () {
        ModelAndView mv = new ModelAndView("ListarAlunos");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        mv.addObject("alunos", alunoRepository.findAll());
        mv.addObject("qntAlunos", alunoRepository.findAll().size());
        return mv;
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes attributes){
        alunoRepository.deleteById(id);
        ModelAndView mv = new ModelAndView("redirect:/alunos/listar");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        attributes.addFlashAttribute("mensagem", "Aluno removido com sucesso.");
        return mv;
    }
}
