package com.lcp.projetos.repositories;



import java.util.List;

import com.lcp.projetos.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	List<Receita> findByNomeContains(String nome);
	
	List<Receita> findDistinctByNomeAndIngredientes_NomeContains(String nome, String ingredientes);
}
