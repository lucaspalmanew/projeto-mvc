package com.lcp.projetos;

import com.lcp.projetos.entities.Ingredientes;
import com.lcp.projetos.entities.Receita;
import com.lcp.projetos.entities.UnidadeMedida;
import com.lcp.projetos.repositories.IngredientesRepository;
import com.lcp.projetos.repositories.ReceitaRepository;
import com.lcp.projetos.repositories.UnidadeMedidaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PopularBanco implements CommandLineRunner {

	@Autowired
	private IngredientesRepository ingredientesRepository;
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Ingredientes i1 = new Ingredientes();
		i1.setId(1L);
		i1.setNome("leite");

		Ingredientes i2 = new Ingredientes();
		i2.setId(2L);
		i2.setNome("açúcar");
		
		Ingredientes i3 = new Ingredientes();
		i3.setId(3L);
		i3.setNome("cenoura");
		
		Ingredientes i4 = new Ingredientes();
		i4.setId(4L);
		i4.setNome("manteiga");
		
		Ingredientes i5 = new Ingredientes();
		i5.setId(5L);
		i5.setNome("ovo");
		
		Ingredientes i6 = new Ingredientes();
		i6.setId(6L);
		i6.setNome("chocolate");
		
		Ingredientes i7 = new Ingredientes();
		i7.setId(7L);
		i7.setNome("limão");
		
		Ingredientes i8 = new Ingredientes();
		i8.setId(8L);
		i8.setNome("gelo");

		Ingredientes i9 = new Ingredientes();
		i9.setId(9L);
		i9.setNome("água");
		
		ingredientesRepository.save(i1);
		ingredientesRepository.save(i2);
		ingredientesRepository.save(i3);
		ingredientesRepository.save(i4);
		ingredientesRepository.save(i5);
		ingredientesRepository.save(i6);
		ingredientesRepository.save(i7);
		ingredientesRepository.save(i8);
		ingredientesRepository.save(i9);

	
		Receita r1 = new Receita();
		r1.setNome("Bolo de cenoura");
		//r1.setId(1L);
		r1.setModoPreparo("Em um liquidificador, adicione a cenoura, os ovos e o óleo, depois misture.\r\n"
				+ "\r\n"
				+ "Acrescente o açúcar e bata novamente por 5 minutos.\r\n");
		r1.setTempoPreparo(40);
		r1.setIngredientes(ingredientesRepository.findAll().subList(0, 4));
		
		
		Receita r2 = new Receita();
		r2.setNome("Limonada");
		//r2.setId(2L);
		r2.setModoPreparo("Esprema os limões e misture com a água, açúcar e o gelo.");
		r2.setTempoPreparo(10);
		r2.setIngredientes(ingredientesRepository.findAll().subList(6, 9));
		
		receitaRepository.save(r1);
		receitaRepository.save(r2);
		
		
		UnidadeMedida u1 = new UnidadeMedida();
		u1.setNome("Copo");
		
		unidadeMedidaRepository.save(u1);
	}
}