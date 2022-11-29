package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Emprestimo;

public class DaoEmprestimo extends DaoBd<Emprestimo>{
    public List<Emprestimo> listarTodos(){
        return em.createQuery("select e from Emprestimo e order by dataEmprestimo desc").getResultList();
    }
}
