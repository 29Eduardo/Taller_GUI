package Formulario3y4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioRegistro extends JFrame {
    private JTextField txtNombre, txtApellido, txtEdad, txtTelefono;
    private JRadioButton rbHombre, rbMujer;
    private JCheckBox cbFutbol, cbBasquet, cbTenis, cbNatacion;
    private JComboBox<String> cbBarrio;
    private JTable tablaRegistros;
    private DefaultTableModel modeloTabla;

    public FormularioRegistro() {
        // Configuración básica del formulario
        setTitle("Registro de Personas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        // Panel principal con color de fondo
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(Color.LIGHT_GRAY);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de datos personales (arriba)
        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 5, 5));
        panelDatos.setBackground(Color.CYAN);
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos Personales"));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtEdad = new JTextField();
        txtTelefono = new JTextField();

        panelDatos.add(crearEtiqueta("Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(crearEtiqueta("Apellido:"));
        panelDatos.add(txtApellido);
        panelDatos.add(crearEtiqueta("Edad:"));
        panelDatos.add(txtEdad);
        panelDatos.add(crearEtiqueta("Teléfono:"));
        panelDatos.add(txtTelefono);

        // Panel de géner
        JPanel panelGenero = new JPanel(new GridLayout(2, 1));
        panelGenero.setBackground(Color.PINK);
        panelGenero.setBorder(BorderFactory.createTitledBorder("Género"));

        rbHombre = new JRadioButton("Hombre");
        rbMujer = new JRadioButton("Mujer");
        ButtonGroup grupoGenero = new ButtonGroup();
        grupoGenero.add(rbHombre);
        grupoGenero.add(rbMujer);

        panelGenero.add(rbHombre);
        panelGenero.add(rbMujer);

        // Panel de deportes (centro derecha)
        JPanel panelDeportes = new JPanel(new GridLayout(4, 1));
        panelDeportes.setBackground(Color.GREEN);
        panelDeportes.setBorder(BorderFactory.createTitledBorder("Deportes Favoritos"));

        cbFutbol = new JCheckBox("Fútbol");
        cbBasquet = new JCheckBox("Básquet");
        cbTenis = new JCheckBox("Tenis");
        cbNatacion = new JCheckBox("Natación");

        panelDeportes.add(cbFutbol);
        panelDeportes.add(cbBasquet);
        panelDeportes.add(cbTenis);
        panelDeportes.add(cbNatacion);

        // Panel de barrio (abajo izquierda)
        JPanel panelBarrio = new JPanel();
        panelBarrio.setBackground(Color.YELLOW);
        panelBarrio.setBorder(BorderFactory.createTitledBorder("Barrio de Residencia"));

        String[] barrios = {"La Floresta", "Chillogallo", "Carcelén", "El Condado"};
        cbBarrio = new JComboBox<>(barrios);
        panelBarrio.add(cbBarrio);

        // Panel central para género, deportes y barrio
        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 10));
        panelCentral.add(panelGenero);
        panelCentral.add(panelDeportes);
        panelCentral.add(panelBarrio);

        // Panel de botones (abajo derecha)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(Color.ORANGE);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(Color.BLUE);
        btnRegistrar.setForeground(Color.WHITE);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(Color.RED);
        btnLimpiar.setForeground(Color.WHITE);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);

        // Panel inferior que combina barrio y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelBarrio, BorderLayout.WEST);
        panelInferior.add(panelBotones, BorderLayout.EAST);

        // Configurar tabla de registros
        String[] columnas = {"Nombre", "Apellido", "Edad", "Teléfono", "Género", "Deportes", "Barrio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaRegistros = new JTable(modeloTabla);
        tablaRegistros.setBackground(Color.WHITE);
        tablaRegistros.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(tablaRegistros);

        // Organizar los paneles en el formulario
        panelPrincipal.add(panelDatos, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.BLACK);
        return label;
    }

    private void configurarEventos() {
        // Obtener referencia a los botones
        JButton btnRegistrar = (JButton) ((JPanel) ((JPanel) getContentPane().getComponent(0))
                .getComponent(2)).getComponent(1);

        JButton btnLimpiar = (JButton) ((JPanel) ((JPanel) getContentPane().getComponent(0))
                .getComponent(2)).getComponent(1).getParent().getComponent(1);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDatos();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });
    }

    private void registrarDatos() {
        // Validar campos obligatorios
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y Apellido son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener datos del formulario
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edad = txtEdad.getText();
        String telefono = txtTelefono.getText();

        String genero = rbHombre.isSelected() ? "Hombre" :
                rbMujer.isSelected() ? "Mujer" : "No especificado";

        StringBuilder deportes = new StringBuilder();
        if (cbFutbol.isSelected()) deportes.append("Fútbol, ");
        if (cbBasquet.isSelected()) deportes.append("Básquet, ");
        if (cbTenis.isSelected()) deportes.append("Tenis, ");
        if (cbNatacion.isSelected()) deportes.append("Natación, ");

        String deportesStr = deportes.toString();
        if (deportesStr.endsWith(", ")) {
            deportesStr = deportesStr.substring(0, deportesStr.length() - 2);
        }
        if (deportesStr.isEmpty()) deportesStr = "Ninguno";

        String barrio = (String) cbBarrio.getSelectedItem();

        // Agregar a la tabla
        Object[] fila = {nombre, apellido, edad, telefono, genero, deportesStr, barrio};
        modeloTabla.addRow(fila);

        JOptionPane.showMessageDialog(this, "Registro exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");

        rbHombre.setSelected(false);
        rbMujer.setSelected(false);

        cbFutbol.setSelected(false);
        cbBasquet.setSelected(false);
        cbTenis.setSelected(false);
        cbNatacion.setSelected(false);

        cbBarrio.setSelectedIndex(0);
    }
}