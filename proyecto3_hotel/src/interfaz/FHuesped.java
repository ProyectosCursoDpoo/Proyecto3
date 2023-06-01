package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import logica.*;

public class FHuesped extends JPanel implements ActionListener {
    FPrincipalHuesped principal;
    public Hotel hotel;
    private JTextField fechaInicio;
    private JTextField fechaFin;
    private Recepcionista recepcionista;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Color fondo = new Color(28, 35, 46);

    public FHuesped(Hotel hotel, FPrincipalHuesped fPrincipal) {
        this.principal = fPrincipal;
        this.hotel = hotel;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);
        setBackground(fondo);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(fondo);

        JPanel info = new JPanel(new GridLayout(3, 3, 10, 10));
        info.setBackground(fondo);
        
        JLabel labelLlegada = new JLabel("Fecha de inicio");
        labelLlegada.setFont(new Font("Georgia", Font.BOLD, 18));
        labelLlegada.setForeground(Color.WHITE);
        labelLlegada.setHorizontalAlignment(JLabel.CENTER);
        labelLlegada.setOpaque(true);
        labelLlegada.setBackground(fondo);

        fechaInicio = new JTextField();
        fechaInicio.setFont(new Font("Georgia", Font.BOLD, 18));
        fechaInicio.setBackground(fondo);
        fechaInicio.setForeground(Color.WHITE);
        fechaInicio.setHorizontalAlignment(JLabel.CENTER);

        JLabel labelSalida = new JLabel("Fecha de salida");
        labelSalida.setFont(new Font("Georgia", Font.BOLD, 18));
        labelSalida.setForeground(Color.WHITE);
        labelSalida.setHorizontalAlignment(JLabel.CENTER);
        labelSalida.setOpaque(true);
        labelSalida.setBackground(fondo);
        
        fechaFin = new JTextField();
        fechaFin.setFont(new Font("Georgia", Font.BOLD, 18));
        fechaFin.setBackground(fondo);
        fechaFin.setForeground(Color.WHITE);
        fechaFin.setHorizontalAlignment(JLabel.CENTER);

        JLabel labelImportante = new JLabel("Formato de fecha: mm/dd/aaaa Ej: 01/01/2021");
        labelImportante.setFont(new Font("Georgia", Font.BOLD, 18));
        labelImportante.setForeground(Color.WHITE);
        labelImportante.setHorizontalAlignment(JLabel.CENTER);
        labelImportante.setOpaque(true);
        labelImportante.setBackground(fondo);

        info.add(labelLlegada);
        info.add(labelSalida);
        info.add(fechaInicio);
        info.add(fechaFin);
        info.add(labelImportante);

        top.add(info, BorderLayout.CENTER);

        JButton bEnviar = new JButton("Enviar");
        bEnviar.setFont(new Font("Georgia", Font.BOLD, 18));
        bEnviar.setBackground(fondo);
        bEnviar.setForeground(Color.WHITE);
        bEnviar.addActionListener(this);
        bEnviar.setActionCommand("enviar");

        panel.add(bEnviar);
        top.add(panel, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Ingrese las fechas de su estadía");
        titulo.setBackground(fondo);
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 60));
        top.add(titulo, BorderLayout.NORTH);
        top.setBackground(fondo);

        add(top, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel2.setBackground(fondo);

        JButton bVolver = new JButton("Volver");
        bVolver.setFont(new Font("Georgia", Font.BOLD, 18));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.WHITE);
        bVolver.addActionListener(this);
        bVolver.setActionCommand("volver");

        JButton bIniciarR = new JButton("Iniciar Reserva");
        bIniciarR.setFont(new Font("Georgia", Font.BOLD, 18));
        bIniciarR.addActionListener(this);

        panel2.add(bVolver);
        panel2.add(bIniciarR);

        add(panel2, BorderLayout.SOUTH);

        setBackground(fondo);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("enviar")) {
            String llegada = fechaInicio.getText();
            String salida = fechaFin.getText();
            if (llegada.equals("") || salida.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                recepcionista = new Recepcionista();
                HashMap<Integer, Huesped> huespedes = hotel.getHuespedes();
                HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();
                HashMap<String, Integer> tarifasEstandar = hotel.getTarifasEstandar();
                HashMap<String, Integer> tarifasSuite = hotel.getTarifasSuite();
                HashMap<String, Integer> tarifasSuite2 = hotel.getTarifasSuite2();
                HashMap<Integer, Habitacion> info_habitaciones_disponibles = recepcionista.darCotizacion(llegada, salida, huespedes, habitaciones, tarifasEstandar, tarifasSuite,
                        tarifasSuite2, recepcionista);

                Fdisponibles ventaHabitaciones = new Fdisponibles(info_habitaciones_disponibles);

                add(ventaHabitaciones, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        } else if (comando.equals("volver")) {
            principal.setVisible(true);
        } else if (comando.equals("Iniciar Reserva")){
            setVisible(false);
            FregistrarHuesped registro = new FregistrarHuesped(this, hotel);
            registro.setVisible(true);
        }

    } 
    
}


