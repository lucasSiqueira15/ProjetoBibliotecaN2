package br.edu.femass.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String ano;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Autor> autores;

    public Livro(String titulo, String ano, List<Autor> autores) {
        if(autores.isEmpty()){
            throw new IllegalArgumentException("POR FAVOR, INSERIR UM AUTOR PARA PODER CADASTRAR SEU LIVRO.");
        }
        else {
            this.titulo = titulo;
            this.autores = autores;
            this.ano = ano;
        }
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

    @Override
    public String toString() {
        StringBuilder autores = new StringBuilder();
        for(int i = 0; i < this.autores.size(); i++){
            if(autores.toString().equals("")){
                autores = new StringBuilder(this.autores.get(i).getNome() + " " + this.autores.get(i).getSobrenome());
            }
            else{
                autores.append(", ").append(this.autores.get(i).getNome()).append(" ").append(this.autores.get(i).getSobrenome());
            }
        }

        return "Código: " + this.id + " | Título: " + this.titulo.toUpperCase() + " | Ano: " + this.ano.toUpperCase() + " | Autores: " + autores.toString().toUpperCase();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Livro livro = (Livro) obj;
        if(livro.getId().equals(this.id)) return true;
        return false;
    }
}
