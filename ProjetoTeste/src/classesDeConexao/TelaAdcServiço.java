package classesDeConexao;

import java.awt.EventQueue;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import de.wannawork.jcalendar.JCalendarDialog;
import java.awt.Component;
import de.wannawork.jcalendar.FlatButton;
import de.wannawork.jcalendar.DayLabel;
import de.wannawork.jcalendar.JMonthPanel;
import de.wannawork.jcalendar.JCalendarComboBox;
import de.wannawork.jcalendar.JCalendarPanel;
import javax.swing.JLabel;
import java.awt.Cursor;

public class TelaAdcServiço extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NomeCliente;
	private JComboBox FormaPagamento;
	private JComboBox valorCobrado;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JLabel lblFormaDePagamento;
	private JLabel lblHorarioFimDo;
	private JLabel lblHorarioDoServio;
	private JLabel lblValorCobrado;
	private JLabel lblServio;
	private JLabel lblNomeDoCliente;
	private JLabel lblNomeDoBarbeiro;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdcServiço frame = new TelaAdcServiço();
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
	public TelaAdcServiço() {
		setTitle("Cadastrar alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NomeCliente = new JTextField();
		NomeCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		NomeCliente.setBounds(589, 155, 396, 35);
		contentPane.add(NomeCliente);
		NomeCliente.setColumns(10);
		
		JComboBox horasComeça = new JComboBox();
		horasComeça.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		horasComeça.setFont(new Font("Tahoma", Font.BOLD, 15));
		horasComeça.setModel(new DefaultComboBoxModel(new String[] {"8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM (meio-dia)", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM", "", "", "", "", ""}));
		horasComeça.setBounds(589, 317, 396, 35);
		contentPane.add(horasComeça);
		
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdcServiço tADC = new TelaAdcServiço();
				TelaPrincipal TP = new TelaPrincipal();
				tADC.dispose();
				TP.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(966, 11, 109, 35);
		btnNewButton_1.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton_1.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton_1.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton_1.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnNewButton_1);
		
		JComboBox horasFinaliza = new JComboBox();
		horasFinaliza.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		horasFinaliza.setModel(new DefaultComboBoxModel(new String[] {"8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM (meio-dia)", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM", ""}));
		horasFinaliza.setFont(new Font("Tahoma", Font.BOLD, 15));
		horasFinaliza.setBounds(589, 377, 396, 35);
		contentPane.add(horasFinaliza);
		
		JComboBox barbeiro = new JComboBox();
		barbeiro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		barbeiro.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		barbeiro.setBounds(589, 92, 396, 35);
		contentPane.add(barbeiro);
		 try {
				Connection con = Conexao.conexao();
				  String sql = "SELECT nome FROM usuarios";
		            PreparedStatement stmt = con.prepareStatement(sql);
		            ResultSet rs = stmt.executeQuery();
		            List<String> nomesUsuarios = new ArrayList<>();

		            while (rs.next()) {
		                nomesUsuarios.add(rs.getString("nome"));
		            }

		            stmt.close();
		            con.close();

		            // Define os nomes dos usuários como o modelo do JComboBox
		            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nomesUsuarios.toArray(new String[0]));
		            barbeiro.setModel(model);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				  JOptionPane.showMessageDialog(null, "Erro ao carregar os nomes dos usuários.");
			}
		
		JComboBox servicos = new JComboBox();
		servicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		servicos.setModel(new DefaultComboBoxModel(new String[] {"CORTE CABELO SOCIAL / DEGRADÊ ........R$ 35,00", "CORTE CABELO DEGRADÊ / NAVALHADO ........R$ 35,00", "MAQUINA C/ 1 PENTE ........R$ 20,00", "ACABAMENTO / PEZINHO .......R$15,00", "BARBA/MAQUINA/TESOURA/NAVALHA........R$30,00", "BARBA + ACABAMENTO / PEZINHO ......... R$35,00", "BARBA + SOMBRANCELHA ......... R$35,00", "1 CORTE CABELO + BARBA........R$60,00", "1 CORTE CABELO + SOMBRANCELHA.......R$40,00", "2 CORTE CABELO AMIGO (A).......R$60,00", "SELAGEM CABELO CURTO.......R$90,00", "SELAGEM CABELO MÉDIO.......R$120,00", "PROGRESSIVA CABELO CURTO.......R$100,00", "PROGRESSIVA CABELO MÉDIO.......R$120,00", "LUZES MECHAS/PLATINADO.......R$90,00", "LUZES CABELO MÉDIO.......R$120,00", "LUZES GLOBAL.......R$120,00", "PLANO MENSAL CABELO 1/4.......R$120,00", "PLANO MENSAL CABELO 2/4.......R$0,00", "PLANO MENSAL CABELO 3/4.......R$0,00", "PLANO MENSAL CABELO 4/4.......R$0,00", "PLANO MENSAL CABELO E BARBA 1/4.......R$220,00", "PLANO MENSAL CABELO E BARBA 2/4.......R$0,00", "PLANO MENSAL CABELO E BARBA 3/4.......R$0,00", "PLANO MENSAL CABELO E BARBA 4/4.......R$0,00"}));
		servicos.setFont(new Font("Tahoma", Font.BOLD, 15));
		servicos.setBounds(589, 208, 396, 35);
		contentPane.add(servicos);
		
		FormaPagamento = new JComboBox();
		FormaPagamento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"Dinheiro", "PIX", "Cartao de credito/debito"}));
		FormaPagamento.setFont(new Font("Tahoma", Font.BOLD, 15));
		FormaPagamento.setBounds(589, 438, 396, 35);
		contentPane.add(FormaPagamento);
			JCalendarComboBox calendarComboBox = new JCalendarComboBox();
			calendarComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendarComboBox.setBounds(589, 499, 396, 35);
		contentPane.add(calendarComboBox);
		valorCobrado = new JComboBox();
		valorCobrado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		valorCobrado.setModel(new DefaultComboBoxModel(new String[] {"0", " 5", " 10", " 15", " 20", " 25", " 30", " 35", " 40", " 45", " 50", " 55", " 60", " 65", " 70", " 75", " 80", " 95", " 100", " 105", " 110", " 115", " 120", " 125 ", " 130 ", " 135", " 140", " 145", " 150", " 155", " 160", " 165", " 170", " 175", " 180", " 185", " 190", " 195", " 200", " 205", " 210", " 215", " 220", " 225", " 230", " 235", " 240 ", " 245", " 250", " 255", " 260", " 265", " 270", " 275", " 280", " 285", " 290", " 295", " 300"}));
		valorCobrado.setFont(new Font("Tahoma", Font.BOLD, 15));
		valorCobrado.setBounds(589, 259, 396, 35);
		contentPane.add(valorCobrado);
		
		JButton btnNewButton = new JButton("Adicionar serviço");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(NomeCliente.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Falta o nome do cliente");
				}else {
				try {
					Connection con = Conexao.conexao();
					String sql = "insert into servicos (barbeiro,servico,nomeCliente,horaInicio,horaTermina,formaPagamento,valorServico,data_servico) "
							+ "values (?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1,barbeiro.getSelectedItem().toString());
					stmt.setString(2,servicos.getSelectedItem().toString());
					stmt.setString(3,NomeCliente.getText());
					stmt.setString(4,horasComeça.getSelectedItem().toString());
					stmt.setString(5,horasFinaliza.getSelectedItem().toString());
					stmt.setString(6,FormaPagamento.getSelectedItem().toString());
					stmt.setString(7,valorCobrado.getSelectedItem().toString());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        String dataFormatada = sdf.format(calendarComboBox.getDate());
			        stmt.setString(8, dataFormatada);
					
					stmt.execute();
					JOptionPane.showMessageDialog(null, "Serviço adicionado com sucesso");
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ERRO AO ADICIONAR SERVIÇO !!!");
				}
				}
			}
		});
		btnNewButton.setBounds(524, 570, 282, 35);
		btnNewButton.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Data do serviço:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(387, 499, 182, 35);
		contentPane.add(lblNewLabel);
			lblFormaDePagamento = new JLabel("Forma de pagamento :");
		lblFormaDePagamento.setForeground(Color.WHITE);
		lblFormaDePagamento.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblFormaDePagamento.setBounds(385, 438, 194, 35);
		contentPane.add(lblFormaDePagamento);
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaAdcServiço.class.getResource("/classesDeConexao/babidi-removebg-preview (1).png")));
		lblNewLabel_2.setBounds(10, 11, 226, 168);
		contentPane.add(lblNewLabel_2);
		
	
		lblHorarioFimDo = new JLabel("Horario fim do serviço :");
		lblHorarioFimDo.setForeground(Color.WHITE);
		lblHorarioFimDo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHorarioFimDo.setBounds(385, 377, 194, 35);
		contentPane.add(lblHorarioFimDo);
		
		lblHorarioDoServio = new JLabel("Horario do serviço:");
		lblHorarioDoServio.setForeground(Color.WHITE);
		lblHorarioDoServio.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHorarioDoServio.setBounds(385, 317, 182, 35);
		contentPane.add(lblHorarioDoServio);
		
		lblValorCobrado = new JLabel("Valor cobrado :");
		lblValorCobrado.setForeground(Color.WHITE);
		lblValorCobrado.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValorCobrado.setBounds(385, 259, 182, 35);
		contentPane.add(lblValorCobrado);
		
		lblServio = new JLabel("Serviço :");
		lblServio.setForeground(Color.WHITE);
		lblServio.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblServio.setBounds(387, 207, 182, 35);
		contentPane.add(lblServio);
		
		lblNomeDoCliente = new JLabel("Nome do cliente :");
		lblNomeDoCliente.setForeground(Color.WHITE);
		lblNomeDoCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeDoCliente.setBounds(387, 154, 182, 35);
		contentPane.add(lblNomeDoCliente);
		
		lblNomeDoBarbeiro = new JLabel("Nome do Barbeiro :");
		lblNomeDoBarbeiro.setForeground(Color.WHITE);
		lblNomeDoBarbeiro.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNomeDoBarbeiro.setBounds(387, 89, 182, 35);
		contentPane.add(lblNomeDoBarbeiro);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaAdcServiço.class.getResource("/classesDeConexao/TelaADC.png")));
		lblNewLabel_1.setBounds(0, -310, 1730, 1285);
		contentPane.add(lblNewLabel_1);
		
		
		
	
		
	}
}
