package Formulario3;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                crearYMostrarGUI();
            }
        });
    }

    private static void crearYMostrarGUI() {
        // Crear y configurar la ventana
        FormularioCompras formulario = new FormularioCompras();
        formulario.setLocationRelativeTo(null); // Centrar  pantalla
        formulario.setVisible(true);
    }
}