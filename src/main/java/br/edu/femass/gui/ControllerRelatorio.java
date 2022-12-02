// package femass.gui;

// import femass.dao.DaoEmprestimo;
// import femass.model.Emprestimo;
// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.time.LocalDate;
// import java.util.List;

// public class GuiRelatorio {
//     private JPanel jPainel;
//     private JButton btnExtrair;
//     private JList lstRelatorio;
//     private JButton btnVoltar;
//     private JButton btnLimpar;
//     private DefaultListModel<Emprestimo> lstModelRelatorio = new DefaultListModel<Emprestimo>();
//     private JFrame telaFechar;

//     public GuiRelatorio() {
//         btnVoltar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiPrincipal telaPrincipal = new GuiPrincipal();
//                 telaFechar.dispose();
//                 telaPrincipal.abrirTela();
//             }
//         });
//         btnExtrair.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     atualizarListaRelatorio();
//                     if(lstModelRelatorio.isEmpty()){
//                         JOptionPane.showMessageDialog(null, "Sem Pendências de Empréstimo.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//                     }
//                 } catch (Exception ex) {
//                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         });
//         btnLimpar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 lstModelRelatorio.clear();
//             }
//         });
//     }

//     public void abrirTela(){
//         JFrame tela = new JFrame();
//         telaFechar = tela;
//         tela.setTitle("Devolução de Exemplares");
//         tela.setContentPane(jPainel);
//         tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         lstRelatorio.setModel(lstModelRelatorio);
//         tela.pack();
//         tela.setVisible(true);
//     }

//     private void atualizarListaRelatorio() throws Exception{
//         lstModelRelatorio.clear();
//         DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
//         List<Emprestimo> emprestimos = daoEmprestimo.consultar();
//         for(Emprestimo emprestimo : emprestimos){
//             if(emprestimo.getDataDevolucao() == null & LocalDate.now().isAfter(emprestimo.getDataPrevistaDevolucao())){
//                 lstModelRelatorio.addElement(emprestimo);
//             }
//         }
//     }

// }
