package classesDeConexao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import de.wannawork.jcalendar.JCalendarComboBox;
import java.awt.Cursor;

public class TelaChef extends JFrame {

	private JPanel contentPane;
	private JTable tbDados;
	private JTextField dinheiro;
	private JTextField Deletar;
	private JTextField comissao;
	private JTextField comissao2;
	private JTextField comissao3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaChef frame = new TelaChef();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaChef() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 1254, 297);
		contentPane.add(scrollPane);

		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Barbeiro", "Servi\u00E7o", "Nome do cliente", "Horarios do servi\u00E7o", "Horarios fim do servi\u00E7o", "Forma de pagamento", "Valor Cobrado", "Data"
			}
		));
		tbDados.getColumnModel().getColumn(0).setPreferredWidth(31);
		tbDados.getColumnModel().getColumn(2).setPreferredWidth(135);
		scrollPane.setViewportView(tbDados);
		JCalendarComboBox DataInicio = new JCalendarComboBox();
		DataInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DataInicio.setBounds(627, 55, 138, 20);
		contentPane.add(DataInicio);
		
		JCalendarComboBox DataAcaba = new JCalendarComboBox();
		DataAcaba.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DataAcaba.setBounds(843, 55, 138, 20);
		contentPane.add(DataAcaba);
		
		// ... Código anterior ...

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(395, 52, 152, 23);
		contentPane.add(comboBox);

		// Adicione a opção "Todos" antes de recuperar os nomes dos usuários
		List<String> nomesUsuarios = new ArrayList<>();
		nomesUsuarios.add("Todos");

		try {
		    Connection con = Conexao.conexao();
		    String sql = "SELECT nome FROM usuarios";
		    PreparedStatement stmt = con.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();

		    while (rs.next()) {
		        nomesUsuarios.add(rs.getString("nome"));
		    }

		    stmt.close();
		    con.close();

		    // Define os nomes dos usuários como o modelo do JComboBox
		    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nomesUsuarios.toArray(new String[0]));
		    comboBox.setModel(model);

		} catch (SQLException e) {
		    JOptionPane.showMessageDialog(null, "Erro ao carregar os nomes dos usuários.");
		}

		// ... Resto do código ...

	

		// ... Código anterior ...

		JButton listarDados = new JButton("Procurar");
		listarDados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listarDados.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (comboBox.getSelectedItem().toString().equals("Todos")) {
		            try {	
		                Connection con = Conexao.conexao();
		                String sql = "SELECT * FROM servicos WHERE data_servico BETWEEN ? AND ?";
		                

		                PreparedStatement stmt = con.prepareStatement(sql);
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada = sdf.format(DataInicio.getDate());
				        stmt.setString(1, dataFormatada);
						
				        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
				        stmt.setString(2, dataFormatada2);
						

		                ResultSet rs = stmt.executeQuery();

		                DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
		                modelo.setNumRows(0);

		                while (rs.next()) {
		                    modelo.addRow(new Object[]{rs.getString("ID"),rs.getString("barbeiro"), rs.getString("servico"), rs.getString("nomeCliente"), rs.getString("horaInicio"), rs.getString("horaTermina"), rs.getString("formaPagamento"), rs.getString("valorServico"),rs.getString("data_servico")});
		                }

		            } catch (SQLException e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao consultar os serviços.");
		                
		            }	
		             
		                
		        } else {
		            try {	
		                Connection con = Conexao.conexao();
		                String sql = "SELECT * FROM servicos WHERE data_servico BETWEEN ? AND ?"
		                	+ " and barbeiro = ?";
		                PreparedStatement stmt = con.prepareStatement(sql);
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada = sdf.format(DataInicio.getDate());
				        stmt.setString(1, dataFormatada);
						
				        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
				        stmt.setString(2, dataFormatada2);
		                stmt.setString(3, comboBox.getSelectedItem().toString());
		                ResultSet rs = stmt.executeQuery();

		                DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
		                modelo.setNumRows(0);

		                while (rs.next()) {
		                    modelo.addRow(new Object[]{rs.getString("id"),rs.getString("barbeiro"), rs.getString("servico"), rs.getString("nomeCliente"), rs.getString("horaInicio"), rs.getString("horaTermina"), rs.getString("formaPagamento"), rs.getString("valorServico"),rs.getString("data_servico")});
		                }

		            } catch (SQLException e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao consultar os serviços.");
		            }
		        }
		        double total= 0;
		        if(comboBox.getSelectedItem().toString().equals("Todos")) {
		        try {
					Connection con = Conexao.conexao();
					String sql = "SELECT SUM(valorServico) FROM servicos WHERE data_servico BETWEEN ? AND ?"	;
					
					  
					  PreparedStatement stmt = con.prepareStatement(sql);
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada = sdf.format(DataInicio.getDate());
				        stmt.setString(1, dataFormatada);
						
				        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
				        stmt.setString(2, dataFormatada2);
		                ResultSet rs = stmt.executeQuery();
		                while(rs.next()) {
		                	total = rs.getDouble(1);
		                }
		                dinheiro.setText("R$ " +(total));
		                
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        }else {
		        	try {
						Connection con = Conexao.conexao();
						String sql = "SELECT SUM(valorServico) FROM servicos WHERE data_servico BETWEEN ? AND ? AND barbeiro = ?";
								
						  PreparedStatement stmt = con.prepareStatement(sql);
						  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					        String dataFormatada = sdf.format(DataInicio.getDate());
					        stmt.setString(1, dataFormatada);
							
					        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
					        stmt.setString(2, dataFormatada2);
						  stmt.setString(3, comboBox.getSelectedItem().toString());
			                ResultSet rs = stmt.executeQuery();
			                while(rs.next()) {
			                	total = rs.getDouble(1);
			                }
			                dinheiro.setText("R$ " +(total));
			                int comi1 = (int) ((total * 40) / 100);
			                int comi2 = (int) ((total * 45) / 100);
			                int comi3 = (int) ((total * 50) / 100);
			                comissao.setText("R$ "+(comi1));
			                comissao2.setText("R$ "+(comi2));
			                comissao3.setText("R$ "+(comi3));
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
		        }
		        }
		});
		listarDados.setFont(new Font("Tahoma", Font.BOLD, 18));
		listarDados.setBounds(1019, 48, 131, 35);
		contentPane.add(listarDados);
		listarDados.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		listarDados.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		listarDados.setBorderPainted(false); // Ocultando a borda do botão
		listarDados.setForeground(Color.BLACK); // Definindo a cor do texto do botão para PRTO
		
		
		dinheiro = new JTextField();
		dinheiro.setEditable(false);
		dinheiro.setFont(new Font("Tahoma", Font.BOLD, 20));
		dinheiro.setBounds(420, 456, 164, 40);
		contentPane.add(dinheiro);
		dinheiro.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Valor total :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(236, 456, 164, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblDeletarServio = new JLabel("Deletar serviço :");
		lblDeletarServio.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDeletarServio.setBounds(745, 530, 185, 35);
		contentPane.add(lblDeletarServio);
		
		Deletar = new JTextField();
		Deletar.setFont(new Font("Tahoma", Font.BOLD, 20));
		Deletar.setColumns(10);
		Deletar.setBounds(925, 528, 131, 40);
		contentPane.add(Deletar);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.conexao();
					String sql = " DELETE FROM servicos WHERE ID = ? " ;
					 PreparedStatement stmt = con.prepareStatement(sql);
					 
					 stmt.setString(1,Deletar.getText());
		              stmt.executeUpdate();
		                
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (comboBox.getSelectedItem().toString().equals("Todos")) {
		            try {	
		                Connection con = Conexao.conexao();
		                String sql = "SELECT * FROM servicos WHERE data_servico BETWEEN ? AND ?";
		                

		                PreparedStatement stmt = con.prepareStatement(sql);
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada = sdf.format(DataInicio.getDate());
				        stmt.setString(1, dataFormatada);
						
				        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
				        stmt.setString(2, dataFormatada2);
						

		                ResultSet rs = stmt.executeQuery();

		                DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
		                modelo.setNumRows(0);

		                while (rs.next()) {
		                    modelo.addRow(new Object[]{rs.getString("ID"),rs.getString("barbeiro"), rs.getString("servico"), rs.getString("nomeCliente"), rs.getString("horaInicio"), rs.getString("horaTermina"), rs.getString("formaPagamento"), rs.getString("valorServico"),rs.getString("data_servico")});
		                }

		            } catch (SQLException e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao consultar os serviços.");
		                
		            }	
		             
		                
		        } else {
		            try {	
		                Connection con = Conexao.conexao();
		                String sql = "SELECT * FROM servicos WHERE data_servico BETWEEN ? AND ?"
		                	+ " and barbeiro = ?";
		                PreparedStatement stmt = con.prepareStatement(sql);
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada = sdf.format(DataInicio.getDate());
				        stmt.setString(1, dataFormatada);
						
				        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
				        stmt.setString(2, dataFormatada2);
		                stmt.setString(3, comboBox.getSelectedItem().toString());
		                ResultSet rs = stmt.executeQuery();

		                DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
		                modelo.setNumRows(0);

		                while (rs.next()) {
		                    modelo.addRow(new Object[]{rs.getString("id"),rs.getString("barbeiro"), rs.getString("servico"), rs.getString("nomeCliente"), rs.getString("horaInicio"), rs.getString("horaTermina"), rs.getString("formaPagamento"), rs.getString("valorServico"),rs.getString("data_servico")});
		                }

		            } catch (SQLException e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao consultar os serviços.");
		            }
		        }
		        double total= 0;
		        if(comboBox.getSelectedItem().toString().equals("Todos")) {
		        try {
					Connection con = Conexao.conexao();
					String sql = "SELECT SUM(valorServico) FROM servicos WHERE data_servico BETWEEN ? AND ?"	;
					
					  
					  PreparedStatement stmt = con.prepareStatement(sql);
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada = sdf.format(DataInicio.getDate());
				        stmt.setString(1, dataFormatada);
						
				        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
				        stmt.setString(2, dataFormatada2);
		                ResultSet rs = stmt.executeQuery();
		                while(rs.next()) {
		                	total = rs.getDouble(1);
		                }
		                int comi1 = (int) ((total * 40) / 100);
		                int comi2 = (int) ((total * 45) / 100);
		                int comi3 = (int) ((total * 50) / 100);
		                dinheiro.setText("R$ " +(total));
		                comissao.setText("R$ "+(comi1));
		                comissao2.setText("R$ "+(comi2));
		                comissao3.setText("R$ "+(comi3));
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        }else {
		        	try {
						Connection con = Conexao.conexao();
						String sql = "SELECT SUM(valorServico) FROM servicos WHERE data_servico BETWEEN ? AND ? AND barbeiro = ?";
								
						  PreparedStatement stmt = con.prepareStatement(sql);
						  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					        String dataFormatada = sdf.format(DataInicio.getDate());
					        stmt.setString(1, dataFormatada);
							
					        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					        String dataFormatada2 = sdf2.format(DataAcaba.getDate());
					        stmt.setString(2, dataFormatada2);
						  stmt.setString(3, comboBox.getSelectedItem().toString());
			                ResultSet rs = stmt.executeQuery();
			                while(rs.next()) {
			                	total = rs.getDouble(1);
			                }
			                int comi1 = (int) ((total * 40) / 100);
			                int comi2 = (int) ((total * 45) / 100);
			                int comi3 = (int) ((total * 50) / 100);
			                dinheiro.setText("R$ " +(total));
			                comissao.setText("R$ "+(comi1));
			                comissao2.setText("R$ "+(comi2));
			                comissao3.setText("R$ "+(comi3));
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
		        }
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(1036, 514, 84, 59);
		contentPane.add(btnNewButton);
		btnNewButton.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton.setForeground(Color.BLACK); // Definindo a cor do texto do botão para PRTO
		btnNewButton.setFocusPainted(false); // Removendo o retângulo de foco ao clicar no botão

		
		JLabel lblEscolherBarbeiro = new JLabel("Escolher Barbeiro:");
		lblEscolherBarbeiro.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEscolherBarbeiro.setBounds(199, 48, 185, 25);
		contentPane.add(lblEscolherBarbeiro);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaChef.class.getResource("/classesDeConexao/babidi-removebg-preview (1).png")));
		lblNewLabel_1.setBounds(10, 0, 194, 137);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblData = new JLabel("data :");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblData.setBounds(563, 50, 73, 25);
		contentPane.add(lblData);
		
		
		JLabel lblAte = new JLabel("até :");
		lblAte.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAte.setBounds(787, 50, 73, 25);
		contentPane.add(lblAte);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaChef tp = new TelaChef();
				TelaADM telaADM = new TelaADM();
				telaADM.setVisible(true);;
				tp.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(1165, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton_1.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton_1.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton_1.setForeground(Color.BLACK); // Definindo a cor do texto do botão para PRTO
	
		
		
		JLabel lblComisso = new JLabel("Comissão 40% :");
		lblComisso.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblComisso.setBounds(236, 509, 164, 35);
		contentPane.add(lblComisso);
		
		comissao = new JTextField();
		comissao.setFont(new Font("Tahoma", Font.BOLD, 20));
		comissao.setEditable(false);
		comissao.setColumns(10);
		comissao.setBounds(420, 507, 164, 40);
		contentPane.add(comissao);
		
		JLabel lblComisso_2 = new JLabel("Comissão 45% :");
		lblComisso_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblComisso_2.setBounds(236, 565, 164, 35);
		contentPane.add(lblComisso_2);
		
		comissao2 = new JTextField();
		comissao2.setFont(new Font("Tahoma", Font.BOLD, 20));
		comissao2.setEditable(false);
		comissao2.setColumns(10);
		comissao2.setBounds(420, 558, 164, 40);
		contentPane.add(comissao2);
		
		JLabel lblComisso_2_1 = new JLabel("Comissão 50% :");
		lblComisso_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblComisso_2_1.setBounds(236, 621, 164, 35);
		contentPane.add(lblComisso_2_1);
		
		comissao3 = new JTextField();
		comissao3.setFont(new Font("Tahoma", Font.BOLD, 20));
		comissao3.setEditable(false);
		comissao3.setColumns(10);
		comissao3.setBounds(420, 616, 164, 40);
		contentPane.add(comissao3);
		
		}	
}

		// ... Resto do código ...
