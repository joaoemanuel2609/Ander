package classesDeConexao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;

public class TelaADM extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaADM frame = new TelaADM();
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
	public TelaADM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Adicionar serviço");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaADCchef tl  = new TelaADCchef ();
				TelaADM telaADM = new TelaADM();
				telaADM.dispose();
				tl.setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(473, 114, 314, 74);
		btnNewButton.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnNewButton);
		
		JButton btnTodosOsServios = new JButton("Todos os serviços ");
		btnTodosOsServios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTodosOsServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaChef tp = new TelaChef();
				TelaADM telaADM = new TelaADM();
				telaADM.dispose();
				tp.setVisible(true);
			}
		});
		btnTodosOsServios.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnTodosOsServios.setBounds(473, 236, 334, 84);
		btnTodosOsServios.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnTodosOsServios.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnTodosOsServios.setBorderPainted(false); // Ocultando a borda do botão
		btnTodosOsServios.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnTodosOsServios);
		
		JButton btnExcluirBarbeiro = new JButton("Excluir barbeiro");
		btnExcluirBarbeiro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluirBarbeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaDeletaBarbeiro tdb = new TelaDeletaBarbeiro();
				TelaADM telaADM = new TelaADM();
				telaADM.dispose();
				tdb.setVisible(true);
				
			}
		});
		btnExcluirBarbeiro.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnExcluirBarbeiro.setBounds(473, 379, 326, 74);
		btnExcluirBarbeiro.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnExcluirBarbeiro.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnExcluirBarbeiro.setBorderPainted(false); // Ocultando a borda do botão
		btnExcluirBarbeiro.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnExcluirBarbeiro);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaADM.class.getResource("/classesDeConexao/TelaADC.png")));
		lblNewLabel.setBounds(-14, -43, 1315, 753);
		contentPane.add(lblNewLabel);
		
		
	}
}
