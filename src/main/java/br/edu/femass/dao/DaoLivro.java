package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Livro;

public class DaoLivro extends DaoBd<Livro>{
    public List<Livro> listarTodos(){
        return em.createQuery("select l from Livro l order by l.id asc").getResultList();
    }
}
