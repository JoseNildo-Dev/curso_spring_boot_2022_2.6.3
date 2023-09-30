package br.com.josenildo.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.josenildo.api.models.Pessoa;

import java.util.List;


@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {

    List<Pessoa> findAll();

    Pessoa findByCodigo(int codigo);

    List<Pessoa> findByOrderByNome();

    List<Pessoa> findByNomeOrderByIdadeDesc(String nome);

    List<Pessoa> findByNomeContaining(String termo);

    int countByCodigo(int codigo);
    
}
