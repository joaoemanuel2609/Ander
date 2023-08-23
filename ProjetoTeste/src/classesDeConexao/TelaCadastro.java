package classesDeConexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField nomeCompleto;
	private JTextField Usuario;
	private JPasswordField senhaUsuario;
	protected TelaCadastro telacadastro;
	protected TelaLogin telaLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpf = new JLabel("Nome completo :");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCpf.setBounds(561, 146, 176, 48);
		contentPane.add(lblCpf);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario.setBounds(561, 227, 149, 48);
		contentPane.add(lblUsuario);
		
		JLabel lblCpf_1_1 = new JLabel("Senha :");
		lblCpf_1_1.setForeground(Color.WHITE);
		lblCpf_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCpf_1_1.setBounds(561, 314, 149, 48);
		contentPane.add(lblCpf_1_1);
		
		nomeCompleto = new JTextField();
		nomeCompleto.setFont(new Font("Tahoma", Font.BOLD, 15));
		nomeCompleto.setBounds(753, 162, 166, 20);
		contentPane.add(nomeCompleto);
		nomeCompleto.setColumns(10);
		
		Usuario = new JTextField();
		Usuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		Usuario.setColumns(10);
		Usuario.setBounds(753, 243, 166, 20);
		contentPane.add(Usuario);
		
		senhaUsuario = new JPasswordField();
		senhaUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		senhaUsuario.setBounds(753, 330, 166, 20);
		contentPane.add(senhaUsuario);
		
		JButton adcUsuario = new JButton("Adicionar novo usuario");
		adcUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nomeCompleto.getText().equals("") ||Usuario.getText().equals("")||senhaUsuario.getText().equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Dados nao informados");
			
				}else {
				try {
					Connection con = Conexao.conexao();
					String sql = "insert into usuarios(usuario,senha,nome )"
							+ "values (?,?,?) ";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1,Usuario.getText());
					stmt.setString(2, new String(senhaUsuario.getPassword()));
					stmt.setString(3,nomeCompleto.getText());
					
				
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Usuario Cadastrado com sucesso");
					nomeCompleto.setText("");
					Usuario.setText("");
					senhaUsuario.setText("");
				
					TelaPrincipal tl  = new TelaPrincipal();
					this.dispose();
					tl.setVisible(true);
					
					
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		adcUsuario.setFont(new Font("Tahoma", Font.BOLD, 23));
		adcUsuario.setBounds(605, 391, 314, 60);
		contentPane.add(adcUsuario);
		 adcUsuario.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		 adcUsuario.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		 adcUsuario.setBorderPainted(false); // Ocultando a borda do botão
		 adcUsuario.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		 adcUsuario.setFocusPainted(false); // Removendo o retângulo de foco ao clicar no botão

		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaCadastro.class.getResource("/classesDeConexao/WhatsApp Image 2023-07-22 at 22.24.03 (1).jpeg")));
		lblNewLabel_1.setBounds(-21, 0, 1080, 1001);
		contentPane.add(lblNewLabel_1);
	}
}
