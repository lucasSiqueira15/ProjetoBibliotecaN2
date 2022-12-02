// package femass.gui;

// import femass.dao.DaoExemplar;
// import femass.dao.DaoLivro;
// import femass.model.Exemplar;
// import femass.model.Livro;
// import javax.swing.*;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
// import javax.swing.text.MaskFormatter;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.List;

// public class GuiExemplar {
//     private JPanel jPainel;
//     private JButton btnCadastrar;
//     private JButton btnVoltar;
//     private JTextField txtCodigo;
//     private JTextField txtDisponivel;
//     private JFormattedTextField txtAquisicao;
//     private JComboBox cboLivro;
//     private JList lstExemplares;
//     private JFrame telaFechar;

//     public GuiExemplar() {
//         btnCadastrar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     DaoExemplar daoExemplar = new DaoExemplar();
//                     Exemplar exemplar = new Exemplar(
//                             LocalDate.parse(txtAquisicao.getText(),
//                             DateTimeFormatter.ofPattern("dd/MM/yyyy")),
//                             (Livro) cboLivro.getSelectedItem()
//                         );
//                     daoExemplar.gravar(exemplar);
//                     atualizarListaExemplares();
//                 } catch (Exception ex) {
//                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         });
//         btnVoltar.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiLivro guiLivro = new GuiLivro();
//                 telaFechar.dispose();
//                 guiLivro.abrirTela();
//             }
//         });
//         lstExemplares.addListSelectionListener(new ListSelectionListener() {
//             @Override
//             public void valueChanged(ListSelectionEvent e) {
//                 Exemplar exemplar = (Exemplar) lstExemplares.getSelectedValue();
//                 if(exemplar==null) return;
//                 txtCodigo.setText(exemplar.getCodigo().toString());
//                 txtAquisicao.setText(exemplar.getDataAquisicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                 if(exemplar.getDisponivel() == true){
//                     txtDisponivel.setText("Disponível");
//                 }
//                 else{
//                     txtDisponivel.setText("Indisponível");
//                 }
//                 cboLivro.setSelectedItem(exemplar.getLivro());
//             }
//         });
//     }

//     public void abrirTela(){
//         JFrame tela = new JFrame();
//         telaFechar = tela;
//         tela.setTitle("Cadastro de Exemplares");
//         tela.setContentPane(jPainel);
//         tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         try{
//             atualizarListaExemplares();
//             atualizarComboLivros();
//             inserirMascaraData();
//         }
//         catch (Exception ex){
//             JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//         }

//         tela.pack();
//         tela.setVisible(true);
//     }

//     private void atualizarListaExemplares() throws Exception{
//         DaoExemplar daoExemplar = new DaoExemplar();
//         lstExemplares.setListData(daoExemplar.consultar().toArray());
//     }

//     private void atualizarComboLivros() throws Exception{
//         cboLivro.removeAllItems();
//         List<Livro> livros = new DaoLivro().consultar();
//         for(Livro livro : livros){
//             cboLivro.addItem(livro);
//         }
//         cboLivro.setSelectedIndex(-1);
//     }

//     private void inserirMascaraData() throws Exception{
//         MaskFormatter mascaraData = new MaskFormatter("##/##/####");
//         mascaraData.install(txtAquisicao);
//     }
// }
