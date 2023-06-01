package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.ApplePay;

import logica.TarjetaApplePay;

public class FApplePay extends JFrame implements ActionListener {
    private double total;
    // private Frecep frecep;
    private boolean pago;
    private HashMap<String, TarjetaApplePay> tarjetas;
    Color fondo = new Color(28, 35, 46);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JTextField tNombre;
    JTextField tNumTarjeta;
    JTextField tCVV;
    JTextField tfechaVen;
    JTextField tToken;

    public FApplePay(double total, HashMap<String, TarjetaApplePay> tarjetas) {
        this.total = total;
        this.tarjetas = tarjetas;
        inicializar();
    }

    public void inicializar() {
        setLayout(new BorderLayout());
        setSize(screenSize.width - 50, screenSize.height - 80);

        JPanel panel1 = new JPanel(new GridLayout(1, 1, 10, 10));
        panel1.setBackground(fondo);

        JLabel titulo = new JLabel("ApplePay");
        titulo.setFont(new Font("Georgia", Font.BOLD, 60));
        titulo.setBackground(fondo);
        titulo.setForeground(Color.white);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        panel1.add(titulo);
        add(panel1, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBackground(fondo);

        JLabel lNombre = new JLabel("Nombre propietario: ");
        lNombre.setFont(new Font("Georgia", Font.BOLD, 40));
        lNombre.setBackground(fondo);
        lNombre.setForeground(Color.white);
        lNombre.setHorizontalAlignment(JLabel.CENTER);

        this.tNombre = new JTextField();
        tNombre.setFont(new Font("Georgia", Font.BOLD, 40));
        tNombre.setBackground(fondo);
        tNombre.setForeground(Color.white);
        tNombre.setHorizontalAlignment(JLabel.CENTER);

        JLabel lNumTarjeta = new JLabel("NumTarjeta: ");
        lNumTarjeta.setFont(new Font("Georgia", Font.BOLD, 40));
        lNumTarjeta.setBackground(fondo);
        lNumTarjeta.setForeground(Color.white);
        lNumTarjeta.setHorizontalAlignment(JLabel.CENTER);

        this.tNumTarjeta = new JTextField();
        tNumTarjeta.setFont(new Font("Georgia", Font.BOLD, 40));
        tNumTarjeta.setBackground(fondo);
        tNumTarjeta.setForeground(Color.white);
        tNumTarjeta.setHorizontalAlignment(JLabel.CENTER);

        JLabel lCVV = new JLabel("CVV: ");
        lCVV.setFont(new Font("Georgia", Font.BOLD, 40));
        lCVV.setBackground(fondo);
        lCVV.setForeground(Color.white);
        lCVV.setHorizontalAlignment(JLabel.CENTER);

        this.tCVV = new JTextField();
        tCVV.setFont(new Font("Georgia", Font.BOLD, 40));
        tCVV.setBackground(fondo);
        tCVV.setForeground(Color.white);
        tCVV.setHorizontalAlignment(JLabel.CENTER);

        JLabel fechaVen = new JLabel("Fecha de Vencimiento: ");
        fechaVen.setFont(new Font("Georgia", Font.BOLD, 40));
        fechaVen.setBackground(fondo);
        fechaVen.setForeground(Color.white);
        fechaVen.setHorizontalAlignment(JLabel.CENTER);

        this.tfechaVen = new JTextField();
        tfechaVen.setFont(new Font("Georgia", Font.BOLD, 40));
        tfechaVen.setBackground(fondo);
        tfechaVen.setForeground(Color.white);
        tfechaVen.setHorizontalAlignment(JLabel.CENTER);

        JLabel ltoken = new JLabel("Codigo ApplePay enviado al iphone: ");
        ltoken.setFont(new Font("Georgia", Font.BOLD, 40));
        ltoken.setBackground(fondo);
        ltoken.setForeground(Color.white);
        ltoken.setHorizontalAlignment(JLabel.CENTER);

        this.tToken = new JTextField();
        tToken.setFont(new Font("Georgia", Font.BOLD, 40));
        tToken.setBackground(fondo);
        tToken.setForeground(Color.white);
        tToken.setHorizontalAlignment(JLabel.CENTER);

        panel.add(lNombre);
        panel.add(tNombre);
        panel.add(lNumTarjeta);
        panel.add(tNumTarjeta);
        panel.add(lCVV);
        panel.add(tCVV);
        panel.add(fechaVen);
        panel.add(tfechaVen);
        panel.add(ltoken);
        panel.add(tToken);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setBackground(fondo);

        JButton bVolver = new JButton("Volver");
        bVolver.setFont(new Font("Georgia", Font.BOLD, 40));
        bVolver.setBackground(fondo);
        bVolver.setForeground(Color.white);
        bVolver.addActionListener(this);

        JButton bPagar = new JButton("Pagar");
        bPagar.setFont(new Font("Georgia", Font.BOLD, 40));
        bPagar.setBackground(fondo);
        bPagar.setForeground(Color.white);
        bPagar.addActionListener(this);

        panelBotones.add(bVolver);
        panelBotones.add(bPagar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Volver")) {
            this.dispose();
        } else if (comando.equals("Pagar")) {
            String nombre = tNombre.getText();
            String numTarjeta = tNumTarjeta.getText();
            String cvv = tCVV.getText();
            String fechaVen = tfechaVen.getText();
            String token = tToken.getText();
            if (nombre.equals("") || numTarjeta.equals("") || cvv.equals("") || fechaVen.equals("")
                    || tToken.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                ApplePay pasarela = new ApplePay();
                // System.out.println(tarjetas);
                boolean existe = pasarela.verificarTarjeta(numTarjeta, Integer.parseInt(cvv), fechaVen, nombre, token,
                        tarjetas);
                if (existe) {
                    boolean pago = pasarela.pagar(total);
                    if (pago) {
                        JOptionPane.showMessageDialog(null, "Pago realizado con exito");
                        this.setPago(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo hacer el pago");
                        this.setPago(false);
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La tarjeta no existe");
                    this.setPago(false);
                    this.dispose();
                }
            }
        }

    }

    /**
     * @return double return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return boolean return the pago
     */
    public boolean isPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(boolean pago) {
        this.pago = pago;
    }

    /**
     * @return HashMap<String, TarjetaPayPal> return the tarjetas
     */
    public HashMap<String, TarjetaApplePay> getTarjetas() {
        return tarjetas;
    }

    /**
     * @param tarjetas the tarjetas to set
     */
    public void setTarjetas(HashMap<String, TarjetaApplePay> tarjetas) {
        this.tarjetas = tarjetas;
    }

}
