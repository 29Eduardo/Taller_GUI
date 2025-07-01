package FormularioBanco.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BancoForm extends JFrame{
    private JPanel bancoPanel;
    private JButton depositarButton;
    private JButton retirarButton;
    private JButton tranferirButton;
    private JButton salirButton;
    private JLabel saludoLabel;
    private JLabel saldoLabel;
    private JTextArea historialArea;

    private String nombreCliente;
    private double saldo;
    private DecimalFormat formatoMoneda;
    private SimpleDateFormat formatoFecha;

    public BancoForm (String nombreCliente){
        this.nombreCliente = nombreCliente;
        this.saldo =1000.00;
        this.formatoMoneda = new DecimalFormat("$#,##0.00");
        this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        setTitle("Sistema Bancario");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(bancoPanel);
        setLocationRelativeTo(null);

        bancoPanel.setBackground(new Color(214, 234, 248));
        bancoPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);
        Font fuenteTexto = new Font("Arial", Font.PLAIN, 12);
        Font fuenteBotones = new Font("Arial", Font.BOLD, 10);
        Font fuenteSaldo = new Font("Arial", Font.BOLD, 18);

        Color rojo= new Color(237, 28, 36);
        Color azul = new Color(0, 84, 159);
        Color gris = new Color(102, 102, 102);

        saludoLabel.setText("¡Bienvenido, " + nombreCliente + "!");
        saludoLabel.setFont(fuenteTitulo);
        saludoLabel.setForeground(rojo);
        saludoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        saldoLabel.setFont(fuenteSaldo);
        saldoLabel.setForeground(azul);
        saldoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        actualizarSaldo();
        historialArea.setFont(fuenteTexto);
        historialArea.setForeground(gris);
        historialArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15 )
        ));

        JScrollPane scrollPane = new JScrollPane(historialArea);
        scrollPane.setPreferredSize(new Dimension(1150, 500));

        personalizarBoton(depositarButton, "DEPOSITAR", rojo);
        personalizarBoton(retirarButton, "RETIRAR", azul);
        personalizarBoton(tranferirButton, "TRANSFERIR", new Color(0, 128, 0));
        personalizarBoton(salirButton, "SALIR", gris);
        depositarButton.setFont(fuenteBotones);
        retirarButton.setFont(fuenteBotones);
        tranferirButton.setFont(fuenteBotones);
        salirButton.setFont(fuenteBotones);
        bancoPanel.setLayout(new BorderLayout(15, 15));
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(saludoLabel, BorderLayout.NORTH);
        panelSuperior.add(saldoLabel, BorderLayout.CENTER);
        panelSuperior.setOpaque(false);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 15, 0));
        panelBotones.add(depositarButton);
        panelBotones.add(retirarButton);
        panelBotones.add(tranferirButton);
        panelBotones.add(salirButton);
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        bancoPanel.add(panelSuperior, BorderLayout.NORTH);
        bancoPanel.add(scrollPane, BorderLayout.CENTER);
        bancoPanel.add(panelBotones, BorderLayout.SOUTH);

        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarDeposito();
            }
        });
        retirarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarRetiro();

            }
        });
        tranferirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarTransferencia();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirPrograma();

            }
        });
    }

    private void personalizarBoton(JButton boton, String texto, Color colorFondo) {
        boton.setText(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorFondo.darker()),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
    }

    private void actualizarSaldo() {
        saldoLabel.setText(formatoMoneda.format(saldo));
    }
    private void agregarTransaccion(String transaccion) {
        String fecha = formatoFecha.format(new Date());
        historialArea.append("[" + fecha + "] " + transaccion + "\n");
        historialArea.setCaretPosition(historialArea.getDocument().getLength());
    }
    private void realizarDeposito() {
        String input = JOptionPane.showInputDialog(this,
                "Ingrese el monto a depositar:",
                "Depósito",
                JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                double monto = Double.parseDouble(input.trim());
                if (monto > 0) {
                    saldo += monto;
                    actualizarSaldo();
                    agregarTransaccion("Depósito: " + formatoMoneda.format(monto) +
                            " - Nuevo saldo: " + formatoMoneda.format(saldo));
                    JOptionPane.showMessageDialog(this,
                            "Depósito exitoso!\nMonto: " + formatoMoneda.format(monto) +
                                    "\nNuevo saldo: " + formatoMoneda.format(saldo),
                            "Depósito Exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El monto debe ser mayor a cero",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese un número válido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void realizarRetiro() {
        String input = JOptionPane.showInputDialog(this,
                "Ingrese el monto a retirar:\nSaldo disponible: " + formatoMoneda.format(saldo),
                "Retiro",
                JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                double monto = Double.parseDouble(input.trim());
                if (monto > 0) {
                    if (monto <= saldo) {
                        saldo -= monto;
                        actualizarSaldo();
                        agregarTransaccion("Retiro: " + formatoMoneda.format(monto) +
                                " - Nuevo saldo: " + formatoMoneda.format(saldo));
                        JOptionPane.showMessageDialog(this,
                                "Retiro exitoso!\nMonto: " + formatoMoneda.format(monto) +
                                        "\nNuevo saldo: " + formatoMoneda.format(saldo),
                                "Retiro Exitoso",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Saldo insuficiente!\nSaldo disponible: " + formatoMoneda.format(saldo) +
                                        "\nMonto solicitado: " + formatoMoneda.format(monto),
                                "Saldo Insuficiente",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El monto debe ser mayor a cero",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese un número válido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void salirPrograma() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea salir del sistema?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            agregarTransaccion("Cierre de sesión - Saldo final: " + formatoMoneda.format(saldo));
            System.exit(0);
        }
    }

    private void realizarTransferencia() {
        // Panel personalizado para transferencia
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel destinatarioLabel = new JLabel("Destinatario:");
        JTextField destinatarioField = new JTextField(20);
        JLabel montoLabel = new JLabel("Monto:");
        JTextField montoField = new JTextField(20);
        JLabel saldoDisponibleLabel = new JLabel("Saldo disponible: " + formatoMoneda.format(saldo));

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(destinatarioLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(destinatarioField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST; gbc.fill = GridBagConstraints.NONE;
        panel.add(montoLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(montoField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(saldoDisponibleLabel, gbc);

        int result = JOptionPane.showConfirmDialog(this, panel, "Transferencia",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String destinatario = destinatarioField.getText().trim();
            String montoStr = montoField.getText().trim();

            if (destinatario.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Debe ingresar el nombre del destinatario",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double monto = Double.parseDouble(montoStr);
                if (monto > 0) {
                    if (monto <= saldo) {
                        saldo -= monto;
                        actualizarSaldo();
                        agregarTransaccion("Transferencia a " + destinatario + ": " +
                                formatoMoneda.format(monto) +
                                " - Nuevo saldo: " + formatoMoneda.format(saldo));
                        JOptionPane.showMessageDialog(this,
                                "Transferencia exitosa a " + destinatario +
                                        " por " + formatoMoneda.format(monto) +
                                        "\nNuevo saldo: " + formatoMoneda.format(saldo),
                                "Transferencia Exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Saldo insuficiente!\nSaldo disponible: " + formatoMoneda.format(saldo) +
                                        "\nMonto solicitado: " + formatoMoneda.format(monto),
                                "Saldo Insuficiente",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El monto debe ser mayor a cero",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese un monto válido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

