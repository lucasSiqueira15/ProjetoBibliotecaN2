// package femass.gui;

// import femass.dao.DaoAluno;
// import femass.model.Aluno;
// import javax.swing.*;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class GuiAluno {
//     private JPanel jPainel;
//     private JButton btnCadastrar;
//     private JButton btnVoltar;
//     private JTextField txtCodigo;
//     private JTextField txtNome;
//     private JTextField txtEndereco;
//     private JTextField txtTelefone;
//     private JTextField txtDevolucao;
//     private JTextField txtMatricula;
//     private JList lstLeitores;
//     private JButton btnPrincipal;
//     private JFrame telaFechar;
//     private String retornoTelaLeitor;

//     public GuiAluno() {
//         btnCadastrar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     DaoAluno daoAluno = new DaoAluno();
//                     daoAluno.gravar(new Aluno(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText(), txtMatricula.getText()));
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
//                 Aluno aluno = (Aluno) lstLeitores.getSelectedValue();
//                 if (aluno==null) return;
//                 txtCodigo.setText(aluno.getCodigo().toString());
//                 txtNome.setText(aluno.getNome());
//                 txtEndereco.setText(aluno.getEndereco());
//                 txtTelefone.setText(aluno.getTelefone());
//                 txtDevolucao.setText(aluno.getPrazoMaximoDevolucao().toString());
//                 txtMatricula.setText(aluno.getMatricula());
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
//         tela.setTitle("Cadastro de Leitores: Aluno");
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
//         DaoAluno daoAluno = new DaoAluno();
//         lstLeitores.setListData(daoAluno.consultar().toArray());
//     }

// }
