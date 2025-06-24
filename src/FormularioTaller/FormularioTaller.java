package FormularioTaller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioTaller extends JFrame {
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textCarrera;
    private JTextField textNota1;
    private JTextField textNota2;
    private JTextField textNota3;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JPanel panelNotas;
    private JButton registrarDocenteButton;

    public FormularioTaller()  {
        setTitle("Formulario Notas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelNotas);
        setLocationRelativeTo(null);
        setVisible(true);

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textNombre.setText("");
                textApellido.setText("");
                textCarrera.setText("");
                textNota1.setText("");
                textNota2.setText("");
                textNota3.setText("");
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double nota1 = Double.parseDouble(textNota1.getText());
                double nota2 = Double.parseDouble(textNota2.getText());
                double nota3 = Double.parseDouble(textNota3.getText());
                double promedio = (nota1 + nota2 + nota3) / 3;

                if (nota1 < 0 || nota2 < 0 || nota3 < 0) {
                    JOptionPane.showMessageDialog(null, "El promedio es negado", "Error", JOptionPane.ERROR_MESSAGE);
                }else  if (nota1 > 20 || nota2 > 20 || nota3 > 20) {
                    JOptionPane.showMessageDialog(null, "Sobrepasa el 20", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "El Promedio es: "+ promedio);
                }


            }
        });
        registrarDocenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Docentes docentes = new Docentes();
                dispose();
                docentes.setSize(500, 300);
                docentes.setVisible(true);
            }
        });
    }
}
