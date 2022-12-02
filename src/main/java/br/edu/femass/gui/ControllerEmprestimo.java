// package femass.gui;

// import femass.dao.*;
// import femass.model.*;
// import javax.swing.*;
// import javax.swing.text.MaskFormatter;
// import java.awt.event.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.List;

// public class GuiEmprestimo {
//     private JPanel jPainel;
//     private JButton btnCadEmprestimo;
//     private JComboBox<Exemplar> cboExemplar;
//     private JComboBox<Leitor> cboLeitor;
//     private JFormattedTextField txtDataEmprestimo;
//     private JFormattedTextField txtDataPrevDevolucao;
//     private JButton btnNovoLeitor;
//     private JButton btnVoltar;
//     private JCheckBox chbProfessor;
//     private JCheckBox chbAluno;
//     private JFrame telaFechar;

//     public GuiEmprestimo() {
//         btnCadEmprestimo.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
//                     daoEmprestimo.atualizarId();

//                     Emprestimo emprestimo = new Emprestimo((Exemplar) cboExemplar.getSelectedItem(), (Leitor) cboLeitor.getSelectedItem());

//                     daoEmprestimo.gravar(emprestimo);

//                     DaoExemplar daoExemplar = new DaoExemplar();
//                     daoExemplar.atualizarExemplar(emprestimo.getExemplar());

//                     limparDados();
//                 } catch (Exception ex) {
//                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         });
//         btnVoltar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiPrincipal telaPrincipal = new GuiPrincipal();
//                 telaFechar.dispose();
//                 telaPrincipal.abrirTela();
//             }
//         });
//         btnNovoLeitor.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiLeitor telaLeitor = new GuiLeitor();
//                 telaFechar.dispose();
//                 telaLeitor.abrirTela(new GuiEmprestimo().toString());
//             }
//         });
//         chbProfessor.addItemListener(new ItemListener() {
//             @Override
//             public void itemStateChanged(ItemEvent e) {
//                 try {
//                     if (e.getStateChange() == 1) {
//                         atualizarComboProfessor();
//                         chbAluno.setSelected(false);
//                     }
//                     else {
//                         if (chbAluno.isSelected() == false) {
//                             limparComboLeitor();
//                         }
//                     }
//                 }
//                 catch (Exception ex) {
//                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         });
//         chbAluno.addItemListener(new ItemListener() {
//             @Override
//             public void itemStateChanged(ItemEvent e) {
//                 try {
//                     if (e.getStateChange() == 1) {
//                         atualizarComboAluno();
//                         chbProfessor.setSelected(false);
//                     }
//                     else {
//                         if (chbProfessor.isSelected() == false) {
//                             limparComboLeitor();
//                         }
//                     }
//                 }
//                 catch (Exception ex) {
//                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         });
//         cboLeitor.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if(cboLeitor.isPopupVisible()){
//                     if(chbAluno.isSelected()){
//                         Aluno aluno = (Aluno) cboLeitor.getSelectedItem();
//                         if (aluno != null){
//                             LocalDate previsaoDevolucao = LocalDate.now().plusDays(aluno.getPrazoMaximoDevolucao());

//                             txtDataEmprestimo.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                             txtDataPrevDevolucao.setText(previsaoDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                         }
//                     }
//                     else{
//                         if(chbProfessor.isSelected()){
//                             Professor professor = (Professor) cboLeitor.getSelectedItem();
//                             if(professor != null){
//                                 LocalDate previsaoDevolucao = LocalDate.now().plusDays(professor.getPrazoMaximoDevolucao());

//                                 txtDataEmprestimo.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                                 txtDataPrevDevolucao.setText(previsaoDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                             }
//                         }
//                     }
//                 }
//             }
//         });
//     }

//     public void abrirTela(){
//         JFrame tela = new JFrame();
//         telaFechar = tela;
//         tela.setTitle("Cadastro de Empréstimos");
//         tela.setContentPane(jPainel);
//         tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         try{
//             atualizarComboExemplares();
//             inserirMascaraData();
//             chbAluno.setSelected(false);
//             chbProfessor.setSelected(false);
//         }
//         catch (Exception ex){
//             JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//         }

//         tela.pack();
//         tela.setVisible(true);
//     }

//     private void atualizarComboAluno() throws Exception{
//         cboLeitor.removeAllItems();
//         List<Aluno> alunos = new DaoAluno().consultar();
//         for(Aluno aluno : alunos){
//             cboLeitor.addItem(aluno);
//         }
//         cboLeitor.setSelectedIndex(-1);
//     }

//     private void atualizarComboProfessor() throws Exception{
//         cboLeitor.removeAllItems();
//         List<Professor> professores = new DaoProfessor().consultar();
//         for(Professor professor : professores){
//             cboLeitor.addItem(professor);
//         }
//         cboLeitor.setSelectedIndex(-1);
//     }

//     private void limparComboLeitor(){
//         cboLeitor.removeAllItems();
//     }

//     private void atualizarComboExemplares() throws Exception{
//         cboExemplar.removeAllItems();
//         List<Exemplar> exemplares = new DaoExemplar().consultar();
//         for(Exemplar exemplar : exemplares){
//             if(exemplar.getDisponivel()){
//                 cboExemplar.addItem(exemplar);
//             }
//         }
//         cboExemplar.setSelectedIndex(-1);
//     }

//     private void inserirMascaraData() throws Exception{
//         MaskFormatter mascaraData = new MaskFormatter("##/##/####");
//         mascaraData.install(txtDataEmprestimo);
//         mascaraData.install(txtDataPrevDevolucao);
//     }

//     private void limparDados() throws Exception{
//         cboLeitor.removeAllItems();
//         chbProfessor.setSelected(false);
//         chbAluno.setSelected(false);
//         txtDataEmprestimo.setText("");
//         txtDataPrevDevolucao.setText("");
//         atualizarComboExemplares();
//     }

//     @Override
//     public String toString() {
//         return "Emprestimo";
//     }
// }
