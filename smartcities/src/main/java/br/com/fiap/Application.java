package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * H2
 * http://localhost:8080/h2
 * 
 * OBS: Se os inserts iniciais do DB não carregar no automático
 * será necessário inserir manualmente, o arquivo com os inserts 
 * é o populate-database.
 * 
 * Projeto
 * http://localhost:8080/produto/cadastrar
 * 
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
