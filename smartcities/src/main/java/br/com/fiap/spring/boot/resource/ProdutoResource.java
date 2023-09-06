package br.com.fiap.spring.boot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.spring.boot.entity.Produto;
import br.com.fiap.spring.boot.repository.ProdutoRepository;

/*
 * 
 * A notação "@RestController" éa annotationque habilita 
 * a externalização dessa classe como um websevice RESTFul
 * 
 * A notação "@Autowired" serve para SpringMVC colocar automaticamente
 * um objeto do tipo "EstacionamentoDAO dao" na variável.
 * 
 * A notação "@Transactional" serve para o SpringMVC habilitar automaticamente
 * as transações com o banco exemplo: commit.
 * 
 * A notação "@RequestMapping" habilita  uma  rota  com  caminho  específico 
 * (Estacionamento,nesse caso) para todas as sub-rotas dentro da classe, ou seja, todas as 
 * sub-rotas deverão conter um /Estacionamento/ na URL
 * 
 * A notação "@GetMapping" habilita uma rota do verbo GET,que nada mais é do que 
 * obter a listagem de todos os elementos de um repository.
 * 
 * A notação "@GetMapping({id})" habilita uma rota do verbo GET que 
 * recebe um ID de elemento a ser buscado.
 * 
 * A notação "@PostMapping" habilita uma rota do verbo POSTpara inserir um elemento.
 * 
 * A notação "@PathVariable" é uma forma de avisar ao Spring MVC que uma 
 * determinada parte da URL virá como se fosse uma variável dentro do método.
 * 
 * A notação "@PutMapping({id})" habilita uma rota do verbo PUT para atualizar um
 * elemento, passando o ID dele via URL.
 * 
 * A notação "@DeleteMapping({id})" habilita uma rota do verbo DELETE para remover
 * um elemento, passando oseuID via URL.
 * 
 * A notação "@ResponseStatus" contém o código HTTP de respostae varia conforme
 * o  tipo de operação realizada. Vide a listagem de códigos HTTP em: 
 * <https://en.wikipedia.org/wiki/List_of_HTTP_status_codes>
 * 
 * 
 * A notação "@RequestBody" faz o mapeamento dos dados que virão do requestem um
 * Bean. Nesse caso,quando os dados são recebidos, eles  são  colocados dentro 
 * de um Bean para uso dentro do método.
 * 
 */

@RestController
@RequestMapping("produto") 
public class ProdutoResource { 

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@GetMapping("{codigo}")
	public Produto buscar(@PathVariable int codigo) {
		return produtoRepository.findById(codigo).get();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Produto cadastrar(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

	@PutMapping("{id}")
	public Produto atualizar(@RequestBody Produto produto, @PathVariable int id) {
		produto.setCodigo(id);
		return produtoRepository.save(produto);
	}

	@DeleteMapping("{codigo}")
	public void remover(@PathVariable int codigo) {
		produtoRepository.deleteById(codigo);
	}

	@GetMapping("pesquisa")
	public List<Produto> buscar(@RequestParam(required = false) String nome,
			@RequestParam(defaultValue = "false") boolean novo) {
		return nome != null ? produtoRepository.findByNomeAndNovo(nome, novo) : produtoRepository.findByNovo(novo);
	}

}  
