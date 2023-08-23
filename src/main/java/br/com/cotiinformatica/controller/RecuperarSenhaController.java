package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecuperarSenhaController {

	@RequestMapping(value = "/recuperar-senha") // caminho da p�gina no navegador
	public ModelAndView recuperarSenha() {
		// Mapeamento da p�gina que ser� exibida no navegador
		// WEB-INF/views/recuperar-senha.jsp
		ModelAndView modelAndView = new ModelAndView("recuperar-senha");
		return modelAndView;
	}
}
