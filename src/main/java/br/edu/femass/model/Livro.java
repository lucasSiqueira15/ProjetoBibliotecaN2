package br.edu.femass.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String ano;
    @OneToMany
    @JoinTable(
            name="Livro_Autor",
            joinColumns = @JoinColumn(name="livro_id"),
            inverseJoinColumns = @JoinColumn(name="autor_id")
    )
    private List<Autor> autores;

    public Livro(String titulo, String ano, List<Autor> autores) {
        this.titulo = titulo;
        this.autores = autores;
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void adicionarAutor(Autor autor) {
        this.autores.add(autor);
    }

    public void excluirAutor(Autor autor) {
        this.autores.remove(autor);
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        StringBuilder autores = new StringBuilder();
        for (int i = 0; i < this.autores.size(); i++) {
            if (autores.toString().equals("")) {
                autores = new StringBuilder(this.autores.get(i).getNome() + " " + this.autores.get(i).getSobrenome());
            } else {
                autores.append(", ").append(this.autores.get(i).getNome()).append(" ")
                        .append(this.autores.get(i).getSobrenome());
            }
        }

        return "Código: " + this.id + " | Título: " + this.titulo.toUpperCase() + " | Ano: " + this.ano.toUpperCase()
                + " | Autores: " + autores.toString().toUpperCase();
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
