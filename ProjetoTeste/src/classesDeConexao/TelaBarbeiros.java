package classesDeConexao;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Escolar.conexao;
import de.wannawork.jcalendar.JCalendarComboBox;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class TelaBarbeiros extends JFrame {

	private JPanel contentPane;
	private JTable tbDados;
	private JTextField dinheiro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBarbeiros frame = new TelaBarbeiros();
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
	public TelaBarbeiros() {
		setTitle("Procurar meus serviços");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Comanda de qual barbeiro :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(129, 43, 256, 38);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.setBounds(395, 52, 152, 23);
		contentPane.add(comboBox);

		// Adicione a opção "Todos" antes de recuperar os nomes dos usuários
		List<String> nomesUsuarios = new ArrayList<>();
		nomesUsuarios.add("Todos");

		try {
		    Connection con = conexao.conectar();
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
		JCalendarComboBox DataInicio = new JCalendarComboBox();
		DataInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DataInicio.setBounds(645, 52, 138, 20);
		contentPane.add(DataInicio);
		
		JCalendarComboBox DataAcaba = new JCalendarComboBox();
		DataAcaba.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DataAcaba.setBounds(885, 52, 138, 20);
		contentPane.add(DataAcaba);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 145, 1227, 384);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setFont(new Font("Tahoma", Font.BOLD, 13));
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Barbeiro", "Nome do cliente", "Servi\u00E7o", "Horario Come\u00E7a", "Horario Termina", "Data servi\u00E7o", "Forma de pagamento", "Valor cobrado"
			}
		));
		scrollPane.setViewportView(tbDados);
		
		JLabel lblDe = new JLabel("De :");
		lblDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDe.setBounds(600, 43, 57, 38);
		contentPane.add(lblDe);
		
		JLabel lblAte = new JLabel("Até :");
		lblAte.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAte.setBounds(819, 42, 57, 38);
		contentPane.add(lblAte);
		
		JButton btnNewButton = new JButton("Mostrar");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
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
		                    modelo.addRow(new Object[]{rs.getString("barbeiro"),rs.getString("nomeCliente"), rs.getString("servico"),  rs.getString("horaInicio"), rs.getString("horaTermina"),rs.getString("data_servico"), rs.getString("formaPagamento"), rs.getString("valorServico")});
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
		                	 modelo.addRow(new Object[]{rs.getString("barbeiro"),rs.getString("nomeCliente"), rs.getString("servico"),  rs.getString("horaInicio"), rs.getString("horaTermina"),rs.getString("data_servico"), rs.getString("formaPagamento"), rs.getString("valorServico")});
		                }

		            } catch (SQLException e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao consultar os serviços.");
		            }
		            double total= 0;
		            try {
						Connection con = conexao.conectar();
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
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
		        }
			}
		});
		btnNewButton.setBounds(1033, 48, 106, 27);
		btnNewButton.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton.setForeground(Color.BLACK); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnNewButton);
		
		dinheiro = new JTextField();
		dinheiro.setEditable(false);
		dinheiro.setFont(new Font("Tahoma", Font.BOLD, 20));
		dinheiro.setBounds(738, 583, 138, 50);
		contentPane.add(dinheiro);
		dinheiro.setColumns(10);
		
		JLabel lblValorCorrespondente = new JLabel("Valor correspondente :");
		lblValorCorrespondente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblValorCorrespondente.setBounds(488, 582, 240, 50);
		contentPane.add(lblValorCorrespondente);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaBarbeiros tp = new TelaBarbeiros();
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				tp.dispose();
				telaPrincipal.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(1165, 11, 89, 23);
		btnNewButton_1.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton_1.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton_1.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton_1.setForeground(Color.BLACK); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaBarbeiros.class.getResource("/classesDeConexao/babidi-removebg-preview (1).png")));
		lblNewLabel_1.setBounds(-27, 0, 231, 134);
		contentPane.add(lblNewLabel_1);
		
		

	}
}

