package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Emprestimo;

public class DaoEmprestimo extends DaoBd<Emprestimo>{
    public List<Emprestimo> listarTodos(){
        return em.createQuery("select e from Emprestimo e order by e.id asc").getResultList();
    }

    public List<Emprestimo> listarTodosDevolvidos(){
        return em.createQuery("select e from Emprestimo e where e.dataDevolucao IS NOT NULL order by e.id asc").getResultList();
    }

    public List<Emprestimo> listarTodosEmprestados(){
        return em.createQuery("select e from Emprestimo e where e.dataDevolucao IS NULL order by e.id asc").getResultList();
    }
}
