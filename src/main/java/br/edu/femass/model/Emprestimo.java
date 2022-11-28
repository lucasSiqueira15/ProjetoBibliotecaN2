package br.edu.femass.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private Exemplar exemplar;
    private Leitor leitor;

    public Emprestimo(Exemplar exemplar, Leitor leitor) {
        if(exemplar == null & leitor != null){
            throw new IllegalArgumentException("POR FAVOR, INSERIR UM EXEMPLAR PARA PODER REALIZAR O EMPRÉSTIMO.");
        }
        else {
            if(leitor == null & exemplar != null){
                throw new IllegalArgumentException("POR FAVOR, INSERIR UM LEITOR PARA PODER REALIZAR O EMPRÉSTIMO.");
            }
            else{
                if(exemplar == null & leitor == null){
                    throw new IllegalArgumentException("POR FAVOR, INSERIR UM LEITOR E UM EXEMPLAR PARA PODER REALIZAR O EMPRÉSTIMO.");
                }
                else{
                    this.dataEmprestimo = LocalDate.now();
                    this.leitor = leitor;
                    this.dataPrevistaDevolucao = dataEmprestimo.plusDays(leitor.getPrazoMaximoDevolucao());
                    this.dataDevolucao = null;
                    this.exemplar = exemplar;
                    this.exemplar.setDisponivel(false);
                }
            }
        }
    }

    public Emprestimo() {
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void devolverEmprestimo(){
        this.exemplar.setDisponivel(true);
        this.dataDevolucao = LocalDate.now();
    }

    @Override
    public String toString() {
        if(this.dataDevolucao == null){
            return "ID: " + this.id
                    + " | Leitor: " + this.leitor.toString()
                    + " | Exemplar: " + this.exemplar.toString()
                    + " | Data Emprestimo: " + this.dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " | Data Prevista Devolução: " + this.dataPrevistaDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " | Data Devolução: Pendente";
        }
        return "ID: " + this.id
                + " | Leitor: " + this.leitor.toString()
                + " | Exemplar: " + this.exemplar.toString()
                + " | Data Emprestimo: " + this.dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " | Data Prevista Devolução: " + this.dataPrevistaDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " | Data Devolução: " + this.dataDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;

        Emprestimo emprestimo = (Emprestimo) obj;
        if(emprestimo.getId().equals(this.id)) return true;
        return false;
    }
}
