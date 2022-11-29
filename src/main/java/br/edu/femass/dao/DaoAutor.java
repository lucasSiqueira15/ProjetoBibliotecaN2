package br.edu.femass.dao;

import java.util.List;
import br.edu.femass.model.Autor;

public class DaoAutor extends DaoBd<Autor>{

    public List<Autor> listarTodos(){
        return em.createQuery("select a from Autor a order by a.nome asc").getResultList();
    }
}
