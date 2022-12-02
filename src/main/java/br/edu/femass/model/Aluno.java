package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Aluno extends Leitor{

    private String matricula;

    public Aluno(String nome, String endereco, String telefone, String matricula){
        super();
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        prazoMaximoDevolucao = 15L;
    }

    public Aluno(){
        super();
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "Codigo: " + this.getId() + " | Nome: " + this.getNome().toUpperCase()+
                " | Matricula: " + this.matricula;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Aluno aluno = (Aluno) obj;
        if(aluno.getId().equals(this.id)) return true;
        return false;
    }
}
