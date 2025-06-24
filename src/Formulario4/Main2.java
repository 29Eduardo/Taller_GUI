package Formulario4;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swin
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                crearYMostrarGUI();
            }
        });
    }

    private static void crearYMostrarGUI() {
        // Crear y configurar la ventana
        FormularioRegistro formulario = new FormularioRegistro();
        formulario.setLocationRelativeTo(null); // Centrar en la pantalla
        formulario.setVisible(true);
    }
}