package br.edu.femass.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoBd<E>{

    protected static EntityManagerFactory emf;
    protected EntityManager em;

    static{
        try{
            emf = Persistence.createEntityManagerFactory("jpa_biblioteca");
        }
        catch(Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public DaoBd(){
        em = emf.createEntityManager();
    }

    public void adicionar(E entidade){
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }

    public void deletar(E entidade){
        em.getTransaction().begin();
        em.remove(entidade);
        em.getTransaction().commit();
    }

    public void modificar(E entidade){
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

}

