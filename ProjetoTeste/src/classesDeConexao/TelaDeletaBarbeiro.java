package classesDeConexao;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class TelaDeletaBarbeiro extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeletaBarbeiro frame = new TelaDeletaBarbeiro();
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
	public TelaDeletaBarbeiro() {
		setTitle("Deletar barbeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	contentPane.setLayout(null);
	JComboBox barbeiro = new JComboBox();
	barbeiro.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		barbeiro.setBounds(664, 259, 246, 51);
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
		            
		            JLabel lblNewLabel = new JLabel("Escolha o Barbeiro que deseja excluir :");
		            lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		            lblNewLabel.setBounds(297, 259, 357, 51);
		            contentPane.add(lblNewLabel);
		            
		            JTextArea txtrQuandoUmBarbeiro = new JTextArea();
		            txtrQuandoUmBarbeiro.setFont(new Font("Arial Black", Font.BOLD, 14));
		            txtrQuandoUmBarbeiro.setRows(5);
		            txtrQuandoUmBarbeiro.setColumns(1);
		            txtrQuandoUmBarbeiro.setEditable(false);
		            txtrQuandoUmBarbeiro.setText("Quando um barbeiro é excluído todos seus serviços tambem são deletados , logo o valor que corresponde a seus serviços tambem vai ser excluido.");
		            txtrQuandoUmBarbeiro.setBounds(23, 54, 1231, 29);
		            contentPane.add(txtrQuandoUmBarbeiro);
		            
		            JButton btnNewButton = new JButton("Deletar");
		            btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		            btnNewButton.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e) {
		            		try {
								Connection con = Conexao.conexao();
								String sql =" DELETE FROM usuarios "
										+" WHERE nome = ? ";
								 PreparedStatement stmt = con.prepareStatement(sql);
								 stmt.setString(1,barbeiro.getSelectedItem().toString());
								 stmt.execute();
								 String name = barbeiro.getSelectedItem().toString();
									JOptionPane.showMessageDialog(null, "Usuario "+name+" excluido com sucesso !");
									con.close();
									stmt.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "ERRO!!!");
								e1.printStackTrace();
							}
		            		
		            	}
		            });
		            btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		            btnNewButton.setBounds(569, 321, 150, 40);
		            contentPane.add(btnNewButton);
		            
		            JButton btnNewButton_1 = new JButton("Voltar");
		            btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		            btnNewButton_1.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e) {
		            		TelaDeletaBarbeiro tdb = new TelaDeletaBarbeiro();
		    				TelaADM telaADM = new TelaADM();
		    				tdb.dispose();
		    				telaADM.setVisible(true);
		            	}
		            });
		            btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		            btnNewButton_1.setBounds(1140, 11, 114, 32);
		            contentPane.add(btnNewButton_1);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				  JOptionPane.showMessageDialog(null, "Erro ao carregar os nomes dos usuários.");
			}
	}
}
