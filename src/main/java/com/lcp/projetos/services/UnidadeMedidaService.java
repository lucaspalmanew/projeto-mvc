package com.lcp.projetos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcp.projetos.entities.UnidadeMedida;
import com.lcp.projetos.repositories.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {
	
	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public UnidadeMedida salvarUnidadeMedida(UnidadeMedida unidadeMedida) {
		
		return unidadeMedidaRepository.save(unidadeMedida);
		
	}
	
	public List<UnidadeMedida> listarTodasUnidadeMedidas(){
		
		return unidadeMedidaRepository.findAll();
		
	}
	
	public List<UnidadeMedida> listarUnidadeMedidaPorNome(String nome) {
		
		return unidadeMedidaRepository.findByNomeContains(nome);
	}
	
	public List<UnidadeMedida> listarUnidadeMedida(String nome) {
		
		if(nome != null) {
			return listarUnidadeMedidaPorNome(nome);
		}
		return listarTodasUnidadeMedidas();
	}
	
	
	public UnidadeMedida obterUnidadeMedida(Long id) throws Exception{
		
		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);
		
		if(unidadeMedida.isEmpty()) {
			throw new Exception("UnidadeMedida não encontrada");
		}
		
		return unidadeMedida.get();
		
		
	}
	
	public void excluirUnidadeMedida(Long id) {
		
		unidadeMedidaRepository.deleteById(id);
		
	}

}
