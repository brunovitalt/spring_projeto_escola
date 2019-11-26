package com.VA2.projetoescola.controller;

import com.VA2.projetoescola.model.Aluno;
import com.VA2.projetoescola.model.Turma;
import com.VA2.projetoescola.repository.AlunoRepository;
import com.VA2.projetoescola.repository.TurmaRepository;
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
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;


    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/adicionar")
    public ModelAndView adicionar () {
        ModelAndView mv = new ModelAndView("AdicionaTurma");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        mv.addObject("alunos", alunoRepository.findAll());
        mv.addObject("turma", new Turma());
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar (@Valid Turma turma, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            ModelAndView mv2 = new ModelAndView("AdicionaTurma");
            mv2.addObject("turma",new Turma());
            return mv2;
        }
        ModelAndView mv = new ModelAndView("redirect:/turmas/listar");
        if(turma.getId() != null){
            attributes.addFlashAttribute("mensagem", "Turma editado com sucesso.");
        } else {
            attributes.addFlashAttribute("mensagem", "Turma adicionado com sucesso.");
        }
        this.turmaRepository.save(turma);
        return mv;
    }

    @GetMapping("/listar")
    public ModelAndView listar () {
        ModelAndView mv = new ModelAndView("ListarTurmas");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        mv.addObject("turmas", turmaRepository.findAll());
        return mv;
    }
    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id,
                                RedirectAttributes attributes){
        turmaRepository.deleteById(id);
        ModelAndView mv = new ModelAndView("redirect:/turmas/listar");
        attributes.addFlashAttribute("mensagem", "Turma removida com sucesso.");
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("AdicionaTurma");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        mv.addObject("turma", turmaRepository.findById(id));
        mv.addObject("alunos", alunoRepository.findAll());
        mv.addObject("id", id);
        return mv;
    }
}
