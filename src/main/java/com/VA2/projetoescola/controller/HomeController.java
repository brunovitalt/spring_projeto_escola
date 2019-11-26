package com.VA2.projetoescola.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/home")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("home");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("nomeUsuarioLogado",authentication.getName());
        return mv;
    }
}
