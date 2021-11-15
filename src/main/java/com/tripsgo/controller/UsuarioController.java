package com.tripsgo.controller;

import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tripsgo.exception.UsuarioServiceException;
import com.tripsgo.model.Usuario;
import com.tripsgo.service.UsuarioService;
import com.tripsgo.util.Util;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String start() {
		return "index";
	}

	//metodo para pagina de erro
	
	@GetMapping("/erro")
	public ModelAndView error() {
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		model.addObject("usuario", new Usuario());
		return model;
	}
	
	@GetMapping( "/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("usuario/usuario");
		model.addObject("usuario", new Usuario());
		return model;
	}
	
	@GetMapping("/home-user")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("hotel/bemvindoHotelCliente");
		model.addObject("usuario", new Usuario());
		return model;
	}
	
	@GetMapping("/cadastro-usuario")
	public ModelAndView getCadastroUsuario( ) {
		ModelAndView model = new ModelAndView();
		model.addObject("usuario", new Usuario());
		model.setViewName("usuario/cadastro");
		return model;
	}
	
	@PostMapping("/salvar-usuario")
	public ModelAndView postCadastroUsuario( Usuario usuario) throws Exception {
		ModelAndView model = new ModelAndView();
		usuarioService.cadastrarUsuario(usuario);
		model.setViewName("redirect:/login");
		return model;
	}
	
	
	@PostMapping("/login")
	public ModelAndView postLoginUsuario(@Valid Usuario usuario, BindingResult br, HttpSession session, Errors errors) 
			throws NoSuchAlgorithmException, UsuarioServiceException {
		ModelAndView model = new ModelAndView();
		model.addObject("usuario", new Usuario());
		
		if(br.hasErrors()) {
			model.setViewName("usuario/usuario");
			
		}
		
		Usuario userLogin = usuarioService.loginUsuario(usuario.getUser(), Util.md5(usuario.getSenha()));
		
		if(userLogin == null) {
			model.addObject("msg", "Usuario não encontrado, tente novamente");
		}
		
		//else if para pagina de erro
		
		else if (errors.hasErrors()) {
	        return error();
	      }

		else {
			session.setAttribute("usuarioLogado", userLogin);
			return index();
		}
		
		return model;
	}
	
	
	@PostMapping("/logout")
	public ModelAndView postLogoutUsuario(HttpSession session) {
		session.invalidate();
		return login();
	}
}