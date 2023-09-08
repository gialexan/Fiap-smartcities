package br.com.fiap.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.ProdutoRepository;

/*
 * A notação "@Controller" informa ao Spring que esta classe possui um 
 * Controller com mapeamentos (rotas).
 * 
 * A notação "@PathVariable" é uma forma de avisar ao Spring MVC que uma determinada 
 * parte da URL virá como se fosse uma variável dentro do método.
 * 
 * A anotação "@Valid" verifica se o objeto (que virá da telaque, no caso, será o formulário de cadastro) 
 * é válido.
 * 
 *  O Parâmetro do tipo BindingResult, que tem a função de guardar eventuais mensagens 
 *  de erro na validação. Esse objeto possui um método chamado hasErrors(), responsável por 
 *  verificar se existemou não erros de validação no código.
 */

/*
 * A escolha entre usar o RedirectAttributes ou o Model depende do cenário em que você está trabalhando e das suas necessidades específicas.
 *
 *	RedirectAttributes:
 *
 *	Use o RedirectAttributes quando você precisa passar atributos entre solicitações após um redirecionamento.
 *	É útil para exibir mensagens temporárias, como mensagens de sucesso ou erros, após uma ação ser realizada (por exemplo, após um formulário ser enviado com sucesso).
 *	Os atributos adicionados usando addFlashAttribute são mantidos apenas para a próxima solicitação e são automaticamente removidos depois disso.
 *	Model:
 *
 *	Use o Model quando você deseja passar dados para a camada de visualização (View) sem redirecionar.
 *	É apropriado para compartilhar informações que serão usadas para renderizar a página que está sendo retornada pela solicitação atual.
 *	Os atributos adicionados ao Model são disponibilizados para a visualização durante a renderização da página.
 *	Se você está lidando com mensagens de sucesso ou erro que devem ser exibidas após uma ação (como envio de formulário) e quer garantir que essas mensagens estejam disponíveis apenas na próxima solicitação, o RedirectAttributes é uma escolha adequada.
 *
 *	Por outro lado, se você está compartilhando dados para renderizar uma página ou fornecer informações para a visualização sem redirecionar, o Model é mais apropriado.
 *
 *	Em alguns casos, você pode usar ambos. Por exemplo, pode usar o RedirectAttributes para passar uma mensagem de sucesso após uma ação e o Model para compartilhar dados com a página para a qual você redirecionou.
 */

@Controller 
@RequestMapping("produto") 
public class ProdutoController { 

    @Autowired 
    private ProdutoRepository repository; 

    @Autowired
    private CategoriaRepository categoriaRepository; 

    @GetMapping("cadastrar")
    public String abrirFormulario(Produto produto, Model model){
        model.addAttribute("categorias", categoriaRepository.findAll()); 
        return "produto/form";
    }

    @PostMapping("cadastrar") 
    public String processarForm(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes){ 
        if(result.hasErrors()) {
            return "produto/form";
        }
        redirectAttributes.addFlashAttribute("msg", "Cadastrado!");
        repository.save(produto);
        return "redirect:listar";
    } 

    @GetMapping("listar") 
    public String listarProdutos(Model model){ 
        model.addAttribute("produtos", repository.findAll()); 
        return "produto/lista";
    } 

    @GetMapping("editar/{id}") 
    public String editar(@PathVariable("id") int codigo, Model model){ 
        model.addAttribute("produto",repository.findById(codigo));
        model.addAttribute("categorias", categoriaRepository.findAll()); 
        return "produto/form"; 
    } 

    @PostMapping("excluir") 
    public String remover(int codigo, RedirectAttributes redirectAttributes) { 
        redirectAttributes.addFlashAttribute("msg", "Removido!"); 
        repository.deleteById(codigo); 
        return "redirect:listar"; 
    } 
} 