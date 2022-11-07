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

public class AtualizarContato extends JFrame {

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
	private JLabel lbId;
	private JTextField txNome;
	private JTextField txId;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JTextField txRua;
	private JTextField txNumero;
	private JTextField txValor;
	private JTextField txDesconto;
	private JTextField txValortotal;
	private JLabel lbValortotal;
	Contato contato;
	private int linhaSelecionada;

	public AtualizarContato(DefaultTableModel md, int id, int linha) {
		super("Contatos");
		criaJanela();
		modelo = md;
		ContatoDao dao = new ContatoDao();
		contato = dao.getContatoById(id);
		txId.setText(Integer.toString(contato.getId()));
		txNome.setText(contato.getNome());
		txTelefone.setText(contato.getTelefone());
		txEmail.setText(contato.getEmail());
		txRua.setText(contato.getRua());
		txNumero.setText(Integer.toString(contato.getNumero()));
		txValor.setText(Double.toString(contato.getValor()));
		txDesconto.setText(Double.toString(contato.getDesconto()));
		txValortotal.setText(Double.toString(contato.getValortotal()));
		linhaSelecionada = linha;
	}

	public void criaJanela() {
		btSalvar = new JButton("Salvar");
		btLimpar = new JButton("Limpar");
		lbId = new JLabel("         Id.:");
		lbNome = new JLabel("         Nome.:");
		lbTelefone = new JLabel("         Telefone.:");
		lbEmail = new JLabel("         Email.:");
		lbRua = new JLabel("         Rua.:");
		lbNumero = new JLabel("         Numero.:");
		lbValor = new JLabel("         Valor do Produto.:");
		lbDesconto = new JLabel("         Desconto.:");
		lbValortotal = new JLabel("         Valor Total.:");
		txNome = new JTextField();
		txTelefone = new JTextField();
		txEmail = new JTextField();
		txRua = new JTextField();
		txNumero = new JTextField();
		txValor = new JTextField();
		txDesconto = new JTextField();
		txValortotal = new JTextField();
		txValortotal.setEditable(false);
		txId = new JTextField();
		txId.setEditable(false);

		painelFundo = new JPanel();
		painelFundo.setLayout(new GridLayout(10, 2, 1, 1));
		painelFundo.add(lbId);
		painelFundo.add(txId);
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
		painelFundo.add(lbValortotal);
		painelFundo.add(txValortotal);
		painelFundo.add(btLimpar);
		painelFundo.add(btSalvar);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550, 350);
		setVisible(true);

		btSalvar.addActionListener(new AtualizarContato.BtSalvarListener());
		btLimpar.addActionListener(new AtualizarContato.BtLimparListener());
	}

	private class BtSalvarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Contato c = new Contato();
			c.setId(Integer.parseInt(txId.getText()));
			c.setNome(txNome.getText());
			c.setTelefone(txTelefone.getText());
			c.setEmail(txEmail.getText());
			c.setRua(txRua.getText());
			c.setNumero(Integer.parseInt(txNumero.getText()));
			c.setValor(Double.parseDouble(txValor.getText()));
			c.setDesconto(Double.parseDouble(txDesconto.getText()));
			c.setValortotal(Double.parseDouble(txValortotal.getText()));

			ContatoDao dao = new ContatoDao();
			dao.atualizar(c);
			modelo.removeRow(linhaSelecionada);
			modelo.addRow(new Object[]{c.getId(),c.getNome(), c.getTelefone(), c.getEmail(),
					c.getRua(), c.getNumero(), c.getValor(), c.getDesconto(), c.getValortotal()});
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