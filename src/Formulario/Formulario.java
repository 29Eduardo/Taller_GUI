package Formulario;

import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;

public class Formulario extends JFrame{
    private JPanel ventana;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textTelef;
    private JTextField textDirec;
    private JTextField textAnio;
    private JTextField textEstatu;
    private JButton accionButton1;
    private JButton accionButton2;
    private JLabel textDato;

    public Formulario()  {
        setTitle("Mi Formulario");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(ventana);
        ventana.setBackground(Color.DARK_GRAY);
        setVisible(true);


        accionButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datos = mostrarDatos();
                JOptionPane.showMessageDialog(null,datos,"Datos Ingresados",JOptionPane.INFORMATION_MESSAGE);

            }
        });
        accionButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datos = mostrarDatos();
                textDato.setText("<html>" + datos.replaceAll("\n", "<br>") + "</html>");

            }
        });
    }

    private String mostrarDatos(){
                return  "Nombre: " + textNombre.getText() + "\n" +
                "Apellido: " + textApellido.getText() + "\n" +
                "Dirección: " + textDirec.getText() + "\n" +
                "Teléfono: " + textTelef.getText() + "\n" +
                "Año de nacimiento: " + textAnio.getText() + "\n" +
                "Estatura: " + textEstatu.getText() + " metros";
    }

    public static class Main {
        public static void main(String[] args) {
            new Formulario();
            }
    }
}
