/**
	FILA DE ESPERA
	ACADÊMICO: GILSIMAR HABNER MONTEIRO
	CURSO: CIÊNCIA DA COMPUTAÇÃO
 **/
package Restaurante;

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyEvent;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.RowFilter;
	import javax.swing.UIManager;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.table.TableRowSorter;
	
	public class Fila_de_Espera {

		static JFrame Tela_Principal = new JFrame("Lista de Espera");
		static JFrame Tela_Cadastro = new JFrame("Cadastro");

		static JButton Botao_Cadastrar = new JButton("Cadastrar Na Lista");
		static JButton Botao_Salvar = new JButton("Salvar");
		static JButton Botao_Remover = new JButton("Remover Da Lista");
		static JButton Botao_Sobre = new JButton("Sobre");
		static JButton Botao_Voltar_Tela_Principal = new JButton("Voltar ao Menu");
		static JButton Botao_Sair = new JButton("Sair");
		static JButton Botao_Limpar_Pesquisa = new JButton("Limpar pesquisa");

		static JLabel Label_Tela_Principal = new JLabel("Lista de Espera");
		static JLabel Label_Tela_Cadastro = new JLabel("Cadastro");
		static JLabel Label_Nome = new JLabel("Nome: ");
		static JLabel Label_Aliados = new JLabel("Quantos Aliados ? ");
		static JLabel LAbel_Pesquisa = new JLabel("Pesquisar:");
		

		static JTextField Escrita_Nome = new JTextField();
		static JTextField Escrita_Aliados = new JTextField();
		static JTextField Escrita_Pesquisa = new JTextField();
		
		
		static JTable Tabela_Consultas = new JTable();
		static JPanel Panel_Consulta = new JPanel();
		static JScrollPane Rolagem_Consulta = new JScrollPane(Tabela_Consultas);

		private static final String ARIAL = "Arial";
		private static final String DESOCUPADO = "REMOVIDO";
		private static final String IMAGEM_CADASTRAR = "/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif";
		private static final String IMAGEM_REMOVER = "/javax/swing/plaf/metal/icons/ocean/close.gif";
		private static final String IMAGEM_SALVAR = "/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif";



		static int Fila = 0;
		static int Quantidade_Cliente = 100;
		static int Informacao = 3;
		static String[][] Posicao = new String[Quantidade_Cliente][Informacao];

		public static void main(String[] args) {

			// alterando tema pelo LookAndFeel
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (Exception e) {

			}

			Janela_Principal();
			Janela_Cadastrar();
			Janela_Excluir();
			Comportamento_Limpar_Pesquisa();
			Comportamento_Cadastrar();
			Comportamento_Sobre();
			Comportamento_Sair();

		}

		static void Janela_Principal() {


			Componentes_Janela_Principal();

			Tela_Principal.setLayout(null);
			Tela_Principal.setSize(1180, 540);
			Tela_Principal.getContentPane().setBackground(Color.LIGHT_GRAY);
			Tela_Principal.setResizable(false);
			Tela_Principal.setLocationRelativeTo(null);
			Tela_Principal.setVisible(true);
			Tela_Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Tela_Principal.add(Label_Tela_Principal);
			Label_Tela_Principal.setBounds(500, 1, 400, 50);
			Label_Tela_Principal.setFont(new Font(ARIAL, Font.BOLD, 25));

		}

		@SuppressWarnings("serial")
		static void Componentes_Janela_Principal() {

			Tela_Principal.add(LAbel_Pesquisa);
			LAbel_Pesquisa.setBounds(225, 55, 100, 30);
			LAbel_Pesquisa.setFont(new Font(ARIAL, Font.BOLD, 13));
			LAbel_Pesquisa.setForeground(Color.BLACK);


			Tela_Principal.add(Escrita_Pesquisa);
			Escrita_Pesquisa.setBounds(300, 58, 225, 25);

			Tela_Principal.add(Botao_Cadastrar);
			Botao_Cadastrar.setFont(new Font(ARIAL, Font.BOLD, 13));
			Botao_Cadastrar.setToolTipText("Clique para cadastrar na lista");
			Botao_Cadastrar.setBounds(50, 100, 200, 50);
			Botao_Cadastrar.setBackground(Color.CYAN);
			Botao_Cadastrar.setForeground(Color.BLACK);
			Botao_Cadastrar.setIcon(new ImageIcon(Fila_de_Espera.class.getResource(IMAGEM_CADASTRAR)));

			Tela_Principal.add(Botao_Remover);
			Botao_Remover.setFont(new Font(ARIAL, Font.BOLD, 13));
			Botao_Remover.setToolTipText("Clique para remover da lista de espera");
			Botao_Remover.setBounds(50, 200, 200, 50);
			Botao_Remover.setBackground(Color.CYAN);
			Botao_Remover.setForeground(Color.BLACK);
			Botao_Remover.setIcon(new ImageIcon(Fila_de_Espera.class.getResource(IMAGEM_REMOVER)));

			Tela_Principal.add(Botao_Sobre);
			Botao_Sobre.setBounds(50, 300, 200, 50);
			Botao_Sobre.setToolTipText("Clique para mais informações");
			Botao_Sobre.setBackground(Color.CYAN);
			Botao_Sobre.setForeground(Color.BLACK);
			Botao_Sobre.setFont(new Font(ARIAL, Font.BOLD, 13));
			Botao_Sobre.setIcon(new ImageIcon(Fila_de_Espera.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif")));



			Tela_Principal.add(Botao_Sair);
			Botao_Sair.setBounds(50, 400, 200, 50);
			Botao_Sair.setBackground(Color.CYAN);
			Botao_Sair.setForeground(Color.BLACK);
			
			Tabela_Consultas.setLayout(null);
			Tabela_Consultas.revalidate();
			Tabela_Consultas.setBounds(0, 0, 800, 500);
			Tabela_Consultas.setModel(new DefaultTableModel(
					new Object[] { "Fila", "Nome", "Aliados"},0) {
				// bloqueando a edição das linhas
				@Override
				public boolean isCellEditable(int row, int col) {
					return false;
				}

			});

			Rolagem_Consulta.add(Tabela_Consultas);
			Rolagem_Consulta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			Rolagem_Consulta.setBounds(0, 0, 800, 360);
			Rolagem_Consulta.setViewportView(Tabela_Consultas);

			Tela_Principal.add(Panel_Consulta);
			Panel_Consulta.add(Rolagem_Consulta);
			Panel_Consulta.setLayout(null);
			Panel_Consulta.setBounds(300, 100, 800, 360);
			

			for (int i = 0; i < Fila; i++) {
				DefaultTableModel valores = (DefaultTableModel) Tabela_Consultas.getModel();
				valores.addRow(new Object[] { i + 1, Posicao[i][0], Posicao[i][1], Posicao[i][2], });
				acaoPesquisar(Tabela_Consultas);
			}
			
			Tabela_Consultas.getColumnModel().getColumn(0).setPreferredWidth(10);
			Tabela_Consultas.getColumnModel().getColumn(1).setPreferredWidth(400);
			Tabela_Consultas.getColumnModel().getColumn(2).setPreferredWidth(80);
			

		}

		static void Comportamento_Limpar_Pesquisa() {
			
			Tela_Principal.add(Botao_Limpar_Pesquisa);
			Botao_Limpar_Pesquisa.setBounds(535, 58, 130, 25);
			Botao_Limpar_Pesquisa.setBackground(Color.CYAN);
			Botao_Limpar_Pesquisa.setForeground(Color.BLACK);


			
			Botao_Limpar_Pesquisa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					limparPesquisa();
					
				}
			});
			
		}
		static void Janela_Cadastrar() {

			Botao_Cadastrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					limparPesquisa();

					Escrita_Nome.setText(null);
					Escrita_Aliados.setText(null);

					Parte_Cadastar();

					Tela_Cadastro.add(Botao_Voltar_Tela_Principal);
					Comportamento_Voltar_Janela_Principal();
					Botao_Voltar_Tela_Principal.setBounds(440, 250, 200, 50);
					Botao_Voltar_Tela_Principal.setBackground(Color.CYAN);
					Botao_Voltar_Tela_Principal.setForeground(Color.BLACK);



					Tela_Cadastro.setLayout(null);
					Tela_Cadastro.setSize(720, 380);
					Tela_Cadastro.getContentPane().setBackground(Color.LIGHT_GRAY);
					Tela_Cadastro.setResizable(false);
					Tela_Cadastro.setLocationRelativeTo(null);
					Tela_Cadastro.setVisible(true);
					Tela_Cadastro.add(Label_Tela_Cadastro);

					Label_Tela_Cadastro.setBounds(290, 1, 150, 50);
					Label_Tela_Cadastro.setFont(new Font(ARIAL, Font.BOLD, 30));

				}
			});

		}

		static void Parte_Cadastar() {

			Tela_Cadastro.add(Label_Nome);
			Label_Nome.setBounds(50, 50, 50, 50);
			Label_Nome.setFont(new Font(ARIAL, Font.BOLD, 15));

			Tela_Cadastro.add(Escrita_Nome);
			Escrita_Nome.setBounds(100, 60, 560, 30);

			Tela_Cadastro.add(Label_Aliados);
			Label_Aliados.setBounds(50, 150, 200, 50);
			Label_Aliados.setFont(new Font(ARIAL, Font.BOLD, 15));

			Tela_Cadastro.add(Escrita_Aliados);
			Escrita_Aliados.setBounds(180,160, 300, 30);

			Tela_Cadastro.add(Botao_Salvar);
			Botao_Salvar.setToolTipText("Clique para salvar o cadastro");
			Botao_Salvar.setBounds(80, 250, 200, 50);
			Botao_Salvar.setBackground(Color.CYAN);
			Botao_Salvar.setForeground(Color.BLACK);
			Botao_Salvar.setIcon(new ImageIcon(Fila_de_Espera.class.getResource(IMAGEM_SALVAR)));



			

		}

		static void Comportamento_Cadastrar() {

			Botao_Salvar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					// validção de todos campos preenchidos
					if (Escrita_Nome.getText().trim().isEmpty() 
							|| Escrita_Aliados.getText().trim().isEmpty()) {

						JOptionPane.showMessageDialog(null, "Preencha os dois campos!");
						Parte_Cadastar();

					} else {

						Posicao[Fila][0] = Escrita_Nome.getText().toUpperCase();
						Posicao[Fila][1] = Escrita_Aliados.getText().toUpperCase();

						DefaultTableModel valores = (DefaultTableModel) Tabela_Consultas.getModel();
						valores.addRow(new Object[] { (Fila + 1), Posicao[Fila][0], Posicao[Fila][1],
								Posicao[Fila][2]});

						// validação do codigo chegar somente no 100
						if (Fila < 100) {

							Fila++;

							int desejo = JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais?");
							// validando se sim ou se nao do JOption
							if (desejo == JOptionPane.YES_OPTION) {

								Parte_Cadastar();
								Escrita_Nome.setText(null);
								Escrita_Aliados.setText(null);

							} else {

								Escrita_Nome.setText(null);
								Escrita_Aliados.setText(null);
								Tela_Cadastro.dispose();
								Janela_Principal();

							}
						} else {

							Escrita_Nome.setEnabled(false);
							Escrita_Aliados.setEnabled(false);

							JOptionPane.showMessageDialog(null, "Não há espaço para novos cadastros");

							Tela_Cadastro.dispose();
							Janela_Principal();

						}

					}

				}

			});

		}

		static int verificandoLinha() {

			int selectLinha = Tabela_Consultas.getSelectedRow();
			int posMatriz = (int) Tabela_Consultas.getValueAt(selectLinha, 0);

			return posMatriz;
		}

		static void Janela_Excluir() {

			Botao_Remover.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (Tabela_Consultas.getSelectedRow() != -1) {

						int deseja = JOptionPane.showConfirmDialog(null, "Deseja remover da lista?");

						if (deseja == JOptionPane.YES_OPTION) {

							int posMatriz = verificandoLinha();

							((DefaultTableModel) Tabela_Consultas.getModel()).setValueAt(DESOCUPADO, posMatriz - 1, 1);
							((DefaultTableModel) Tabela_Consultas.getModel()).setValueAt(DESOCUPADO, posMatriz - 1, 2);

							Comportamento_Excluir();

						}

					} else {

						JOptionPane.showMessageDialog(null, "Selecione a linha que deseja desocupar!");

					}

				}
			});

		}

		static void Comportamento_Excluir() {

			int selectLinha = Tabela_Consultas.getSelectedRow();
			int posMatriz = verificandoLinha();

			String valorNome = (String) Tabela_Consultas.getModel().getValueAt(selectLinha, 1);
			String valorEnd = (String) Tabela_Consultas.getValueAt(selectLinha, 2);
			
			Posicao[posMatriz - 1][0] = valorNome;
			Posicao[posMatriz - 1][1] = valorEnd;

		}

			
		static void acaoPesquisar(JTable x) {

			Escrita_Pesquisa.addKeyListener(new java.awt.event.KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {
					
					acaoBuscar(x);

				}

				@Override
				public void keyReleased(KeyEvent e) {

					acaoBuscar(x);

				}

				@Override
				public void keyTyped(KeyEvent e) {

					acaoBuscar(x);

				}
			});

		}

		@SuppressWarnings("unchecked")
		static void acaoBuscar(JTable x) {

			DefaultTableModel model = (DefaultTableModel) Tabela_Consultas.getModel();

			@SuppressWarnings({ "rawtypes" })
			TableRowSorter sorter = new TableRowSorter(model);
			x.setRowSorter(sorter);
			String text = Escrita_Pesquisa.getText().toUpperCase();

			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				sorter.setRowFilter(RowFilter.regexFilter(text));
			}

		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		static void limparPesquisa() {

			DefaultTableModel model = (DefaultTableModel) Tabela_Consultas.getModel();
			TableRowSorter sorter = new TableRowSorter(model);
			Tabela_Consultas.setRowSorter(sorter);
			Escrita_Pesquisa.setText(null);

		}


		static void Comportamento_Sobre() {

			Botao_Sobre.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,
									"\n - Faculdade: Alvorada de Maringá" + "\n"
									+ "\n - Matéria: Estrutura de Dados" + "\n"
									+ "\n - Professor: Altieres de Matos"
									+ "\n" + "\n - Aluno: Gilsimar Habner Monteiro" + "\n" + "\n");

				}
			});

		}

		static void Comportamento_Voltar_Janela_Principal() {

			Botao_Voltar_Tela_Principal.setVisible(true);
			Botao_Voltar_Tela_Principal.setToolTipText("Clique para voltar a tela principal");
			Botao_Voltar_Tela_Principal.setIcon(new ImageIcon(Fila_de_Espera.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif")));

			Botao_Voltar_Tela_Principal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Tela_Cadastro.dispose();
					Janela_Principal();

				}
			});

		}

		static void Comportamento_Sair() {

			Botao_Sair.setVisible(true);
			Botao_Sair.setFont(new Font(ARIAL, Font.BOLD, 13));
			Botao_Sair.setToolTipText("Clique para sair do programa");
			Botao_Sair.setIcon(new ImageIcon(IMAGEM_CADASTRAR));

			Botao_Sair.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					int desejo = JOptionPane.showConfirmDialog(null,
							"Toda a lista será perdida, deseja sair do programa?");
					if (desejo == JOptionPane.YES_OPTION) {
						Tela_Principal.dispose();
					}

				}
			});

		}
}