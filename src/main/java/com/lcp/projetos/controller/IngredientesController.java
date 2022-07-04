package com.lcp.projetos.controller;

import javax.validation.Valid;

import com.lcp.projetos.entities.Ingredientes;
import com.lcp.projetos.services.IngredientesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("ingredientes")
public class IngredientesController {
	
	@Autowired
	private IngredientesService ingredientesService;
	

	
	
	@RequestMapping(path = "novo")
	public ModelAndView novaIngredientes() {
		
		ModelAndView mv = new ModelAndView("ingredientes/form.html");
		mv.addObject("ingredientes", new Ingredientes());
		
		return mv;
	}
	
	@RequestMapping(method =RequestMethod.POST, path = "novo")
	public ModelAndView salvarIngredientes(@Valid Ingredientes ingredientes, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("ingredientes/form.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("ingredientes", ingredientes);
			return mv;
		}
		
		Ingredientes ingredientesSalva = ingredientesService.salvarIngredientes(ingredientes);
		
		
		if(ingredientes.getId() == null) {
			mv.addObject("ingredientes", new Ingredientes());
		} else {
			mv.addObject("ingredientes", ingredientesSalva);
		}
		
		mv.addObject("mensagem", "ingrediente salvo com sucesso");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarTodosIngredientes(String nomepesquisa) {
	
		ModelAndView mv = new ModelAndView("ingredientes/listar.html");
		mv.addObject("lista", ingredientesService.listarIngredientes(nomepesquisa));
		
		return mv;
		
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarIngredientes(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("ingredientes/form.html");
		Ingredientes ingredientes;
		
		try {
			ingredientes = ingredientesService.obterIngredientes(id);
		} catch(Exception e) {
			ingredientes = new Ingredientes();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("ingredientes", ingredientes);
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirIngredientes(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/ingredientes");
		
		try {
			ingredientesService.excluirIngredientes(id);
			redirectAttributes.addFlashAttribute("mensagem", "ingredientes excluída com sucesso");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir ingredientes" + e.getMessage());
		}
				
		return mv;
	}
	
	/*@PostMapping("/pesquisaringredientes")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView mv = new ModelAndView("/ingredientes/listar.html");
		mv.addObject("lista", ingredientesService.listarIngredientesPorNome(nomepesquisa));
		return mv;
	}*/
	

}
