package Formulario3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCompras extends JFrame {
    private JComboBox<String> comboProductos;
    private JTextField txtCantidad;
    private JLabel lblPrecioUnitario, lblSubtotal, lblIVA, lblDescuento, lblTotal;
    private JTable tablaCompras;
    private DefaultTableModel modeloTabla;

    public FormularioCompras() {
        // Configuración básica del formulario
        setTitle("Sistema de Compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        // Panel superior para los controles de compra
        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 5, 5));

        // Combo box con productos
        String[] productos = {"Martillo - $10.00", "Clavos - $3.50", "Pintura Blanca - $15.00", "Taladro - $50.00"};
        comboProductos = new JComboBox<>(productos);
        panelSuperior.add(new JLabel("Producto:"));
        panelSuperior.add(comboProductos);

        // Campo para cantidad
        panelSuperior.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelSuperior.add(txtCantidad);

        // Etiquetas para mostrar los cálculos
        panelSuperior.add(new JLabel("Precio Unitario:"));
        lblPrecioUnitario = new JLabel("$0.00");
        panelSuperior.add(lblPrecioUnitario);

        panelSuperior.add(new JLabel("Subtotal:"));
        lblSubtotal = new JLabel("$0.00");
        panelSuperior.add(lblSubtotal);

        panelSuperior.add(new JLabel("IVA (15%):"));
        lblIVA = new JLabel("$0.00");
        panelSuperior.add(lblIVA);

        panelSuperior.add(new JLabel("Descuento (20%):"));
        lblDescuento = new JLabel("$0.00");
        panelSuperior.add(lblDescuento);

        panelSuperior.add(new JLabel("Total a Pagar:"));
        lblTotal = new JLabel("$0.00");
        panelSuperior.add(lblTotal);

        // Panel central para la tabla de compras
        String[] columnas = {"Producto", "Cantidad", "Precio", "Subtotal", "IVA", "Descuento", "Total"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCompras = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCompras);

        // Panel inferior para los botones
        JPanel panelInferior = new JPanel();
        JButton btnPagar = new JButton("Pagar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelInferior.add(btnPagar);
        panelInferior.add(btnLimpiar);

        // Agregar componentes al formulario
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        // Obtener referencia al botón Pagar
        JButton btnPagar = (JButton) ((JPanel) getContentPane().getComponent(2)).getComponent(0);

        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularFactura();
            }
        });

        // Obtener referencia al botón Limpiar
        JButton btnLimpiar = (JButton) ((JPanel) getContentPane().getComponent(2)).getComponent(1);

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });
    }

    private void calcularFactura() {
        try {
            // Obtener datos del formulario
            String productoSeleccionado = (String) comboProductos.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            // Extraer el precio del producto seleccionado
            String[] partes = productoSeleccionado.split(" - \\$");
            String nombreProducto = partes[0];
            double precioUnitario = Double.parseDouble(partes[1]);

            // Calcular valores
            double subtotal = precioUnitario * cantidad;
            double iva = subtotal * 0.15;
            double descuento = subtotal * 0.20;
            double total = subtotal + iva - descuento;

            // Actualizar etiquetas
            lblPrecioUnitario.setText(String.format("$%.2f", precioUnitario));
            lblSubtotal.setText(String.format("$%.2f", subtotal));
            lblIVA.setText(String.format("$%.2f", iva));
            lblDescuento.setText(String.format("$%.2f", descuento));
            lblTotal.setText(String.format("$%.2f", total));

            // Agregar valores a la tabla
            Object[] fila = {
                    nombreProducto,
                    cantidad,
                    String.format("$%.2f", precioUnitario),
                    String.format("$%.2f", subtotal),
                    String.format("$%.2f", iva),
                    String.format("$%.2f", descuento),
                    String.format("$%.2f", total)
            };
            modeloTabla.addRow(fila);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtCantidad.setText("");
        lblPrecioUnitario.setText("$0.00");
        lblSubtotal.setText("$0.00");
        lblIVA.setText("$0.00");
        lblDescuento.setText("$0.00");
        lblTotal.setText("$0.00");
        modeloTabla.setRowCount(0); // Limpiar tabla
    }
}