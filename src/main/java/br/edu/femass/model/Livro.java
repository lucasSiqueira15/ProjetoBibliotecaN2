package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String ano;
    @ManyToOne
    @JoinTable(
            name="Livro_Autor",
            joinColumns = @JoinColumn(name="livro_id"),
            inverseJoinColumns = @JoinColumn(name="autor_id")
    )
    private Autor autor;

    public Livro(String titulo, String ano, Autor autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public Livro() {
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAno() {
        return ano;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Código: " + this.id + " | Título: " + this.titulo.toUpperCase() + " | Ano: " + this.ano.toUpperCase()
                + " | Autor: " + this.autor.toString().toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        Livro livro = (Livro) obj;
        if (livro.getId().equals(this.id))
            return true;
        return false;
    }
}
