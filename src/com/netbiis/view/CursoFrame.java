package com.netbiis.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.netbiis.controller.CursoController;
import com.netbiis.model.curso.Curso;

public class CursoFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel labelNome,labelPreco, labelUrl;
	private JTextField textoNome, textoPreco, textoUrl;
	private JButton botaoSalvarCurso, botaoEditarCurso, botaoLimparCurso, botarApagarCurso;
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private CursoController cursoController;

	public CursoFrame() {
		super("Curso");
		Container container = getContentPane();
		setLayout(null);

		this.cursoController = new CursoController();

		labelNome = new JLabel("Nome do Curso");
		labelPreco = new JLabel("Valor do Curso");
		labelUrl = new JLabel("URL do Curso");

		labelNome.setBounds(10, 10, 240, 15);
		labelPreco.setBounds(10, 50, 240, 15);
		labelUrl.setBounds(10, 90, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelPreco.setForeground(Color.BLACK);
		labelUrl.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelPreco);
		container.add(labelUrl);

		textoNome = new JTextField();
		textoPreco = new JTextField();
		textoUrl = new JTextField();

		textoNome.setBounds(10, 25, 265, 20);
		textoPreco.setBounds(10, 65, 265, 20);
		textoUrl.setBounds(10,105,265,20);

		container.add(textoNome);
		container.add(textoPreco);
		container.add(textoUrl);

		botaoSalvarCurso = new JButton("Salvar");
		botaoLimparCurso = new JButton("Limpar");

		botaoSalvarCurso.setBounds(10, 145, 120, 20);
		botaoLimparCurso.setBounds(150, 145, 120, 20);

		container.add(botaoSalvarCurso);
		container.add(botaoLimparCurso);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador do Curso");
		modelo.addColumn("Nome do Curso");
		modelo.addColumn("Preço do Curso");
		modelo.addColumn("Url do Curso");


		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botarApagarCurso = new JButton("Excluir");
		botaoEditarCurso = new JButton("Alterar");

		botarApagarCurso.setBounds(10, 500, 120, 20);
		botaoEditarCurso.setBounds(150, 500, 120, 20);

		container.add(botarApagarCurso);
		container.add(botaoEditarCurso);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		botaoSalvarCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimparCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCurso();
			}
		});

		botarApagarCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditarCurso.addActionListener(new ActionListener() {
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
			Double preco = (Double) modelo.getValueAt(tabela.getSelectedRow(), 2);
			String url = (String) modelo.getValueAt(tabela.getSelectedRow(), 3);
			
			Curso curso = new Curso(id,nome,preco,url);
					
			this.cursoController.alterar(curso);
			
			JOptionPane.showMessageDialog(this, "Curso alterado com sucesso");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		
		if (objetoDaLinha instanceof Long) {
			
			Long idCurso = (Long) objetoDaLinha;
			
			this.cursoController.deletar(idCurso);
			
			modelo.removeRow(tabela.getSelectedRow());
			
			JOptionPane.showMessageDialog(this, "Curso excluído com sucesso!");
			
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void preencherTabela() {
		List<Curso> cursos =  listarCurso();
		try {
			for (Curso curso : cursos) {
				modelo.addRow(new Object[] { curso.getId(), curso.getNome(), curso.getPreco(), curso.getUrl() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void salvar() {
		if (!textoNome.getText().equals("") && !textoPreco.getText().equals("") && !textoUrl.getText().equals("")) {
			
			Curso curso = new Curso(textoNome.getText(), Double.parseDouble(textoPreco.getText()), textoUrl.getText());
			
			this.cursoController.salvar(curso);
			
			JOptionPane.showMessageDialog(this, "Curso Salvo com sucesso!");
			
			this.limparCurso();
		} else {
			JOptionPane.showMessageDialog(this, "Nome e Email devem ser informados.");
		}
	}

	private List<Curso> listarCurso() {
		return this.cursoController.listar();
	}

	private void limparCurso() {
		this.textoNome.setText("");
		this.textoPreco.setText("");
		this.textoUrl.setText("");
	}

}
