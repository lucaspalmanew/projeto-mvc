package com.lcp.projetos.repositories;



import java.util.List;

import com.lcp.projetos.entities.Ingredientes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IngredientesRepository extends JpaRepository<Ingredientes, Long> {

	List<Ingredientes> findByNomeContains(String nomepesquisa);
	
}
