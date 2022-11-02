package com.netbiis.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.netbiis.controller.ClienteController;
import com.netbiis.controller.CursoController;
import com.netbiis.controller.PagamentoController;
import com.netbiis.model.cliente.Cliente;

public class ClienteFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelEmail;
	private JTextField textoNome, textoEmail;
	private JButton botaoSalvarCliente, botaoEditarCliente, botaoLimparCliente, botarApagarCliente;
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private ClienteController clienteController;

	public ClienteFrame() {
		super("Clientes");
		Container container = getContentPane();
		setLayout(null);

		this.clienteController = new ClienteController();

		labelNome = new JLabel("Nome do Cliente");
		labelEmail = new JLabel("Email do Cliente");

		labelNome.setBounds(10, 10, 240, 15);
		labelEmail.setBounds(10, 50, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelEmail.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelEmail);


		textoNome = new JTextField();
		textoEmail = new JTextField();

		textoNome.setBounds(10, 25, 265, 20);
		textoEmail.setBounds(10, 65, 265, 20);

		container.add(textoNome);
		container.add(textoEmail);

		botaoSalvarCliente = new JButton("Salvar");
		botaoLimparCliente = new JButton("Limpar");

		botaoSalvarCliente.setBounds(10, 145, 120, 20);
		botaoLimparCliente.setBounds(150, 145, 120, 20);

		container.add(botaoSalvarCliente);
		container.add(botaoLimparCliente);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador do Cliente");
		modelo.addColumn("Nome do Cliente");
		modelo.addColumn("Email do Cliente");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botarApagarCliente = new JButton("Excluir");
		botaoEditarCliente = new JButton("Alterar");

		botarApagarCliente.setBounds(10, 500, 120, 20);
		botaoEditarCliente.setBounds(150, 500, 120, 20);

		container.add(botarApagarCliente);
		container.add(botaoEditarCliente);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		botaoSalvarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarCliente();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimparCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCliente();
			}
		});

		botarApagarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				preencherTabela();
			}
		});
	}

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	private void alterar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		
		if (objetoDaLinha instanceof Long) {
			
			Long id = (Long) objetoDaLinha;
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String email = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			
			Cliente cliente = new Cliente(id,nome, email);
			
			this.clienteController.alterar(cliente);
			
			JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		
		if (objetoDaLinha instanceof Long) {
			
			Long idCliente = (Long) objetoDaLinha;
			
			this.clienteController.deletar(idCliente);
			
			modelo.removeRow(tabela.getSelectedRow());
			
			JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void preencherTabela() {
		List<Cliente> clientes =  listarClientes();
		try {
			for (Cliente cliente : clientes) {
				modelo.addRow(new Object[] { cliente.getId(), cliente.getNome(), cliente.getEmail() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void salvarCliente() {
		if (!textoNome.getText().equals("") && !textoEmail.getText().equals("")) {
			Cliente cliente = new Cliente(textoNome.getText(), textoEmail.getText());
			this.clienteController.salvar(cliente);
			JOptionPane.showMessageDialog(this, "Cliente Salvo com sucesso!");
			this.limparCliente();
		} else {
			JOptionPane.showMessageDialog(this, "Nome e Email devem ser informados.");
		}
	}

	private List<Cliente> listarClientes() {
		return this.clienteController.listar();
	}

	private void limparCliente() {
		this.textoNome.setText("");
		this.textoEmail.setText("");
	}
}
