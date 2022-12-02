package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAquisicao;
    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro;
    private boolean disponivel;

    public Exemplar(LocalDate dataAquisicao, Livro livro) {
        if(livro == null){
            throw new IllegalArgumentException("POR FAVOR, CADASTRAR UM LIVRO PARA PODER CADASTRAR SEU EXEMPLAR.");
        }
        else {
            this.dataAquisicao = dataAquisicao;
            this.livro = livro;
            disponivel = true;
        }
    }

    public Exemplar() {
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        StringBuilder autores = new StringBuilder();
        for(int i = 0; i < this.livro.getAutores().size(); i++){
            if(autores.toString().equals("")){
                autores = new StringBuilder(this.livro.getAutores().get(i).getNome() + " " + this.livro.getAutores().get(i).getSobrenome());
            }
            else{
                autores.append(", ").append(this.livro.getAutores().get(i).getNome()).append(" ").append(this.livro.getAutores().get(i).getSobrenome());
            }
        }

        return "Código: " + this.id + " | " + this.livro.getTitulo().toUpperCase() + " | Autores: " + autores.toString().toUpperCase();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Exemplar exemplar = (Exemplar) obj;
        if(exemplar.getId().equals(this.id)) return true;
        return false;

    }
}
