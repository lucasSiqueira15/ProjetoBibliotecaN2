package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Emprestimo;

public class DaoEmprestimo extends DaoBd<Emprestimo>{
    public List<Emprestimo> listarTodos(){
        return em.createQuery("select e from Emprestimo e order by e.id desc").getResultList();
    }

    public List<Emprestimo> listarTodosDevolvidos(){
        return em.createQuery("select e from Emprestimo e where e.datadevolucao is not null order by e.id desc").getResultList();
    }

    public List<Emprestimo> listarTodosEmprestados(){
        return em.createQuery("select e from Emprestimo e where e.datadevolucao is null order by e.id desc").getResultList();
    }

    public List<Emprestimo> listarTodosEmprestadosVencidos(){
        return em.createQuery("select e from Emprestimo e where e.datadevolucao is null and e.dataprevistadevolucao < to_date(sysdate, 'yyyy-mm-dd') order by e.id desc").getResultList();
    }
}
