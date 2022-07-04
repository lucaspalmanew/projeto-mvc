package com.lcp.projetos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lcp.projetos.entities.UnidadeMedida;
import com.lcp.projetos.services.UnidadeMedidaService;
import com.lcp.projetos.services.ReceitaService;
import com.lcp.projetos.services.IngredientesService;

@Controller
@RequestMapping("unidadeMedida")
public class UnidadeMedidaController {
	
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
	@Autowired
	private IngredientesService ingredientesService;
	
	@Autowired
	private ReceitaService receitaService;
		
	@RequestMapping(path = "editar")
	public ModelAndView editarUnidadeMedida(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("unidadeMedida/form.html");
		
		UnidadeMedida unidadeMedida;
		
		if(id==null) {
			unidadeMedida = new UnidadeMedida();
		}else {
			try {
				unidadeMedida = unidadeMedidaService.obterUnidadeMedida(id);
			}catch(Exception e) {
				unidadeMedida = new UnidadeMedida();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("unidadeMedida", unidadeMedida);
		mv.addObject("listaIngredientes", ingredientesService.listarTodosIngredientes());
		mv.addObject("listaReceita", receitaService.listarTodasReceitas());
		
		
		return mv;
		
	}
	
	@RequestMapping(path = "novo")
	public ModelAndView novaUnidadeMedida() {
		
		ModelAndView mv = new ModelAndView("unidadeMedida/form.html");
		mv.addObject("unidadeMedida", new UnidadeMedida());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST , path = "novo")
	public ModelAndView salvarUnidadeMedida(@Valid UnidadeMedida unidadeMedida, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("unidadeMedida/form.html");
		
		boolean novo = true;
		
		if(unidadeMedida.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("unidadeMedida", unidadeMedida);
			return mv;
		}
		
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);
	
		if(novo) {
			mv.addObject("unidadeMedida", new UnidadeMedida());
		}else {
			mv.addObject("unidadeMedida", unidadeMedida);
		}
		
		mv.addObject("mensagem", "UnidadeMedida salvo com sucesso");
		mv.addObject("listaIngredientes", ingredientesService.listarTodosIngredientes());
		mv.addObject("listaReceita", receitaService.listarTodasReceitas());		
		
		return mv;
		
	}
	
	@RequestMapping
	public ModelAndView listarUnidadeMedida(String nome) {
		
		ModelAndView mv = new ModelAndView("unidadeMedida/listar.html");
		
		mv.addObject("lista", unidadeMedidaService.listarUnidadeMedida(nome));
		
		return mv;
		
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirUnidadeMedida(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/unidadeMedida");

		try {
			unidadeMedidaService.excluirUnidadeMedida(id);
			 redirectAttributes.addFlashAttribute("mensagem", "UnidadeMedida excluído com sucesso.");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir UnidadeMedida!"+e.getMessage());
		}
				
		return mv;
	}
}
