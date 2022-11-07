package com.project.negocio.contato;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.project.negocio.view.ListarContato;

public class InserirContato extends JFrame {

	private DefaultTableModel modelo = new DefaultTableModel();
	private JPanel painelFundo;
	private JButton btSalvar;
	private JButton btLimpar;
	private JLabel lbNome;
	private JLabel lbTelefone;
	private JLabel lbEmail;
	private JLabel lbRua;
	private JLabel lbNumero;
	private JLabel lbValor;
	private JLabel lbDesconto;
	
	private JTextField txNome;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JTextField txRua;
	private JTextField txNumero;
	private JTextField txValor;
	private JTextField txDesconto;

	public InserirContato(DefaultTableModel md) {
		super("Contatos");
		criaJanela();
		modelo = md;
	}

	public void criaJanela() {
		btSalvar = new JButton("Salvar");
		btLimpar = new JButton("Limpar");
		lbNome = new JLabel("         Nome.:");
		lbTelefone = new JLabel("         Telefone.:");
		lbEmail = new JLabel("         Email.:   ");
		lbRua = new JLabel("         Rua.:");
		lbNumero = new JLabel("         N°.:");
		lbValor = new JLabel("         Valor do Produto.:");
		lbDesconto = new JLabel("         Desconto.:");
		txNome = new JTextField(10);
		txTelefone = new JTextField();
		txEmail = new JTextField();
		txRua = new JTextField();
		txNumero = new JTextField();
		txValor = new JTextField();
		txDesconto = new JTextField();

		painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(8, 8, 8, 8));
		painelFundo.add(lbNome);
		painelFundo.add(txNome);
		painelFundo.add(lbTelefone);
		painelFundo.add(txTelefone);
		painelFundo.add(lbEmail);
		painelFundo.add(txEmail);
		painelFundo.add(lbRua);
		painelFundo.add(txRua);
		painelFundo.add(lbNumero);
		painelFundo.add(txNumero);
		painelFundo.add(lbValor);
		painelFundo.add(txValor);
		painelFundo.add(lbDesconto);
		painelFundo.add(txDesconto);
		painelFundo.add(btLimpar);
		painelFundo.add(btSalvar);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550, 350);
		setVisible(true);
		btSalvar.addActionListener(new BtSalvarListener());
		btLimpar.addActionListener(new BtLimparListener());
	}

	private class BtSalvarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Contato c = new Contato();
			c.setNome(txNome.getText());
			c.setTelefone(txTelefone.getText());
			c.setEmail(txEmail.getText());
			c.setRua(txRua.getText());
			c.setNumero(Integer.parseInt(txNumero.getText()));
			c.setValor(Double.parseDouble(txValor.getText()));
			c.setDesconto(Double.parseDouble(txDesconto.getText()));

			ContatoDao dao = new ContatoDao();
			dao.inserir(c);
			ListarContato.pesquisar(modelo);

			setVisible(false);
		}
	}

	private class BtLimparListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			txNome.setText("");
			txTelefone.setText("");
			txEmail.setText("");
			txRua.setText("");
			txNumero.setText("");
			txValor.setText("");
			txDesconto.setText("");
		}
	}
}
