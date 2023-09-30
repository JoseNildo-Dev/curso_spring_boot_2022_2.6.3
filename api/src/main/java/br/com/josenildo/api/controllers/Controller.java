package br.com.josenildo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.josenildo.api.models.Pessoa;
import br.com.josenildo.api.repositories.Repositorio;
import br.com.josenildo.api.services.Servico;

@RestController
public class Controller {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
        return servico.cadastrar(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar() {
        return servico.selecionar();
    }

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj) {
        return servico.editar(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo) {
        return servico.remover(codigo);
    }

    @GetMapping("/api/contador")
    public long contador() {
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes() {
        return acao.findByOrderByNome();
    }

    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2() {
        return acao.findByNomeOrderByIdadeDesc("José");
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem() {
        return acao.findByNomeContaining("l");
    }
    
    @GetMapping("/api/{id}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
        return servico.selecionarPeloCodigo(codigo);
    }
    
    @GetMapping("")
    public String mensagem() {
        return "Hello World!";
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Seja bem vindo(a)";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome) {
        return "Seja bem vindo(a)" + nome;
    }

    @PostMapping("/pessoa")    
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }
}
