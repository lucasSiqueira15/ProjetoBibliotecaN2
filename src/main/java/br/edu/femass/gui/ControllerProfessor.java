// package femass.gui;

// import femass.dao.DaoProfessor;
// import femass.model.Professor;
// import javax.swing.*;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class GuiProfessor {
//     private JPanel jPainel;
//     private JButton btnCadastrar;
//     private JButton btnVoltar;
//     private JTextField txtCodigo;
//     private JTextField txtNome;
//     private JTextField txtEndereco;
//     private JTextField txtTelefone;
//     private JTextField txtDevolucao;
//     private JTextField txtDisciplina;
//     private JList lstLeitores;
//     private JButton btnPrincipal;
//     private JFrame telaFechar;
//     private String retornoTelaLeitor;

//     public GuiProfessor() {
//         btnCadastrar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     DaoProfessor daoProfessor = new DaoProfessor();
//                     daoProfessor.gravar(new Professor(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText(), txtDisciplina.getText()));
//                     atualizarLista();
//                 } catch (Exception ex) {
//                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         });
//         btnVoltar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiLeitor telaLeitor = new GuiLeitor();
//                 telaFechar.dispose();
//                 telaLeitor.abrirTela(retornoTelaLeitor);
//             }
//         });
//         lstLeitores.addListSelectionListener(new ListSelectionListener() {
//             @Override
//             public void valueChanged(ListSelectionEvent e) {
//                 Professor professor = (Professor) lstLeitores.getSelectedValue();
//                 if (professor==null) return;
//                 txtCodigo.setText(professor.getCodigo().toString());
//                 txtNome.setText(professor.getNome());
//                 txtEndereco.setText(professor.getEndereco());
//                 txtTelefone.setText(professor.getTelefone());
//                 txtDevolucao.setText(professor.getPrazoMaximoDevolucao().toString());
//                 txtDisciplina.setText(professor.getDisciplina());
//             }
//         });
//         btnPrincipal.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiPrincipal telaPrincipal = new GuiPrincipal();
//                 telaFechar.dispose();
//                 telaPrincipal.abrirTela();
//             }
//         });
//     }

//     public void abrirTela(String telaLeitor){
//         JFrame tela = new JFrame();
//         telaFechar = tela;
//         retornoTelaLeitor = telaLeitor;
//         tela.setTitle("Cadastro de Leitores: Professor");
//         tela.setContentPane(jPainel);
//         tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         try{
//             atualizarLista();
//         }
//         catch (Exception ex){
//             JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//         }

//         tela.pack();
//         tela.setVisible(true);
//     }

//     private void atualizarLista() throws Exception{
//         DaoProfessor daoProfessor = new DaoProfessor();
//         lstLeitores.setListData(daoProfessor.consultar().toArray());
//     }

// }
