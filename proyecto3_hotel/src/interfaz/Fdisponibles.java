package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Set;

import logica.*;

public class Fdisponibles extends JPanel implements ActionListener {
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private HashMap<Integer, Habitacion> habitaciones;
    private JPanel panel;
    // private Hotel hotel;
    private FHuesped fHuesped;

    public Fdisponibles(HashMap<Integer, Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        if (this.habitaciones.size() > 0) {
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(fondo);

            JLabel titulo = new JLabel("Habitaciones disponibles");
            titulo.setFont(new Font("Georgia", Font.BOLD, 24));
            titulo.setForeground(Color.WHITE);
            titulo.setHorizontalAlignment(JLabel.CENTER);
            mainPanel.add(titulo, BorderLayout.NORTH);

            this.panel = new JPanel(new GridLayout(1, 4, 10, 10));
            panel.setBackground(fondo);

            infoHabitacion(this.habitaciones);

            mainPanel.add(panel, BorderLayout.CENTER);
            // Agrega espacio vacío de color de fondo a los lados
            int emptySpaceSize = 50;
            JPanel emptyPanelLeft = new JPanel();
            emptyPanelLeft.setBackground(fondo);
            emptyPanelLeft.setPreferredSize(new Dimension(emptySpaceSize, 1));

            JPanel emptyPanelRight = new JPanel();
            emptyPanelRight.setBackground(fondo);
            emptyPanelRight.setPreferredSize(new Dimension(emptySpaceSize, 1));

            add(emptyPanelLeft, BorderLayout.WEST);
            add(mainPanel, BorderLayout.CENTER);
            add(emptyPanelRight, BorderLayout.EAST);
        }

        setVisible(true);
    }

    public void infoHabitacion(HashMap<Integer, Habitacion> habitaciones) {
        for (Object k : habitaciones.keySet()) {
            Habitacion habitacion = habitaciones.get(k);
            JPanel pHabitacion = new JPanel(new GridLayout(3, 1, 10, 10));
            pHabitacion.setBackground(fondo);
            pHabitacion.setBorder(BorderFactory.createLineBorder(Color.white, 2));

            JLabel nlabel = new JLabel("Número: " + habitacion.getNumero());
            nlabel.setFont(new Font("Georgia", Font.BOLD, 14));
            nlabel.setForeground(Color.WHITE);
            nlabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel cLabel = new JLabel("Capacidad: " + habitacion.getCapacidad());
            cLabel.setFont(new Font("Georgia", Font.BOLD, 14));
            cLabel.setForeground(Color.WHITE);
            cLabel.setHorizontalAlignment(JLabel.CENTER);

            pHabitacion.add(nlabel);
            pHabitacion.add(cLabel);

            JLabel tLabel = new JLabel();
            if (habitacion instanceof Estandar) {
                tLabel.setText("Tipo: Estandar");
            } else if (habitacion instanceof Suite) {
                tLabel.setText("Tipo: Suite");
            } else if (habitacion instanceof Suite_doble) {
                tLabel.setText("Tipo: Suite Doble");
            }
            tLabel.setFont(new Font("Georgia", Font.BOLD, 14));
            tLabel.setForeground(Color.WHITE);
            tLabel.setHorizontalAlignment(JLabel.CENTER);
            pHabitacion.add(tLabel);

            this.panel.add(pHabitacion);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("volver")) {
            setVisible(false);
            fHuesped.setVisible(true);
        }

    }
}
