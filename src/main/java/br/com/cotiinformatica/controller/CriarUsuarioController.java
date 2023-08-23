package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.MD5Helper;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class CriarUsuarioController {
	@Autowired // autoinicializa��o
	UsuarioRepository usuarioRepository; // atributo

	@RequestMapping(value = "/criar-usuario") // caminho da p�gina no navegador
	public ModelAndView criarUsuario() {
		// Mapeamento da p�gina que ser� exibida no navegador
		// WEB-INF/views/criar-conta.jsp
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		return modelAndView;
	}

	// m�todo para capturar o submit post do formul�rio
	@RequestMapping(value = "/criar-usuario-post", method = RequestMethod.POST)
	public ModelAndView criarUsuarioPost(HttpServletRequest request) {
		// Mapeamento da p�gina que ser� exibida no navegador
		// WEB-INF/views/criar-conta.jsp
		ModelAndView modelAndView = new ModelAndView("criar-usuario");

		try {

			Usuario usuario = new Usuario();

			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(MD5Helper.encrypt(request.getParameter("senha")));

			// verificar se o email j� est� cadastrado no banco de dados
			if (usuarioRepository.find(usuario.getEmail()) != null) {
				modelAndView.addObject("mensagem_erro", "O email informado j� est� cadastrado. Tente outro.");
			} else {
				usuarioRepository.create(usuario); // cadastrando o usu�rio
				modelAndView.addObject("mensagem_sucesso", "Usu�rio cadastrado com sucesso!");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Erro: " + e.getMessage());
		}

		return modelAndView;
	}
}
