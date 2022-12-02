// package femass.gui;

// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class GuiLeitor {
//     private JPanel jPainel;
//     private JList lstLeitor;
//     private JTextField txtCodigo;
//     private JTextField txtNome;
//     private JTextField txtEndereco;
//     private JTextField txtTelefone;
//     private JTextField txtDevolucao;
//     private JComboBox cboLeitor;
//     private JTextField txtDisciplina;
//     private JTextField txtMatricula;
//     private JButton btnCadastrarProf;
//     private JButton btnVoltar;
//     private JButton btnCadastrarAluno;
//     private JFrame telaFechar;
//     private String telaNova;

//     public GuiLeitor() {
//         btnCadastrarProf.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiProfessor telaProfessor = new GuiProfessor();
//                 telaFechar.dispose();
//                 telaProfessor.abrirTela(telaNova);
//             }
//         });
//         btnCadastrarAluno.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiAluno telaAluno = new GuiAluno();
//                 telaFechar.dispose();
//                 telaAluno.abrirTela(telaNova);
//             }
//         });
//         btnVoltar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if(telaNova.equals("Principal")){
//                     GuiPrincipal telaPrincipal = new GuiPrincipal();
//                     telaFechar.dispose();
//                     telaPrincipal.abrirTela();
//                 }
//                 else{
//                     if(telaNova.equals("Emprestimo")){
//                         GuiEmprestimo telaEmprestimo = new GuiEmprestimo();
//                         telaFechar.dispose();
//                         telaEmprestimo.abrirTela();
//                     }
//                     else{
//                         JOptionPane.showMessageDialog(null, "Erro ao carregar a tela anterior.", "Alerta", JOptionPane.ERROR_MESSAGE);
//                     }
//                 }
//             }
//         });
//     }

//     public void abrirTela(String telaAnterior){
//         JFrame tela = new JFrame();
//         telaFechar = tela;
//         telaNova = telaAnterior;
//         tela.setTitle("Opções de Cadastro de Leitores");
//         tela.setContentPane(jPainel);
//         tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         tela.pack();
//         tela.setVisible(true);
//     }

// }
