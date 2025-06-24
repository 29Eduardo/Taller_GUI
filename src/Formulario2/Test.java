package Formulario2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    private JPanel Panel;
    private JRadioButton opcion1A;
    private JRadioButton opcion1B;
    private JRadioButton opcion1C;
    private JRadioButton opcion2A;
    private JRadioButton opcion2B;
    private JRadioButton opcion2C;
    private JRadioButton verdaderoRadio;
    private JRadioButton falsoRadio;
    private JButton verResultadoButton;
    private JButton inactivarButton;
    private JButton limpiarButton;
    private JLabel resultadoLabel;

    public Test() {
        ButtonGroup grupo1 = new ButtonGroup();
        grupo1.add(opcion1A);
        grupo1.add(opcion1B);
        grupo1.add(opcion1C);

        ButtonGroup grupo2 = new ButtonGroup();
        grupo2.add(opcion2A);
        grupo2.add(opcion2B);
        grupo2.add(opcion2C);

        ButtonGroup grupo3 = new ButtonGroup();
        grupo3.add(verdaderoRadio);
        grupo3.add(falsoRadio);

        verResultadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int puntaje = 0;
                if (opcion1B.isSelected()) puntaje += 5;
                if (opcion2C.isSelected()) puntaje += 5;
                if (verdaderoRadio.isSelected()) puntaje += 5;
                resultadoLabel.setText("Puntaje total: " + puntaje + " / 15");
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcion1A.setEnabled(false);
                opcion1B.setEnabled(false);
                opcion1C.setEnabled(false);
                opcion2A.setEnabled(false);
                opcion2B.setEnabled(false);
                opcion2C.setEnabled(false);
                verdaderoRadio.setEnabled(false);
                falsoRadio.setEnabled(false);
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grupo1.clearSelection();
                grupo2.clearSelection();
                grupo3.clearSelection();

                opcion1A.setEnabled(true);
                opcion1B.setEnabled(true);
                opcion1C.setEnabled(true);
                opcion2A.setEnabled(true);
                opcion2B.setEnabled(true);
                opcion2C.setEnabled(true);
                verdaderoRadio.setEnabled(true);
                falsoRadio.setEnabled(true);

                resultadoLabel.setText("");
            }
        });
    }

    public JPanel getPanel() {
        return Panel;}
}