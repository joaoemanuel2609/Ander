package classesDeConexao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;

public class TelaLogin extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField Tusuatio;
    private JPasswordField Tsenha;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaLogin frame = new TelaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaLogin() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(""));
        setResizable(false);
        setTitle("Tela de login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1000, 720);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Usuário :");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(604, 220, 85, 48);
        contentPane.add(lblNewLabel);

        JLabel lblSenha = new JLabel("Senha :");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSenha.setBounds(604, 300, 85, 48);
        contentPane.add(lblSenha);

        Tusuatio = new JTextField();
        Tusuatio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        Tusuatio.setBounds(708, 236, 202, 20);
        contentPane.add(Tusuatio);
        Tusuatio.setColumns(10);

        Tsenha = new JPasswordField();
        Tsenha.setBounds(708, 317, 202, 20);
        contentPane.add(Tsenha);

        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ("Admin".equals(Tusuatio.getText()) || "admin".equals(Tusuatio.getText())) {
                    try {
                        Connection con = Conexao.conexao();
                        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
                        PreparedStatement stmt = con.prepareStatement(sql);

                        stmt.setString(1, Tusuatio.getText());
                        stmt.setString(2, new String(Tsenha.getPassword()));

                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Ola Ander, seja bem vindo");

                            Tusuatio.setText("");
                            Tsenha.setText("");
                            TelaADM tp = new TelaADM();
                            TelaLogin telaLogin = new TelaLogin();
                            telaLogin.dispose();
                            tp.setVisible(true);

                            stmt.close();
                            con.close();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario nao foi localizado");
                        }

                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Usuario nao foi localizado");
                        e1.printStackTrace();
                    }

                } else {
                    try {
                        Connection con = Conexao.conexao();
                        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
                        PreparedStatement stmt = con.prepareStatement(sql);
                        String nome = Tusuatio.getText();
                        stmt.setString(1, Tusuatio.getText());
                        stmt.setString(2, new String(Tsenha.getPassword()));

                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Ola " + nome +", seja bem vindo");

                            Tusuatio.setText("");
                            Tsenha.setText("");
                            TelaPrincipal tp = new TelaPrincipal();
                            TelaLogin telaLogin = new TelaLogin();
                            telaLogin.dispose();
                            tp.setVisible(true);

                            stmt.close();
                            con.close();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario nao foi localizado");
                        }

                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Usuario nao foi localizado");
                        e1.printStackTrace();
                    }

                }
            }
        });
        btnNewButton.setBounds(699, 363, 145, 27);
        btnNewButton.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
        btnNewButton.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
        btnNewButton.setBorderPainted(false); // Ocultando a borda do botão
        btnNewButton.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
        contentPane.add(btnNewButton);

        JButton btnCadastrarse = new JButton("cadastrar-se");
        btnCadastrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCadastrarse.setBackground(Color.WHITE);
        btnCadastrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCadastro tc = new TelaCadastro();
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.dispose();
                tc.setVisible(true);
            }
        });
        btnCadastrarse.setForeground(UIManager.getColor("Button.background"));
        btnCadastrarse.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnCadastrarse.setBounds(699, 406, 157, 27);
        btnCadastrarse.setContentAreaFilled(false); // Definindo o conteúdo do botão como não preenchido (não opaco)
        btnCadastrarse.setOpaque(false); // Tornando o botão não opaco (fundo transparente)
        btnCadastrarse.setBorderPainted(false); // Ocultando a borda do botão
        btnCadastrarse.setForeground(Color.WHITE); // Definindo a cor do texto do botão para PRTO
        contentPane.add(btnCadastrarse);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(TelaLogin.class.getResource("/classesDeConexao/WhatsApp Image 2023-07-22 at 22.24.03 (1).jpeg")));
        lblNewLabel_2.setBounds(0, 0, 1497, 1023);
        contentPane.add(lblNewLabel_2);

        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == Tusuatio) {
                Tsenha.requestFocus();
            } else if (e.getSource() == Tsenha) {
                // Aqui você pode executar a ação do botão "Entrar"
                // Por exemplo: btnNewButton.doClick();
            }
        }
    }

    // Métodos não utilizados do KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
