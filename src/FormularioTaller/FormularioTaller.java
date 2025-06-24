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

            }
        });
    }
}
