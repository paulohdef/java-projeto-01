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
import javax.swing.table.DefaultTableModel;

import com.netbiis.controller.ClienteController;
import com.netbiis.controller.CursoController;
import com.netbiis.controller.PagamentoController;
import com.netbiis.model.cliente.Cliente;
import com.netbiis.model.curso.Curso;
import com.netbiis.model.pagamento.Pagamento;

public class PagamentoFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel labelCliente, labelCurso;
	private JComboBox<Cliente> comboCliente;
	private JComboBox<Curso> comboCurso;

	private JButton botaoSalvarPagamento, botaoEditarPagamento, botarApagarPagamento;
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private ClienteController clienteController;
	private CursoController cursoController;
	private PagamentoController pagamentoController;

	public PagamentoFrame() {
		super("Clientes");
		Container container = getContentPane();
		setLayout(null);

		this.clienteController = new ClienteController();
		this.cursoController = new CursoController();
		this.pagamentoController = new PagamentoController();

		labelCliente = new JLabel("Selecione o cliente");
		labelCurso = new JLabel("Selecione o curso");

		labelCliente.setBounds(10, 10, 240, 15);
		labelCurso.setBounds(10, 50, 240, 15);

		labelCliente.setForeground(Color.BLACK);
		labelCurso.setForeground(Color.BLACK);

		container.add(labelCliente);
		container.add(labelCurso);


		comboCliente = new JComboBox<Cliente>();
		comboCurso = new JComboBox<Curso>();
		
		List<Cliente> clientes = this.listarClientes();
		
		for (Cliente cliente : clientes) {
			comboCliente.addItem(cliente);
		}
		
		List<Curso> cursos = this.listarCursos();
		
		for (Curso curso : cursos) {
			comboCurso.addItem(curso);
		}
		
		comboCliente.setBounds(10, 25, 265, 20);
		comboCurso.setBounds(10, 65, 265, 20);

		container.add(comboCliente);
		container.add(comboCurso);

		botaoSalvarPagamento = new JButton("Salvar");

		botaoSalvarPagamento.setBounds(10, 145, 120, 20);
		
		container.add(botaoSalvarPagamento);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador do Pagamento");
		modelo.addColumn("Nome do Cliente");
		modelo.addColumn("Nome do Curso");
		modelo.addColumn("Valor do pagamento");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botarApagarPagamento = new JButton("Excluir");
//		botaoEditarCliente = new JButton("Alterar");
//
		botarApagarPagamento.setBounds(10, 500, 120, 20);
//		botaoEditarCliente.setBounds(150, 500, 120, 20);
//
		container.add(botarApagarPagamento);
//		container.add(botaoEditarCliente);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		botaoSalvarPagamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarPagamento();
				limparTabela();
				preencherTabela();
			}
		});

		botarApagarPagamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletarPagamento();
				limparTabela();
				preencherTabela();
			}
		});
//
//		botaoEditarCliente.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				alterar();
//				limparTabela();
////				preencherTabela();
//			}
//		});
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

	private void deletarPagamento() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		
		if (objetoDaLinha instanceof Long) {
			
			Long idPagamento = (Long) objetoDaLinha;
			
			this.pagamentoController.deletar(idPagamento);
			
			modelo.removeRow(tabela.getSelectedRow());
			
			JOptionPane.showMessageDialog(this, "Pagamento excluído com sucesso!");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void preencherTabela() {
		List<Pagamento> pagamentos =  listarPagamentos();
		try {
			for (Pagamento pagamento : pagamentos) {
				modelo.addRow(new Object[] { pagamento.getId(), pagamento.getCliente().getNome(), pagamento.getCurso().getNome(), pagamento.getCurso().getPreco() });
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void salvarPagamento() {
	
		Curso curso = (Curso) comboCurso.getSelectedItem();
		
		Cliente cliente = (Cliente) comboCliente.getSelectedItem();
		
		Pagamento pagamento = new Pagamento(cliente, curso);

		this.pagamentoController.salvar(pagamento);
		
		JOptionPane.showMessageDialog(this, "Curso Salvo com sucesso!");
	
	}


	private List<Curso> listarCursos() {
		return this.cursoController.listar();
	}
	
	private List<Cliente> listarClientes() {
		return this.clienteController.listar();
	}
	
	private List<Pagamento> listarPagamentos() {
		return this.pagamentoController.listar();
	}

}
