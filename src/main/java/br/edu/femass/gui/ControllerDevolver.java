// package femass.gui;

// import femass.dao.DaoEmprestimo;
// import femass.dao.DaoExemplar;
// import femass.model.Emprestimo;

// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.List;

// public class GuiDevolver {
//     private JPanel jPainel;
//     private JList lstEmprestados;
//     private JList lstDevolvidos;
//     private JButton btnDevolver;
//     private JButton btnVoltar;
//     private JFrame telaFechar;
//     private DefaultListModel<Emprestimo> lstModelEmprestados = new DefaultListModel<Emprestimo>();
//     private DefaultListModel<Emprestimo> lstModelDevolvidos = new DefaultListModel<Emprestimo>();

//     public GuiDevolver() {
//         btnDevolver.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
//                 try {
//                     Emprestimo emprestimo = (Emprestimo) lstEmprestados.getSelectedValue();
//                     emprestimo.devolverEmprestimo();

//                     daoEmprestimo.atualizarEmprestimo(emprestimo);

//                     DaoExemplar daoExemplar = new DaoExemplar();
//                     daoExemplar.atualizarExemplar(emprestimo.getExemplar());

//                     atualizarListaDevolvidos();
//                     atualizarListaEmprestados();
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
//     }

//     public void abrirTela(){
//         JFrame tela = new JFrame();
//         telaFechar = tela;
//         tela.setTitle("Devolução de Exemplares");
//         tela.setContentPane(jPainel);
//         tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         lstDevolvidos.setModel(lstModelDevolvidos);
//         lstEmprestados.setModel(lstModelEmprestados);

//         try{
//             atualizarListaDevolvidos();
//             atualizarListaEmprestados();
//         }
//         catch (Exception ex){
//             JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//         }

//         tela.pack();
//         tela.setVisible(true);
//     }

//     private void atualizarListaDevolvidos() throws Exception{
//         lstModelDevolvidos.clear();
//         DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
//         List<Emprestimo> emprestimos = daoEmprestimo.consultar();
//         for(Emprestimo emprestimo : emprestimos){
//             if(emprestimo.getDataDevolucao() != null){
//                 lstModelDevolvidos.addElement(emprestimo);
//             }
//         }
//     }

//     private void atualizarListaEmprestados() throws Exception{
//         lstModelEmprestados.clear();
//         DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
//         List<Emprestimo> emprestimos = daoEmprestimo.consultar();
//         for(Emprestimo emprestimo : emprestimos){
//             if(emprestimo.getDataDevolucao() == null){
//                 lstModelEmprestados.addElement(emprestimo);
//             }
//         }
//     }
// }
