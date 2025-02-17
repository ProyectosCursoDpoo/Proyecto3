package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logica.*;

public class FfacturaReserva extends JFrame implements ActionListener {
    Recepcionista recepcionista;
    Staff staff;
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JButton bVolver;
    JButton bPagar;
    JPanel panelFactura;
    JTextArea areaTexto;
    JScrollPane scrollPane;
    double total;
    private int id;
    private Hotel hotel;

    public FfacturaReserva(Hotel hotel, int id) {
        super("Factura Reserva");
        this.hotel = hotel;
        this.id = id;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        this.panelFactura = new JPanel();
        panelFactura.setBackground(fondo);

        JLabel factura = new JLabel("Factura");
        factura.setFont(new java.awt.Font("Georgia", 1, 40));
        factura.setForeground(Color.white);
        panelFactura.add(factura);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setBackground(fondo);

        this.bVolver = new JButton("Volver");
        bVolver.setFont(new java.awt.Font("Georgia", 1, 40));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.white);
        bVolver.addActionListener(this);
        bVolver.setActionCommand("volver");

        this.bPagar = new JButton("Pagar");
        bPagar.setFont(new java.awt.Font("Georgia", 1, 40));
        bPagar.setBackground(fondo);
        bPagar.setForeground(Color.white);
        bPagar.addActionListener(this);
        bPagar.setActionCommand("pagar");

        panelBotones.add(bVolver);
        panelBotones.add(bPagar);

        factura();

        panelFactura.add(factura);

        add(panelFactura, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.SOUTH);

    }

    public void factura() {
        recepcionista = new Recepcionista();
        staff = new Staff();
        HashMap<Integer, reserva> reservas = hotel.getReservas();
        HashMap<Integer, Consumo> consumos = hotel.getConsumos();
        HashMap<Integer, Habitacion> habitaciones = hotel.getHabitaciones();
        HashMap<Integer, Grupo> grupos = hotel.getGrupos();
        String info = recepcionista.registrarSalida(id, reservas, staff, consumos, habitaciones, this.hotel, grupos);
        this.total = recepcionista.getTotal();

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 400);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setText(info);
        areaTexto.setBounds(20, 50, 300, 200);

        scrollPane.setViewportView(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("volver")) {
            this.dispose();
        } else if (comando.equals("pagar")) {

            String opc = JOptionPane.showInputDialog(
                    "1. Pasarela de pago Payu\n2. Pasarela de pago Paypal\n3. Pasarela de pago ApplePay", "1");
            if (opc.equals("1")) {
                HashMap<String, TarjetaPayU> tarjetasPayU = hotel.getTarjetasPayU();
                FpayU payu = new FpayU(this.total, tarjetasPayU);
                payu.setVisible(true);

                String texto = "";
                try {
                    BufferedReader br = new BufferedReader(new FileReader(
                            new File("../Proyecto3/proyecto3_hotel/data/historial_pagos/payu.txt")));
                    String linea = br.readLine();
                    while (linea != null) {
                        texto += linea + "\n";
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }

                try (
                        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                                "../Proyecto3/proyecto3_hotel/data/historial_pagos/payu.txt")))) {
                    String cadena = "";
                    cadena += texto;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    cadena += "Fecha: " + date + ";";
                    cadena += "Total: " + this.total + ";";
                    cadena += "Numero reserva: " + this.id + "\n";

                    bw.write(cadena);
                    bw.close();
                } catch (IOException ex) {

                    ex.printStackTrace();
                }

                // hotel.logOut();

                this.dispose();
            } else if (opc.equals("2")) {
                HashMap<String, TarjetaPayPal> tarjetasPaypal = hotel.getTarjetasPayPal();
                // System.out.println(tarjetasPaypal);
                // FpayPal paypal = new FpayPal(this.total, tarjetasPaypal);
                FramePal paypal = new FramePal(this.total, tarjetasPaypal);
                paypal.setVisible(true);
                try {
                    this.add(paypal);

                } catch (Exception exx) {
                    this.add(paypal);
                    System.out.println("Ojo");
                }
                String texto = "";
                try {
                    BufferedReader br = new BufferedReader(new FileReader(
                            new File("../Proyecto3/proyecto3_hotel/data/historial_pagos/paypal.txt")));
                    String linea = br.readLine();
                    while (linea != null) {
                        texto += linea + "\n";
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }

                try (
                        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                                "../Proyecto3/proyecto3_hotel/data/historial_pagos/paypal.txt")))) {
                    String cadena = "";
                    cadena += texto;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    cadena += "Fecha: " + date + ";";
                    cadena += "Total: " + this.total + ";";
                    cadena += "Numero reserva: " + this.id + "\n";

                    bw.write(cadena);
                    bw.close();
                } catch (IOException ex) {

                    ex.printStackTrace();
                }

                // hotel.logOut();

                this.dispose();
            } else if (opc.equals("3")) {
                HashMap<String, TarjetaApplePay> tarjetasApplePay = hotel.getTarjetasApplePay();
                FApplePay applePay = new FApplePay(this.total, tarjetasApplePay);
                applePay.setVisible(true);

                String texto = "";
                try {
                    BufferedReader br = new BufferedReader(new FileReader(
                            new File("../Proyecto3/proyecto3_hotel/data/historial_pagos/applepay.txt")));
                    String linea = br.readLine();
                    while (linea != null) {
                        texto += linea + "\n";
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }

                try (
                        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                                "../Proyecto3/proyecto3_hotel/data/historial_pagos/applepay.txt")))) {
                    String cadena = "";
                    cadena += texto;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    cadena += "Fecha: " + date + ";";
                    cadena += "Total: " + this.total + ";";
                    cadena += "Numero reserva: " + this.id + "\n";

                    bw.write(cadena);
                    bw.close();
                } catch (IOException ex) {

                    ex.printStackTrace();
                }

                // hotel.logOut();

                this.dispose();
            }

        }
    }
}
