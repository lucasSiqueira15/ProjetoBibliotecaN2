package br.edu.femass.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    @ManyToOne
    @JoinTable(
            name="Emprestimo_Exemplar",
            joinColumns = @JoinColumn(name="emprestimo_id"),
            inverseJoinColumns = @JoinColumn(name="exemplar_id")
    )
    private Exemplar exemplar;

    @ManyToOne
    @JoinTable(
            name="Emprestimo_Aluno",
            joinColumns = @JoinColumn(name="emprestimo_id"),
            inverseJoinColumns = @JoinColumn(name="aluno_id")
    )
    private Aluno leitorAluno;

    @ManyToOne
    @JoinTable(
            name="Emprestimo_Professor",
            joinColumns = @JoinColumn(name="emprestimo_id"),
            inverseJoinColumns = @JoinColumn(name="professor_id")
    )
    private Professor leitorProfessor;

    public Emprestimo(Exemplar exemplar, Aluno leitorAluno) {
        if(exemplar == null & leitorAluno != null){
            throw new IllegalArgumentException("POR FAVOR, INSERIR UM EXEMPLAR PARA PODER REALIZAR O EMPRÉSTIMO.");
        }
        else {
            if(leitorAluno == null & exemplar != null){
                throw new IllegalArgumentException("POR FAVOR, INSERIR UM LEITOR PARA PODER REALIZAR O EMPRÉSTIMO.");
            }
            else{
                if(exemplar == null & leitorAluno == null){
                    throw new IllegalArgumentException("POR FAVOR, INSERIR UM LEITOR E UM EXEMPLAR PARA PODER REALIZAR O EMPRÉSTIMO.");
                }
                else{
                    this.dataEmprestimo = LocalDate.now();
                    this.leitorAluno = leitorAluno;
                    this.dataPrevistaDevolucao = dataEmprestimo.plusDays(leitorAluno.getPrazoMaximoDevolucao());
                    this.dataDevolucao = null;
                    this.exemplar = exemplar;
                    this.exemplar.setDisponivel(false);
                }
            }
        }
    }

    public Emprestimo(Exemplar exemplar, Professor leitorProfessor) {
        if(exemplar == null & leitorProfessor != null){
            throw new IllegalArgumentException("POR FAVOR, INSERIR UM EXEMPLAR PARA PODER REALIZAR O EMPRÉSTIMO.");
        }
        else {
            if(leitorProfessor == null & exemplar != null){
                throw new IllegalArgumentException("POR FAVOR, INSERIR UM LEITOR PARA PODER REALIZAR O EMPRÉSTIMO.");
            }
            else{
                if(exemplar == null & leitorProfessor == null){
                    throw new IllegalArgumentException("POR FAVOR, INSERIR UM LEITOR E UM EXEMPLAR PARA PODER REALIZAR O EMPRÉSTIMO.");
                }
                else{
                    this.dataEmprestimo = LocalDate.now();
                    this.leitorProfessor = leitorProfessor;
                    this.dataPrevistaDevolucao = dataEmprestimo.plusDays(leitorProfessor.getPrazoMaximoDevolucao());
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

    public Aluno getLeitorAluno() {
        return leitorAluno;
    }

    public Professor getLeitorProfessor() {
        return leitorProfessor;
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

        String leitor;

        if(this.leitorAluno == null){
            leitor = this.leitorProfessor.toString();
        }
        else{
            leitor = this.leitorAluno.toString();
        }

        if(this.dataDevolucao == null){
            return "ID: " + this.id
                    + " | Leitor: " + leitor
                    + " | Exemplar: " + this.exemplar.toString()
                    + " | Data Emprestimo: " + this.dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " | Data Prevista Devolução: " + this.dataPrevistaDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + " | Data Devolução: Pendente";
        }
        return "ID: " + this.id
                + " | Leitor: " + leitor
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
