package FormularioTaller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Docentes extends JFrame {
    private JTextField textNombre;
    private JTextField textCarrera;
    private JTextField textMateria;
    private JButton limpiarButton;
    private JButton regresarButton;
    private JPanel panelDocent;

    public Docentes() {
        setTitle("Docentes");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelDocent);
        setVisible(true);

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textNombre.setText("");
                textCarrera.setText("");
                textMateria.setText("");
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              FormularioTaller frame = new FormularioTaller();
              dispose();
              frame.setSize(500, 300);
              frame.setVisible(true);

            }
        });
    }


}
