package Formulario1;

import javax.swing.*;

import java.awt.event.*;
import java.time.Year;

public class Encuesta {
    private JPanel panel1;
    private JTextField nombreText;
    private JTextField apellidoText;
    private JTextField anioText;
    private JRadioButton hombreRadio;
    private JRadioButton mujerRadio;
    private JButton verificarEdadButton;
    private JButton limpiarButton;
    private JLabel resultadoLabel;

    public Encuesta(){
        ButtonGroup generoGroup = new ButtonGroup();
        generoGroup.add(hombreRadio);
        generoGroup.add(mujerRadio);
        verificarEdadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText().trim();
                String apellido = apellidoText.getText().trim();
                String anioStr = anioText.getText().trim();
                if (nombre.isEmpty() || apellido.isEmpty() || anioStr.isEmpty()) {
                    resultadoLabel.setText("Por favor complete todos los campos.");
                    return;
                }

                try {
                    int anioNacimiento = Integer.parseInt(anioStr);
                    int edad = Year.now().getValue() - anioNacimiento;
                    String genero = hombreRadio.isSelected() ? "Hombre" : mujerRadio.isSelected() ? "Mujer" : "";

                    if (genero.isEmpty()) {
                        resultadoLabel.setText("Seleccione un género.");
                        return;
                    }

                    String resultado = "Nombre: " + nombre + " " + apellido + "<br/>Edad: " + edad + " años → ";
                    resultado += edad >= 18 ? "<b>Mayor de edad</b>" : "<b>Menor de edad</b>";
                    resultadoLabel.setText("<html>" + resultado + "</html>");
                } catch (NumberFormatException ex) {
                    resultadoLabel.setText("Año de nacimiento inválido.");
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreText.setText("");
                apellidoText.setText("");
                anioText.setText("");
                resultadoLabel.setText("");
                generoGroup.clearSelection();

            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }

}
