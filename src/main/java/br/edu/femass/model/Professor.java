package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professor extends Leitor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String disciplina;

    public Professor(String nome, String endereco, String telefone, String disciplina){
        super();
        this.disciplina = disciplina;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        prazoMaximoDevolucao = 30L;
    }

    public Professor(){
        super();
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    @Override
    public String toString() {
        return "Codigo: " + this.getId() + " | Nome: " + this.getNome().toUpperCase()+
                " | Disciplina: " + this.disciplina.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Professor professor = (Professor) obj;
        if(professor.getId().equals(this.id)) return true;
        return false;
    }
}
