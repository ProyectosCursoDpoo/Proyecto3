package interfaz;

import logica.Hotel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FPrincipalHuesped extends JFrame {

    public Hotel hotel = new Hotel();
    private FHuesped pHuesped;
    private FCrearHuesped pCrearHuesped;

    // CardLayout para cambiar entre los paneles
    private CardLayout cardLayout;
    private JPanel contentP;

    public FPrincipalHuesped() {
        super("Sistema de hotel para huespedes");
        hotel.cargarInformacion();
        try {
            new Hotel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el estado inicial " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pHuesped = new FHuesped(hotel, this);
        pCrearHuesped = new FCrearHuesped(this);

        cardLayout = new CardLayout();
        contentP = new JPanel(cardLayout);
        contentP.add(new FLoginHuesped(this), "login");

        contentP.add(pHuesped, "Huesped");
        contentP.add(pCrearHuesped, "crearHuesped");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
                //hotel.guardarInformacion();
            }
    });

    // establece el tamaño del marco como las dimensiones de la pantalla
    setSize(screenSize.width-50, screenSize.height-50);
    // establece la ubicación del marco en la esquina superior izquierda de la pantalla
    setLocationRelativeTo(null);
    setResizable(false);
    setContentPane(contentP);
    setVisible(true);

    }

    public void login(String usuario, String contrasena) {

    
        String usuarioData = "Huesped" +  usuario;

        if (hotel.database.containsKey(usuarioData)) {
            if (hotel.contrasena(usuarioData, contrasena)){
                cardLayout.show(contentP, "Huesped");
            }
            else{
                JOptionPane.showMessageDialog(this, "El usuario existe pero no corresponde a esa contraseña", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            int opcion = JOptionPane.showOptionDialog(this, "El usuario no existe. ¿Desea crear una nueva cuenta?",
                    "Crear cuenta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (opcion == JOptionPane.YES_OPTION) {
                cardLayout.show(contentP, "crearHuesped");
            }
            //implementacion de crear una nueva cuenta
        }
    }

    public static void main(String[] args) {
        new FPrincipalHuesped();
    }

    private class FCrearHuesped extends JPanel {
        private FPrincipalHuesped fPrincipalHuesped;
        private JTextField usuarioField;
        private JPasswordField contrasenaField;
        private JButton crearButton;
        private JButton cancelarButton;

        public FCrearHuesped(FPrincipalHuesped fPrincipalHuesped) {
            this.fPrincipalHuesped = fPrincipalHuesped;
            Color fondo = new Color(60, 79, 118);
            Color texto = new Color(221, 219, 241);

            setLayout(new GridLayout(3, 2, 10, 10));
            setBackground(fondo);

            JLabel usuarioLabel = new JLabel("Usuario:");
            usuarioLabel.setForeground(texto);
            add(usuarioLabel);

            usuarioField = new JTextField();
            add(usuarioField);

            JLabel contrasenaLabel = new JLabel("Contraseña:");
            contrasenaLabel.setForeground(texto);
            add(contrasenaLabel);

            contrasenaField = new JPasswordField();
            add(contrasenaField);

            crearButton = new JButton("Crear");
            crearButton.setBackground(fondo);
            crearButton.setForeground(texto);
            crearButton.addActionListener(new CrearButtonListener());
            add(crearButton);

            cancelarButton = new JButton("Cancelar");
            cancelarButton.addActionListener(new CancelarButtonListener());
            add(cancelarButton);
        }

        private class CrearButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String usuario = "Huesped" + usuarioField.getText();
                String contrasena = new String(contrasenaField.getPassword());
                if (usuario.length() == 0 || contrasena.length() == 0) {
                    JOptionPane.showMessageDialog(fPrincipalHuesped, "Por favor ingrese un usuario y contraseña",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    hotel.agregarUsuario(usuario, contrasena);
                    JOptionPane.showMessageDialog(fPrincipalHuesped, "Usuario creado exitosamente", "Usuario creado",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                fPrincipalHuesped.cardLayout.show(fPrincipalHuesped.contentP, "login");
            }
        }
        private class CancelarButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                fPrincipalHuesped.cardLayout.show(fPrincipalHuesped.contentP, "login");
            }
        }
    }
}
