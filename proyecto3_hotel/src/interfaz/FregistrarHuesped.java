package interfaz;

import javax.swing.*;
//import javax.swing.plaf.synth.SynthSplitPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import logica.*;

public class FregistrarHuesped extends JFrame implements ActionListener {
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Hotel hotel;
    CardLayout cardLayout;
    JPanel contentP;
    JLabel hab, fech, hues;
    int numHues = 0;
    public ArrayList<Huesped> huespedesRegistrados = new ArrayList<>();
    private JTextField txtNombre, txtIdentificacion, txtCorreo, txtTelefono, txtFechaNacimiento, txtFechaInicio,
            txtFechaSalida;
    private ArrayList<Habitacion> habitacionesRegistradas = new ArrayList<>();
    private String fechaLlegada, fechaSalida;
    JComboBox<String> cbHabitacion;

    public FregistrarHuesped(FHuesped fPrincipal, Hotel hotel) {
        super("Realizar reserva");
        this.hotel = hotel;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);
        JLabel titulo = new JLabel("Reserva");
        titulo.setFont(new Font("Georgia", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setOpaque(true);
        titulo.setBackground(fondo);
        add(titulo, BorderLayout.NORTH);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(fondo);
        JLabel labelFechas = new JLabel("Fechas de reserva");
        labelFechas.setFont(new Font("Georgia", Font.BOLD, 18));
        labelFechas.setForeground(Color.WHITE);
        labelFechas.setHorizontalAlignment(JLabel.CENTER);
        labelFechas.setOpaque(true);
        labelFechas.setBackground(fondo);
        panel.add(labelFechas);
        fech = new JLabel();
        fech.setForeground(Color.WHITE);
        panel.add(fech);
        JLabel labelNumHuespedes = new JLabel("Número de huéspedes");
        labelNumHuespedes.setFont(new Font("Georgia", Font.BOLD, 18));
        labelNumHuespedes.setForeground(Color.WHITE);
        labelNumHuespedes.setHorizontalAlignment(JLabel.CENTER);
        labelNumHuespedes.setOpaque(true);
        labelNumHuespedes.setBackground(fondo);
        panel.add(labelNumHuespedes);
        hues = new JLabel();
        hues.setForeground(Color.WHITE);
        panel.add(hues);
        JLabel labelTipoHabitacion = new JLabel("Numero de habitación");
        labelTipoHabitacion.setFont(new Font("Georgia", Font.BOLD, 18));
        labelTipoHabitacion.setForeground(Color.WHITE);
        labelTipoHabitacion.setHorizontalAlignment(JLabel.CENTER);
        labelTipoHabitacion.setOpaque(true);
        labelTipoHabitacion.setBackground(fondo);
        panel.add(labelTipoHabitacion);
        hab = new JLabel();
        hab.setForeground(Color.WHITE);
        panel.add(hab);

        add(panel, BorderLayout.SOUTH);

        cardLayout = new CardLayout();
        contentP = new JPanel(cardLayout);

        add(contentP, BorderLayout.CENTER);
        panelHabitaciones();
        registrarHuesped();

        cardLayout.show(contentP, "ventaHabitaciones"); // Mostrar inicialmente el panel de habitaciones

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void panelHabitaciones() {
        String llegada = "2021-05-01";
        String salida = "2021-05-05";

        JPanel panelHabitaciones = new JPanel();
        panelHabitaciones.setLayout(new BorderLayout());

        Recepcionista recepcionista = new Recepcionista();
        HashMap<Integer, Huesped> huespedes = hotel.getHuespedes();
        HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();
        HashMap<String, Integer> tarifasEstandar = hotel.getTarifasEstandar();
        HashMap<String, Integer> tarifasSuite = hotel.getTarifasSuite();
        HashMap<String, Integer> tarifasSuite2 = hotel.getTarifasSuite2();
        HashMap<Integer, Habitacion> info_habitaciones_disponibles = recepcionista.darCotizacion(
                llegada, salida, huespedes, habitaciones, tarifasEstandar, tarifasSuite, tarifasSuite2, recepcionista);

        Fdisponibles ventaHabitaciones = new Fdisponibles(info_habitaciones_disponibles);
        panelHabitaciones.add(ventaHabitaciones, BorderLayout.CENTER);

        JLabel lHabitacion = new JLabel("Habitación:");
        cbHabitacion = new JComboBox<>();
        cbHabitacion.addItem("Habitación 1");
        cbHabitacion.addItem("Habitación 2");
        cbHabitacion.addItem("Habitación 3");

        cbHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) cbHabitacion.getSelectedItem();
                if (seleccion != null) {
                    HashMap<Integer, Habitacion> habitacionesDis = recepcionista.habitaciones_disponibles(habitaciones);
                    System.out.println(habitacionesDis);
                    if (seleccion.equals("Habitación 1")) {
                        hab.setText("1201");
                        Habitacion habitacionEscogida = habitacionesDis.get(1201);
                        habitacionesRegistradas.add(habitacionEscogida);
                    } else if (seleccion.equals("Habitación 2")) {
                        hab.setText("201");
                        Habitacion habitacionEscogida = habitacionesDis.get(201);
                        habitacionesRegistradas.add(habitacionEscogida);
                    } else if (seleccion.equals("Habitación 3")) {
                        hab.setText("301");
                        Habitacion habitacionEscogida = habitacionesDis.get(301);
                        habitacionesRegistradas.add(habitacionEscogida);
                    }
                }
            }
        });

        JPanel panelchiquito = new JPanel();
        panelchiquito.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Ajusta las proporciones y el espaciado
        panelchiquito.add(lHabitacion);
        panelchiquito.add(cbHabitacion);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER)); // Alinea el botón al centro
        JButton btnAceptar = new JButton("ACEPTAR");
        btnAceptar.addActionListener(this);
        panelBoton.add(btnAceptar);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelchiquito, BorderLayout.NORTH);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        panelHabitaciones.add(panelPrincipal, BorderLayout.SOUTH);

        contentP.add(panelHabitaciones, "ventaHabitaciones");
    }

    public void registrarHuesped() {
        JPanel panelH = new JPanel(new GridLayout(8, 2, 10, 10)); // Configura el GridLayout con espaciado

        // Etiquetas
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblIdentificacion = new JLabel("Identificación:");
        JLabel lblCorreo = new JLabel("Correo:");
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        JLabel lblFechaInicio = new JLabel("Fecha de inicio:");
        JLabel lblFechaSalida = new JLabel("Fecha de salida:");

        // Campos de texto
        txtNombre = new JTextField(15);
        txtIdentificacion = new JTextField(15);
        txtCorreo = new JTextField(15);
        txtTelefono = new JTextField(15);
        txtFechaNacimiento = new JTextField(15);
        txtFechaInicio = new JTextField(15);
        txtFechaSalida = new JTextField(15);

        // Botones
        JButton btnAgregarHuesped = new JButton("AGREGARHUESPED");
        JButton btnTerminar = new JButton("TERMINAR");

        // Agregar etiquetas y campos de texto al panel
        panelH.add(lblNombre);
        panelH.add(txtNombre);
        panelH.add(lblIdentificacion);
        panelH.add(txtIdentificacion);
        panelH.add(lblCorreo);
        panelH.add(txtCorreo);
        panelH.add(lblTelefono);
        panelH.add(txtTelefono);
        panelH.add(lblFechaNacimiento);
        panelH.add(txtFechaNacimiento);
        panelH.add(lblFechaInicio);
        panelH.add(txtFechaInicio);
        panelH.add(lblFechaSalida);
        panelH.add(txtFechaSalida);

        // Alineación y tamaño de los componentes
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIdentificacion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaSalida.setHorizontalAlignment(SwingConstants.RIGHT);

        lblNombre.setPreferredSize(new Dimension(120, 25));
        lblIdentificacion.setPreferredSize(new Dimension(120, 25));
        lblCorreo.setPreferredSize(new Dimension(120, 25));
        lblTelefono.setPreferredSize(new Dimension(120, 25));
        lblFechaNacimiento.setPreferredSize(new Dimension(120, 25));
        lblFechaInicio.setPreferredSize(new Dimension(120, 25));
        lblFechaSalida.setPreferredSize(new Dimension(120, 25));

        // Agregar botones al panel
        panelH.add(btnAgregarHuesped);
        panelH.add(btnTerminar);

        // Acción del botón "AGREGARHUESPED"
        btnAgregarHuesped.addActionListener(this);

        // Acción del botón "TERMINAR"
        btnTerminar.addActionListener(this);

        contentP.add(panelH, "registrarHuesped");
    }

    public void pagoInmediato() {
        FfacturaReserva fregistrarHuesped = new FfacturaReserva(hotel, 401);

        contentP.add(fregistrarHuesped, "pagoInmediato");
        cardLayout.show(contentP, "pagoInmediato");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("ACEPTAR")) {
            if (hab.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una habitación");
            } else {
                cardLayout.show(contentP, "registrarHuesped");
            } // Mostrar el panel de registro de huéspedes
        } else if (comando.equals("AGREGARHUESPED")) {
            if (txtNombre.getText().equals("") || txtIdentificacion.getText().equals("")
                    || txtCorreo.getText().equals("") || txtTelefono.getText().equals("")
                    || txtFechaNacimiento.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos");
            } else {
                numHues += 1;
                hues.setText(numHues + "");
            }

            fech.setText(txtFechaInicio.getText() + " - " + txtFechaSalida.getText());

            String nombre = txtNombre.getText();
            String identificacion = txtIdentificacion.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();
            String fechaNacimiento = txtFechaNacimiento.getText();

            txtNombre.setText(""); // Limpiar el campo de texto
            txtIdentificacion.setText("");
            txtCorreo.setText("");
            txtTelefono.setText("");
            txtFechaNacimiento.setText("");

            HashMap<String, String> datos = new HashMap<>();
            datos.put("nombre", nombre);
            datos.put("identificacion", identificacion);
            datos.put("correo", correo);
            datos.put("telefono", telefono);
            datos.put("fechaNacimiento", fechaNacimiento);
            Huesped huesped = new Huesped(nombre, Integer.parseInt(identificacion), correo, telefono,
                    fechaNacimiento);
            huespedesRegistrados.add(huesped);

        } else if (comando.equals("TERMINAR")) {
            fechaLlegada = txtFechaInicio.getText();
            fechaSalida = txtFechaSalida.getText();
            Recepcionista recepcionista = new Recepcionista();
            HashMap<Integer, Huesped> huespedes = hotel.getHuespedes();
            HashMap<Integer, reserva> reservas = hotel.getReservas();
            HashMap<String, Integer> tarifasEstandar = hotel.getTarifasEstandar();
            HashMap<String, Integer> tarifasSuite = hotel.getTarifasSuite();
            HashMap<String, Integer> tarifasSuite2 = hotel.getTarifasSuite2();
            HashMap<Integer, Grupo> grupos = hotel.getGrupos();
            HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();

            //recepcionista.iniciarReserva(huespedes, reservas, habitaciones,
              //      recepcionista, tarifasEstandar,
                //    tarifasSuite,
                  //  tarifasSuite2, grupos, habitacionesRegistradas, huespedesRegistrados, fechaLlegada,
                    //fechaSalida, hotel);

            JOptionPane.showMessageDialog(null, "Reserva creada con exito", "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
            // preguntar si paga inmediatamente y mostrar la parte de pago con tarjeta
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea pagar de inmediato? Su monto es de $95000",
                    "Pago Inmediato",
                    JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                pagoInmediato(); // Mostrar el panel de pago inmediato
            } else {
                JOptionPane.showMessageDialog(null, "Gracias por reservar", "Reserva Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cerrar la ventana de reserva
            }
        }
    }
}
