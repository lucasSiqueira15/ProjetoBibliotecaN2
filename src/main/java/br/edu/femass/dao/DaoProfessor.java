package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Professor;

public class DaoProfessor extends DaoBd<Professor> {
    public List<Professor> listarTodos(){
        return em.createQuery("select p from Professor p order by p.nome asc").getResultList();
    }
}
