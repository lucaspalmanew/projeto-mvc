package com.lcp.projetos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcp.projetos.entities.Receita;
import com.lcp.projetos.repositories.ReceitaRepository;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	public Receita salvarReceita(Receita receita) {
		
		return receitaRepository.save(receita);
		
	}
	
	public List<Receita> listarTodasReceitas(){
		
		return receitaRepository.findAll();
		
	}
	
	public List<Receita> listarReceitasPorNomeEIngredientes(String nome, String ingredientes) {
		
		return receitaRepository.findDistinctByNomeAndIngredientes_NomeContains(nome, ingredientes.toString());
	}
	
	public List<Receita> listarReceitas(String nome, String ingredientes) {
		
		if(nome != null || ingredientes != null) 
			return listarReceitasPorNomeEIngredientes(nome, ingredientes);
		else
		return listarTodasReceitas();
	}
	
	public Receita obterReceita(Long id) throws Exception{
		
		Optional<Receita> receita = receitaRepository.findById(id);
		
		if(receita.isEmpty()) {
			throw new Exception("Receita não encontrada");
		}
		
		return receita.get();
		
		
	}
	
	public void excluirReceita(Long id) {
		
		receitaRepository.deleteById(id);
		
	}
}
