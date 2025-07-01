package FormularioBanco.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JPanel loginPanel;
    private JTextField userField;
    private JButton ingresarButton;
    private JTextArea usuarioCliente123TextArea;
    private JTextArea contraseñaClave456TextArea;
    private JPasswordField passwordField;
    private JLabel tituloLabel;
    private JLabel usuarioLabel;
    private JLabel passwordLabel;

    private static final String USUARIO_VALIDO = "cliente123";
    private static final String PASSWORD_VALIDO = "clave456";

    public LoginForm(){
        setTitle("Iniciar Sesión");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(loginPanel);
        setLocationRelativeTo(null);

        Color azul = new Color(0, 84, 159);
        Color gris = new Color(102, 102, 102); // Gris para textos
        Color fondoClaro = new Color(174, 214, 241);

        Font tituloFont = new Font("Segoe UI", Font.BOLD, 24);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font botonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font campoFont = new Font("Segoe UI", Font.PLAIN, 14);

        loginPanel.setBackground(fondoClaro);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));


        tituloLabel.setFont(tituloFont);
        tituloLabel.setForeground(azul);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setText("Iniciar Sesión");

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(labelFont);
        userLabel.setForeground(gris);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(azul);
        userField.setFont(campoFont);
        userField.setForeground(gris);
        userField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        passwordField.setFont(campoFont);
        passwordField.setForeground(gris);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

// Configurar botón de ingreso
        ingresarButton.setText("INGRESAR");  // Texto en mayúsculas
        ingresarButton.setBackground(azul);
        ingresarButton.setForeground(Color.WHITE);
        ingresarButton.setFont(botonFont);
        ingresarButton.setFocusPainted(false);
        ingresarButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(gris.darker()),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        ingresarButton.setOpaque(true);
        setVisible(true);


        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText().trim();
                String password = new String(passwordField.getPassword());
                if (usuario.equals(USUARIO_VALIDO) && password.equals(PASSWORD_VALIDO)) {
                    BancoForm bancoForm = new BancoForm(usuario);
                    dispose();
                    bancoForm.setSize(500,300);
                    bancoForm.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta","Error de login",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    userField.setText("");
                }
            }
        });
    }



}

