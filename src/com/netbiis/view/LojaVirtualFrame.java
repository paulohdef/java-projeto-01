package com.netbiis.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LojaVirtualFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton botaoCliente, botaoCurso, botaoPagamento;

	public LojaVirtualFrame() {
		super("Loja Virtual");
		
		Container container = getContentPane();
		
		botaoCliente = new JButton("Clientes");
		botaoCliente.setBounds(120, 50,100, 20);
		container.add(botaoCliente);
		
		botaoCurso = new JButton("Cursos");
		botaoCurso.setBounds(120, 100,100, 20);
		container.add(botaoCurso);
		
		botaoPagamento = new JButton("Pagamentos");
		botaoPagamento.setBounds(90, 150,150, 20);
		container.add(botaoPagamento);
		
		
		setSize(350, 250);
		setLayout(null);
		setVisible(true);
		
		botaoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClienteFrame clienteFrame = new ClienteFrame();
			}
		});
		
		botaoCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CursoFrame cursoFrame = new CursoFrame();
			}
		});
		
		botaoPagamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PagamentoFrame pagamentoFrame = new PagamentoFrame();
			}
		});

	}
}
