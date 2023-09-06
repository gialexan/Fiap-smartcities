package br.com.fiap.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.spring.boot.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
