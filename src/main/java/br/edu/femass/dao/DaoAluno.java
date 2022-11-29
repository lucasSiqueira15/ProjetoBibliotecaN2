package br.edu.femass.dao;

import java.util.List;
import br.edu.femass.model.Aluno;

public class DaoAluno extends DaoBd<Aluno> {
    public List<Aluno> listarTodos(){
        return em.createQuery("select a from Aluno a order by a.nome asc").getResultList();
    }
}
