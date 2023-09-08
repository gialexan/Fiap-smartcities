package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
