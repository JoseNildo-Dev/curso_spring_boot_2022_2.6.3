package br.com.josenildo.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.josenildo.api.models.Mensagem;
import br.com.josenildo.api.models.Pessoa;
import br.com.josenildo.api.repositories.Repositorio;

@Service
public class Servico {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    public ResponseEntity<?> cadastrar(Pessoa obj) {
        if(obj.getNome().equals("")) {
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloCodigo(int codigo) {
        if(acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("Não foi encontrada nenhuma pessoa.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> editar(Pessoa obj) {
        if(acao.countByCodigo(obj.getCodigo()) == 0) {
            mensagem.setMensagem("O codigo informado não existe.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if(obj.getNome().equals("")) {
            mensagem.setMensagem("É necessario informar um nome.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(obj.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else  {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remover(int codigo) {
        if(acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);

            mensagem.setMensagem("Pessoa removida com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
