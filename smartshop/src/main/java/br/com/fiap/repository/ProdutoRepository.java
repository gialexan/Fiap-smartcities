package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.entity.Produto;

/*
 * Nomenclatura:
 * Os métodos estão misturados em inglês e português porque
 * são os nomes dos atributos + métodos de pesquisas que Spring
 * determina para gente.
 * 
 */

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	List<Produto> findByNome(String prod);

	List<Produto> findByNovo(boolean novo);
	
	List<Produto> findByNomeAndNovo(String prod, boolean novo);
	
	List<Produto> findByPrecoGreaterThan(double preco);

}
