package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Exemplar;

public class DaoExemplar extends DaoBd<Exemplar>{
    public List<Exemplar> listarTodos(){
        return em.createQuery("select e from Exemplar e order by e.id asc").getResultList();
    }
}
