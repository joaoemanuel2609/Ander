package classesDeConexao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setTitle("Menu");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Adicionar serviço");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaAdcServiço tl  = new TelaAdcServiço ();
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaPrincipal.dispose();
				tl.setVisible(true);


			}
		});
		btnNewButton.setBounds(438, 171, 363, 66);
		btnNewButton.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnNewButton.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnNewButton.setBorderPainted(false); // Ocultando a borda do botão
		btnNewButton.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		contentPane.add(btnNewButton);

		JButton btnTodosOsServios = new JButton("Todos os serviços ");
		btnTodosOsServios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTodosOsServios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaBarbeiros tp = new TelaBarbeiros();
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaPrincipal.dispose();
				tp.setVisible(true);
			}
		});
		btnTodosOsServios.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnTodosOsServios.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
		btnTodosOsServios.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
		btnTodosOsServios.setBorderPainted(false); // Ocultando a borda do botão
		btnTodosOsServios.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
		btnTodosOsServios.setBounds(438, 353, 386, 66);
		contentPane.add(btnTodosOsServios);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/classesDeConexao/TelaADC.png")));
		lblNewLabel.setBounds(-14, -43, 1315, 753);
		contentPane.add(lblNewLabel);


	}

}
