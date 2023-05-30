package interfaz;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FCrearHabitacion extends JFrame implements ActionListener{
    FPrincipal principal;
    JTextField numero_in;
    JTextField ubicacion_in;
    JTextField capacidad_in;
    JComboBox<String> tipo_in;
    JButton camas_in;
    JLabel camas_cantidad;
    JComboBox<String> vista_in;
    JComboBox<String> balcon_in;
    JComboBox<String> cocina_in;
    JTextField m2_in;
    JComboBox<String> aireAcondicionado_in;
    JComboBox<String> calefaccion_in;
    JComboBox<String> tv_in;
    JComboBox<String> cafetera_in;
    JComboBox<String> ropaCama_in;
    JComboBox<String> plancha_in;
    JComboBox<String> secador_in;
    JTextField voltaje_in;
    JComboBox<String> usba_in;
    JComboBox<String> usbc_in;
    JComboBox<String> desayuno_in;
    
    FCama fCama;
    Administrador admin;
    Color fondo = new Color(28, 35, 46);


    public FCrearHabitacion(FPrincipal fPrincipal, Administrador admin){
        this.principal = fPrincipal;
        this.admin=admin;
        inicializar();
    }

    public void inicializar(){
        
        JPanel panel1 = new JPanel(new BorderLayout(10, 10)); // espacio entre los componentes de 10 píxeles
        panel1.setBackground(fondo);

        JLabel numero = new JLabel("Numero:");
        numero.setFont(numero.getFont().deriveFont(20f));
        numero.setForeground(fondo);
        numero.setHorizontalAlignment(JLabel.CENTER);
        numero_in = new JTextField();

        JLabel ubicacion = new JLabel("Ubicacion:");
        ubicacion.setFont(ubicacion.getFont().deriveFont(20f));
        ubicacion.setForeground(fondo);
        ubicacion.setHorizontalAlignment(JLabel.CENTER);
        ubicacion_in = new JTextField();

        //capacidad
        JLabel capacidad = new JLabel("Capacidad:");
        capacidad.setFont(capacidad.getFont().deriveFont(20f));
        capacidad.setForeground(fondo);
        capacidad.setHorizontalAlignment(JLabel.CENTER);
        capacidad_in = new JTextField();

        //tipo
        String[] tipos = {"Estandar", "Suite", "Suite_Doble"};
        JLabel tipo = new JLabel("Tipo:");
        tipo.setFont(tipo.getFont().deriveFont(20f));
        tipo.setForeground(fondo);
        tipo.setHorizontalAlignment(JLabel.CENTER);
        tipo_in = new JComboBox<>(tipos);

        //camas
        JLabel camas = new JLabel("Camas:");
        camas.setFont(camas.getFont().deriveFont(20f));
        camas.setForeground(fondo);
        camas.setHorizontalAlignment(JLabel.CENTER);

        camas_in = new JButton("Agregar...");
        camas_in.addActionListener(this);
        camas_in.setActionCommand("Agregar");
        camas_cantidad = new JLabel("0");
        camas_cantidad.setBackground(Color.LIGHT_GRAY);
        camas_cantidad.setForeground(fondo);
        camas_cantidad.setHorizontalAlignment(JLabel.CENTER);
        

        JPanel camas_panel = new JPanel(new GridLayout(1, 2, 10, 50));
        camas_panel.setBackground(Color.LIGHT_GRAY);
        camas_panel.add(camas_in);
        camas_panel.add(camas_cantidad);

        String[] si_no = {"Si", "No"};
        //vista
        JLabel vista = new JLabel("Vista:");
        vista.setFont(vista.getFont().deriveFont(20f));
        vista.setForeground(fondo);
        vista.setHorizontalAlignment(JLabel.CENTER);
        vista_in = new JComboBox<>(si_no);

        //balcon
        JLabel balcon = new JLabel("Balcon:");
        balcon.setFont(balcon.getFont().deriveFont(20f));
        balcon.setForeground(fondo);
        balcon.setHorizontalAlignment(JLabel.CENTER);
        balcon_in = new JComboBox<>(si_no);

        //cocina
        JLabel cocina = new JLabel("Cocina:");
        cocina.setFont(cocina.getFont().deriveFont(20f));
        cocina.setForeground(fondo);
        cocina.setHorizontalAlignment(JLabel.CENTER);
        cocina_in = new JComboBox<>(si_no);

        //m2
        JLabel m2 = new JLabel("Metros cuadrados:");
        m2.setFont(m2.getFont().deriveFont(20f));
        m2.setForeground(fondo);
        m2.setHorizontalAlignment(JLabel.CENTER);
        m2_in = new JTextField();

        //aire acondicionado
        JLabel aireAcondicionado = new JLabel("Aire acondicionado:");
        aireAcondicionado.setFont(aireAcondicionado.getFont().deriveFont(20f));
        aireAcondicionado.setForeground(fondo);
        aireAcondicionado.setHorizontalAlignment(JLabel.CENTER);
        aireAcondicionado_in = new JComboBox<>(si_no);

        //calefaccion
        JLabel calefaccion = new JLabel("Calefaccion:");
        calefaccion.setFont(calefaccion.getFont().deriveFont(20f));
        calefaccion.setForeground(fondo);
        calefaccion.setHorizontalAlignment(JLabel.CENTER);
        calefaccion_in = new JComboBox<>(si_no);

        //tv
        JLabel tv = new JLabel("TV:");
        tv.setFont(tv.getFont().deriveFont(20f));
        tv.setForeground(fondo);
        tv.setHorizontalAlignment(JLabel.CENTER);
        tv_in = new JComboBox<>(si_no);

        //cafetera
        JLabel cafetera = new JLabel("Cafetera:");
        cafetera.setFont(cafetera.getFont().deriveFont(20f));
        cafetera.setForeground(fondo);
        cafetera.setHorizontalAlignment(JLabel.CENTER);
        cafetera_in = new JComboBox<>(si_no);

        //ropa de cama
        JLabel ropaCama = new JLabel("Ropa de cama:");
        ropaCama.setFont(ropaCama.getFont().deriveFont(20f));
        ropaCama.setForeground(fondo);
        ropaCama.setHorizontalAlignment(JLabel.CENTER);
        ropaCama_in = new JComboBox<>(si_no);

        //plancha
        JLabel plancha = new JLabel("Plancha:");
        plancha.setFont(plancha.getFont().deriveFont(20f));
        plancha.setForeground(fondo);
        plancha.setHorizontalAlignment(JLabel.CENTER);
        plancha_in = new JComboBox<>(si_no);

        //secador
        JLabel secador = new JLabel("Secador:");
        secador.setFont(secador.getFont().deriveFont(20f));
        secador.setForeground(fondo);
        secador.setHorizontalAlignment(JLabel.CENTER);
        secador_in = new JComboBox<>(si_no);

        //voltaje
        JLabel voltaje = new JLabel("Voltaje:");
        voltaje.setFont(voltaje.getFont().deriveFont(20f));
        voltaje.setForeground(fondo);
        voltaje.setHorizontalAlignment(JLabel.CENTER);
        voltaje_in = new JTextField();

        //usb a
        JLabel usba = new JLabel("USB A:");
        usba.setFont(usba.getFont().deriveFont(20f));
        usba.setForeground(fondo);
        usba.setHorizontalAlignment(JLabel.CENTER);
        usba_in = new JComboBox<>(si_no);

        //usb c
        JLabel usbc = new JLabel("USB C:");
        usbc.setFont(usbc.getFont().deriveFont(20f));
        usbc.setForeground(fondo);
        usbc.setHorizontalAlignment(JLabel.CENTER);
        usbc_in = new JComboBox<>(si_no);

        //desayuno
        JLabel desayuno = new JLabel("Desayuno:");
        desayuno.setFont(desayuno.getFont().deriveFont(20f));
        desayuno.setForeground(fondo);
        desayuno.setHorizontalAlignment(JLabel.CENTER);
        desayuno_in = new JComboBox<>(si_no);


        JPanel grilla = new JPanel(new GridLayout(10, 4, 20, 30));
        grilla.setBackground(Color.LIGHT_GRAY);
        grilla.add(numero);
        grilla.add(numero_in);
        grilla.add(ubicacion);
        grilla.add(ubicacion_in);
        grilla.add(capacidad);
        grilla.add(capacidad_in);
        grilla.add(tipo);
        grilla.add(tipo_in);
        grilla.add(camas);
        grilla.add(camas_panel);
        grilla.add(vista);
        grilla.add(vista_in);
        grilla.add(balcon);
        grilla.add(balcon_in);
        grilla.add(cocina);
        grilla.add(cocina_in);
        
        grilla.add(m2);
        grilla.add(m2_in);
        grilla.add(aireAcondicionado);
        grilla.add(aireAcondicionado_in);
        grilla.add(calefaccion);
        grilla.add(calefaccion_in);
        grilla.add(tv);
        grilla.add(tv_in);
        grilla.add(cafetera);
        grilla.add(cafetera_in);
        grilla.add(ropaCama);
        grilla.add(ropaCama_in);
        grilla.add(plancha);
        grilla.add(plancha_in);
        grilla.add(secador);
        grilla.add(secador_in);
        grilla.add(voltaje);
        grilla.add(voltaje_in);
        grilla.add(usba);
        grilla.add(usba_in);
        grilla.add(usbc);
        grilla.add(usbc_in);
        grilla.add(desayuno);
        grilla.add(desayuno_in);

        panel1.add(grilla, BorderLayout.CENTER);

        Component spacer = Box.createHorizontalStrut(100);
        Component spacer2 = Box.createHorizontalStrut(100);
        Component spacer3 = Box.createVerticalStrut(15);
        Component spacer4 = Box.createVerticalStrut(15);

        panel1.add(spacer3, BorderLayout.NORTH);
        panel1.add(spacer, BorderLayout.WEST);
        panel1.add(spacer2, BorderLayout.EAST);
        panel1.add(spacer4, BorderLayout.SOUTH);

        
        // Configura el contenedor principal
        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.CENTER);

        JButton botonSalida = new JButton("Enviar");
        botonSalida.setFont(botonSalida.getFont().deriveFont(30f));
        botonSalida.setBackground(Color.GREEN);
        botonSalida.setForeground(fondo);
        botonSalida.setPreferredSize(new Dimension(200, 80));
        botonSalida.addActionListener(this);
        botonSalida.setActionCommand("Enviar");
        this.add(botonSalida, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Crear Habitacion");
        titulo.setFont(titulo.getFont().deriveFont(32f));
        titulo.setBackground(fondo);
        titulo.setForeground(fondo);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        this.add(titulo, BorderLayout.NORTH);
    

        setBackground(fondo);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(screenSize.width-400, screenSize.height-200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Enviar")) {
            enviar();
            this.dispose();

        }
        else if (comando.equals("Agregar")) {
            fCama=new FCama(this);
        }
    }

    public void cambiarcontador(){
        this.camas_cantidad.setText(Integer.toString(fCama.getCamas().size()));
    }

    public void enviar(){
        int text_numero_in=Integer.parseInt(numero_in.getText());
        String text_ubicacion_in=ubicacion_in.getText();
        int text_capacidad_in=Integer.parseInt(capacidad_in.getText());
        
        int text_tipo_in;
        if (tipo_in.getSelectedItem().equals("Estandar")){
            text_tipo_in=1;
        }
        else if (tipo_in.getSelectedItem().equals("Suite")){
            text_tipo_in=2;
        }
        else{
            text_tipo_in=3;
        }

        ArrayList<Cama> text_camas_cantidad=fCama.getCamas();
        
        boolean text_vista_in;
        if (vista_in.getSelectedItem().equals("Si")){
            text_vista_in=true;
        }
        else{
            text_vista_in=false;
        }

        boolean text_balcon_in;
        if (balcon_in.getSelectedItem().equals("Si")){
            text_balcon_in=true;
        }
        else{
            text_balcon_in=false;
        }

        boolean text_cocina_in;
        if (cocina_in.getSelectedItem().equals("Si")){
            text_cocina_in=true;
        }
        else{
            text_cocina_in=false;
        }

        int text_m2_in=Integer.parseInt(m2_in.getText());

        boolean text_aireAcondicionado_in;
        if (aireAcondicionado_in.getSelectedItem().equals("Si")){
            text_aireAcondicionado_in=true;
        }
        else{
            text_aireAcondicionado_in=false;
        }

        boolean text_calefaccion_in;
        if (calefaccion_in.getSelectedItem().equals("Si")){
            text_calefaccion_in=true;
        }
        else{
            text_calefaccion_in=false;
        }

        boolean text_tv_in;
        if (tv_in.getSelectedItem().equals("Si")){
            text_tv_in=true;
        }
        else{
            text_tv_in=false;
        }

        boolean text_cafetera_in;
        if (cafetera_in.getSelectedItem().equals("Si")){
            text_cafetera_in=true;
        }
        else{
            text_cafetera_in=false;
        }

        boolean text_ropaCama_in;
        if (ropaCama_in.getSelectedItem().equals("Si")){
            text_ropaCama_in=true;
        }
        else{
            text_ropaCama_in=false;
        }

        boolean text_plancha_in;
        if (plancha_in.getSelectedItem().equals("Si")){
            text_plancha_in=true;
        }
        else{
            text_plancha_in=false;
        }

        boolean text_secador_in;
        if (secador_in.getSelectedItem().equals("Si")){
            text_secador_in=true;
        }
        else{
            text_secador_in=false;
        }

        int text_voltaje_in=Integer.parseInt(voltaje_in.getText());

        boolean text_usba_in;
        if (usba_in.getSelectedItem().equals("Si")){
            text_usba_in=true;
        }
        else{
            text_usba_in=false;
        }
        
        boolean text_usbc_in;
        if (usbc_in.getSelectedItem().equals("Si")){
            text_usbc_in=true;
        }
        else{
            text_usbc_in=false;
        }

        boolean text_desayuno_in;
        if (desayuno_in.getSelectedItem().equals("Si")){
            text_desayuno_in=true;
        }
        else{
            text_desayuno_in=false;
        }


        admin.crearHabitacion(text_numero_in, text_ubicacion_in, text_capacidad_in, text_tipo_in, text_vista_in, text_balcon_in, text_cocina_in, 
        text_camas_cantidad,this.principal.hotel.tarifasEstandar,this.principal.hotel.tarifasSuite,this.principal.hotel.tarifasSuite2,this.principal.hotel.habitaciones,
        "DISPONIBLE", text_m2_in, text_aireAcondicionado_in, text_calefaccion_in, text_tv_in, text_cafetera_in, text_ropaCama_in, text_plancha_in, text_secador_in,
        text_voltaje_in, text_usba_in, text_usbc_in, text_desayuno_in);

    }
    
}
