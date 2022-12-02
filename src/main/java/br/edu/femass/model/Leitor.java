package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected Long prazoMaximoDevolucao;

    public Leitor(){
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setPrazoMaximoDevolucao(Long prazoMaximoDevolucao) {
        this.prazoMaximoDevolucao = prazoMaximoDevolucao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public Long getPrazoMaximoDevolucao() {
        return prazoMaximoDevolucao;
    }

    @Override
    public String toString() {
        return "Codigo: " + this.id + " | Nome: " + this.nome.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Leitor leitor = (Leitor) obj;
        if(leitor.getId().equals(this.id)) return true;
        return false;
    }
}
