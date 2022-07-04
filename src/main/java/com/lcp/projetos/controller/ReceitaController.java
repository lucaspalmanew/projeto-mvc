package com.lcp.projetos.controller;


import javax.validation.Valid;

import com.lcp.projetos.entities.Receita;
import com.lcp.projetos.services.IngredientesService;
import com.lcp.projetos.services.ReceitaService;
import com.lcp.projetos.services.UnidadeMedidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("receita")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private IngredientesService ingredientesService;
		
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
	
	@RequestMapping(path = "novo")
	public ModelAndView novaReceita() {
		
		ModelAndView mv = new ModelAndView("receita/form.html");
		mv.addObject("receita", new Receita());
		
		return mv;
	}
	
	@RequestMapping(method =RequestMethod.POST, path = "novo")
	public ModelAndView salvarReceita(@Valid Receita receita, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("receita/form.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("receita", receita);
			return mv;
		}
		
		Receita receitaSalva = receitaService.salvarReceita(receita);
		
		
		if(receita.getId() == null) {
			mv.addObject("receita", new Receita());
		} else {
			mv.addObject("receita", receitaSalva);
		}
		
		mv.addObject("mensagem", "receita salva com sucesso");
		
		return mv;
	}
	
	
	@RequestMapping(path = "editar")
	public ModelAndView editarReceita(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("receita/form.html");

		Receita receita;

		if (id == null) {
			receita = new Receita();
		} else {
			try {
				receita = receitaService.obterReceita(id);
			} catch (Exception e) {
				receita = new Receita();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("receita", receita);
		mv.addObject("listaIngredientes", ingredientesService.listarTodosIngredientes());
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.listarTodasUnidadeMedidas());

		return mv;

	}
	
	
	@RequestMapping
	public ModelAndView listarReceita(String nome, String ingredientes) {
	
		ModelAndView mv = new ModelAndView("receita/listar.html");
		mv.addObject("lista", receitaService.listarReceitas(nome, ingredientes));
		mv.addObject("nome", nome);
		mv.addObject("ingredientes", ingredientes);
		
		return mv;
		
	}
	
	
	
	@RequestMapping("/excluir")
	public ModelAndView excluirReceita(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/receita");
		
		try {
			receitaService.excluirReceita(id);
			redirectAttributes.addFlashAttribute("mensagem", "receita excluída com sucesso");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir receita" + e.getMessage());
		}
				
		return mv;
	}
	

}
