package com.VA2.projetoescola.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping(value = {"/","/loginEscola"})
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("LoginEscola");
        return mv;
    }

    @GetMapping("/errologin")
    public ModelAndView errologin(HttpServletRequest request, Model model) {
        ModelAndView mv = login();

        HttpSession session = request.getSession(false);

        String mensagemErro = null;

        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                mensagemErro = ex.getMessage();
            }
        }
        mv.addObject("mensagemErro", mensagemErro);

        return mv;
    }


}
