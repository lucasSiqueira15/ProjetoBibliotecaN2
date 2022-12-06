package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAquisicao;
    @ManyToOne
    @JoinTable(
            name="Exemplar_Livro",
            joinColumns = @JoinColumn(name="exemplar_id"),
            inverseJoinColumns = @JoinColumn(name="livro_id")
    )
    private Livro livro;
    private Boolean disponivel;

    public Exemplar(LocalDate dataAquisicao, Livro livro) {
        this.dataAquisicao = dataAquisicao;
        this.livro = livro;
        disponivel = true;
    }

    public Exemplar() {
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
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
        return "Código: " + this.id + " | " + this.livro.getTitulo().toUpperCase() + " | Autor: " + this.livro.getAutor().toString().toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Exemplar exemplar = (Exemplar) obj;
        if(exemplar.getId().equals(this.id)) return true;
        return false;

    }
}
