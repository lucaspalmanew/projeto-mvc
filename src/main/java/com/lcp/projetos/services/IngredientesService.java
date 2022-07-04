package com.lcp.projetos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcp.projetos.entities.Ingredientes;
import com.lcp.projetos.repositories.IngredientesRepository;

@Service
public class IngredientesService {
	
	@Autowired
	private IngredientesRepository ingredientesRepository;
	
	public Ingredientes salvarIngredientes(Ingredientes ingredientes) {
		
		return ingredientesRepository.save(ingredientes);
		
	}
	
	public List<Ingredientes> listarTodosIngredientes(){
		
		return ingredientesRepository.findAll();
		
	}
	
	public List<Ingredientes> listarIngredientesPorNome(String nomepesquisa) {
		
		return ingredientesRepository.findByNomeContains(nomepesquisa);
	}
	
	public List<Ingredientes> listarIngredientes(String nomepesquisa) {
		
		if(nomepesquisa != null) {
			return listarIngredientesPorNome(nomepesquisa);
		}
		return listarTodosIngredientes();
	}
	
	public Ingredientes obterIngredientes(Long id) throws Exception {
		
		Optional<Ingredientes> ingredientes = ingredientesRepository.findById(id);
		
		if(ingredientes.isEmpty()) {
			throw new Exception("ingrediente não encontrada.");
		}
		
		return ingredientes.get();
		
	}

	public void excluirIngredientes(Long id) {
		ingredientesRepository.deleteById(id);
	}

}
