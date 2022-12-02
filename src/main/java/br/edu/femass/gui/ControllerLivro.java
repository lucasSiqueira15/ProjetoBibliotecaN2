// package femass.gui;

// import femass.dao.DaoAutor;
// import femass.dao.DaoLivro;
// import femass.model.Autor;
// import femass.model.Livro;
// import javax.swing.*;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.List;

// public class GuiLivro {
//     private JPanel jPainel;
//     private JButton btnCadLivros;
//     private JButton btnVoltar;
//     private JTextField txtCodigo;
//     private JTextField txtTitulo;
//     private JComboBox cboAutores;
//     private JList lstAutores;
//     private JList lstLivros;
//     private JButton btnInserir;
//     private JButton btnNovoAutor;
//     private JButton btnLimparDados;
//     private JTextField txtAno;
//     private JButton btnCadExemplares;
//     private JFrame telaFechar;
//     private DefaultListModel<Autor> lstModelAutores = new DefaultListModel<Autor>();

//     public GuiLivro() {
//         btnCadLivros.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     DaoLivro daoLivro = new DaoLivro();
//                     List<Autor> autores = new ArrayList<Autor>();
//                     for(int i = 0; i < lstModelAutores.size(); i++){
//                         autores.add(lstModelAutores.get(i));
//                     }
//                     daoLivro.gravar(new Livro(txtTitulo.getText(), txtAno.getText(), autores));
//                     atualizarListaLivros();
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
//         btnInserir.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 Autor autor = (Autor) cboAutores.getSelectedItem();
//                 lstModelAutores.addElement(autor);
//             }
//         });
//         btnNovoAutor.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiAutor telaAutor = new GuiAutor();
//                 telaFechar.dispose();
//                 telaAutor.abrirTela(new GuiLivro().toString());
//             }
//         });
//         lstLivros.addListSelectionListener(new ListSelectionListener() {
//             @Override
//             public void valueChanged(ListSelectionEvent e) {
//                 Livro livro = (Livro) lstLivros.getSelectedValue();
//                 if(livro==null) return;
//                 txtCodigo.setText(livro.getCodigo().toString());
//                 txtAno.setText(livro.getAno());
//                 txtTitulo.setText(livro.getTitulo());
//                 lstModelAutores.clear();
//                 lstModelAutores.addAll(livro.getAutores());
//             }
//         });
//         btnLimparDados.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 limparDados();
//             }
//         });
//         btnCadExemplares.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 GuiExemplar guiExemplar = new GuiExemplar();
//                 telaFechar.dispose();
//                 guiExemplar.abrirTela();
//             }
//         });
//     }

//     public void abrirTela(){
//         JFrame tela = new JFrame();
//         telaFechar = tela;
//         tela.setTitle("Cadastro de Livros");
//         tela.setContentPane(jPainel);
//         tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         lstAutores.setModel(lstModelAutores);

//         try{
//             atualizarListaLivros();
//             atualizarComboAutores();
//         }
//         catch (Exception ex){
//             JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
//         }

//         tela.pack();
//         tela.setVisible(true);
//     }

//     private void atualizarListaLivros() throws Exception{
//         DaoLivro daoLivro = new DaoLivro();
//         lstLivros.setListData(daoLivro.consultar().toArray());
//     }

//     private void atualizarComboAutores() throws Exception{
//         cboAutores.removeAllItems();
//         List<Autor> autores = new DaoAutor().consultar();
//         for(Autor autor : autores){
//             cboAutores.addItem(autor);
//         }
//         cboAutores.setSelectedIndex(-1);
//     }

//     private void limparDados(){
//         txtCodigo.setText("");
//         txtTitulo.setText("");
//         txtAno.setText("");
//         cboAutores.setSelectedIndex(-1);
//         lstModelAutores.clear();
//     }

//     @Override
//     public String toString() {
//         return "Livro";
//     }
// }
