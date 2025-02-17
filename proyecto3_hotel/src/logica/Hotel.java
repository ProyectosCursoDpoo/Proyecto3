package logica;

import java.io.*;
import java.util.*;

public class Hotel {

    public HashMap<Integer, Habitacion> habitaciones = new HashMap<>();
    public HashMap<String, Plato> platos = new HashMap<>();
    public HashMap<String, Servicios> servicios = new HashMap<>();
    public HashMap<Integer, Huesped> huespedes = new HashMap<>();
    public HashMap<Integer, Factura> facturas = new HashMap<>();
    public HashMap<Integer, reserva> reservas = new HashMap<>();
    public HashMap<String, String> database = new HashMap<>();
    public HashMap<String, Integer> tarifasEstandar = new HashMap<>();
    public HashMap<String, Integer> tarifasSuite = new HashMap<>();
    public HashMap<String, Integer> tarifasSuite2 = new HashMap<>();
    public HashMap<Integer, Grupo> grupos = new HashMap<>();
    public HashMap<Integer, Consumo> consumos = new HashMap<>();
    public HashMap<String, TarjetaPayU> tarjetasPayU = new HashMap<>();
    public HashMap<String, TarjetaPayPal> tarjetasPayPal = new HashMap<>();
    public HashMap<String, TarjetaApplePay> tarjetasApplePay = new HashMap<>();

    public boolean parking_gratis = true;
    public boolean piscina = true;
    public boolean zonas_humedas = true;
    public boolean wifi_gratis = true;
    public boolean bbq = true;
    public boolean recepcion_24 = true;
    public boolean pet_friendly = true;

    public String fileHabitaciones = "../Proyecto3/proyecto3_hotel/data/habitaciones.txt";
    public String fileDatabase = "../Proyecto3/proyecto3_hotel/data/database.txt";
    public String fileHuespedes = "../Proyecto3/proyecto3_hotel/data/hueped.txt";
    public String fileGrupos = "../Proyecto3/proyecto3_hotel/data/grupos.txt";
    public String fileReservas = "../Proyecto3/proyecto3_hotel/data/reserva.txt";
    public String fileServicios = "../Proyecto3/proyecto3_hotel/data/servicios.txt";
    public String fileConsumos = "../Proyecto3/proyecto3_hotel/data/consumos.txt";
    public String fileTarjetas = "../Proyecto3/proyecto3_hotel/data/infoTarjetas.txt";
    public String filePlatos = "../Proyecto3/proyecto3_hotel/data/menu.txt";

    public Empleado empleado;

    public void cargarInformacion() {
        try {

            cargarDatabase();

            cargarTarifa(new File("../Proyecto3/proyecto3_hotel/data/tarifa.txt"), tarifasEstandar);
            cargarTarifa(new File("../Proyecto3/proyecto3_hotel/data/tarifa2.txt"), tarifasSuite);
            cargarTarifa(new File("../Proyecto3/proyecto3_hotel/data/tarifa3.txt"), tarifasSuite2);

            cargarHabitaciones();

            cargarPlatos();

            cargarHuespedes();

            cargarGrupos();

            cargarReservas();

            cargarServicios();

            cargarConsumos();

            cargarTarjetas();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setFileHabitaciones(String file) {
        fileHabitaciones = file;
    }

    public void setFileDataBase(String file) {
        fileDatabase = file;
    }

    public void setFileHuespedes(String file) {
        fileHuespedes = file;
    }

    public void setFileGrupos(String file) {
        fileGrupos = file;
    }

    public void setFileReservas(String file) {
        fileReservas = file;
    }

    public void setFileServicios(String file) {
        fileServicios = file;
    }

    public void setFileConsumos(String file) {
        fileConsumos = file;
    }

    public void setFileTarjetas(String file) {
        fileTarjetas = file;
    }

    public void setFilePlatos(String file) {
        filePlatos = file;
    }

    public HashMap<String, TarjetaPayU> getTarjetasPayU() {
        return this.tarjetasPayU;
    }

    public HashMap<String, TarjetaPayPal> getTarjetasPayPal() {
        return this.tarjetasPayPal;
    }

    public HashMap<String, TarjetaApplePay> getTarjetasApplePay() {
        return this.tarjetasApplePay;
    }

    public HashMap<Integer, Huesped> getHuespedes() {
        return this.huespedes;
    }

    public HashMap<Integer, Habitacion> getHabitaciones() {
        return this.habitaciones;
    }

    public HashMap<String, Plato> getPlatos() {
        return this.platos;
    }

    public HashMap<String, Servicios> getServicios() {
        return this.servicios;
    }

    public HashMap<Integer, Factura> getFacturas() {
        return this.facturas;
    }

    public HashMap<Integer, reserva> getReservas() {
        return this.reservas;
    }

    public void setReservas(HashMap<Integer, reserva> reservas) {
        this.reservas = reservas;
    }

    public void setHabitaciones(HashMap<Integer, Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setGrupos(HashMap<Integer, Grupo> grupos) {
        this.grupos = grupos;
    }

    public void setHuespedes(HashMap<Integer, Huesped> huespedes) {
        this.huespedes = huespedes;
    }

    public HashMap<String, String> getDatabase() {
        return this.database;
    }

    public HashMap<String, Integer> getTarifasEstandar() {
        return this.tarifasEstandar;
    }

    public HashMap<String, Integer> getTarifasSuite() {
        return this.tarifasSuite;
    }

    public HashMap<String, Integer> getTarifasSuite2() {
        return this.tarifasSuite2;
    }

    public HashMap<Integer, Grupo> getGrupos() {
        return this.grupos;
    }

    public HashMap<Integer, Consumo> getConsumos() {
        return this.consumos;
    }

    public boolean contrasena(String usuario, String contrasena) {
        if (contrasena.equals(database.get(usuario))) {
            return true;
        } else {
            return false;
        }
    }

    public void login(String usuario, String contrasena) {

        System.out.println(database.get(usuario));
        if (contrasena.equals(database.get(usuario))) {
            if (usuario.contains("Staff")) {
                mostrarInfoStaff(usuario, contrasena);
            } else if (usuario.contains("Recept")) {
                mostrarInfoRecep(usuario, contrasena);
            } else {
                mostrarInfoAdmin();
            }
        } else {
            System.out.println("Usuario o contraseña incorrecta");
        }

    }

    public void logOut() {
        System.out.println("Logout");
        this.empleado = null;
        guardarInformacion();
    }

    public void guardarInformacion() {
        guardarDatabase(database);
        guardarInfoHabitacion(habitaciones);
        guardarTarifa(tarifasEstandar, "tarifa.txt");
        guardarTarifa(tarifasSuite, "tarifa2.txt");
        guardarTarifa(tarifasSuite2, "tarifa3.txt");
        guardarHuesped(huespedes);
        guardarPlato(platos);
        guardarGrupos(grupos);
        guardarReserva(reservas);
        guardarConsumos(consumos);
    }

    public String[][] mostrarMenu(int lugar) {
        Staff empleado = new Staff();
        String[][] menu = ((Staff) empleado).menuRestaurante(platos, lugar);
        return menu;
    }

    private void mostrarInfoStaff(String usuario, String contrasena) {
        int opcion;
        do {
            System.out.println("Opciones Staff");
            System.out.println("1.) Registrar Servicio ");
            System.out.println("2.) Mostrar consumos por reserva ");
            System.out.println("3.) Cerrar Sesión ");
            opcion = Integer.parseInt(input("\nSeleccione una opcion"));
            if (opcion == 1) {
                // HashMap<Integer, Consumo> consumos_actualizados =
                // empleado.registrarServicio(reservas, platos, pago,
                // consumos);
                // consumos = consumos_actualizados;
            } else if (opcion == 2) {
                // empleado.mostrarFacturaPorReserva(consumos);
            } else if (opcion == 3) {
                logOut();
            } else {
                System.out.println("Opcion Inválida");
            }
        } while (opcion != 3);
    }

    private void mostrarInfoRecep(String usuario, String contrasena) {
        int opcion;
        // String nombre = usuario.substring(6);
        // Recepcionista empleado = new Recepcionista(usuario, contrasena, nombre);
        do {
            System.out.println("Opciones Recepcionista");
            System.out.println("1.) Dar Cotización ");
            System.out.println("2.) Iniciar Reserva ");
            System.out.println("3.) Cancelar Reserva ");
            System.out.println("4.) Registrar Salida ");
            // System.out.println("5.) Generar Factura ");
            System.out.println("5.) Cerrar Sesión ");
            opcion = Integer.parseInt(input("\nSeleccione una opcion"));
            if (opcion == 1) {
                // empleado.darCotizacion(huespedes, habitaciones, tarifasEstandar,
                // tarifasSuite, tarifasSuite2);
            } else if (opcion == 2) {
                // HashMap<Integer, reserva> reservas_actualizadas =
                // empleado.iniciarReserva(huespedes, reservas,
                // habitaciones, empleado, tarifasEstandar, tarifasSuite, tarifasSuite2,
                // grupos);
                // reservas = reservas_actualizadas;
            } else if (opcion == 3) {
                // int numero_reserva = Integer.parseInt(input("Ingresa el numero de la reserva
                // que deseas cancelar: "));
                // empleado.cancelarReserva(numero_reserva, reservas);
            } else if (opcion == 4) {
                // int numero_reserva = Integer
                // .parseInt(input("Ingresa el numero de tu reserva para registrar tu salida:
                // "));
                // HashMap<Integer, reserva> reservas_cambiadas =
                // empleado.registrarSalida(numero_reserva, reservas,
                // consumos);
                // HashMap<Integer, Consumo> consumos_cambiados =
                // empleado.borrarConsumos(numero_reserva, reservas,
                // consumos);
                // reservas = reservas_cambiadas;
                // consumos = consumos_cambiados;
                // } else if (opcion == 5) {
                // int numero_reserva = Integer
                // .parseInt(input("Ingresa el numero de tu reserva para generar tu factura:"));
                // empleado.generarFactura(numero_reserva, reservas, consumos);
            } else if (opcion == 5) {
                logOut();
            } else {
                System.out.println("Opcion Inválida");
            }
        } while (opcion != 5);
    }

    private void mostrarInfoAdmin() {
        int opcion;
        Administrador empleado = new Administrador();
        do {
            System.out.println("Opciones Administrador");
            System.out.println("1.) Cargar Habitaciones ");
            System.out.println("2.) Crear Habitacion ");
            System.out.println("3.) Cargar Tarifa ");
            System.out.println("4.) Cambiar Tarifa ");
            System.out.println("5.) Cargar Menú ");
            System.out.println("6.) Configurar Plato ");
            System.out.println("7.) Cerrar Sesión ");
            opcion = Integer.parseInt(input("\nSeleccione una opcion"));
            if (opcion == 1) {
                File archivoHabitaciones = new File(
                        "../Proyecto3/proyecto3_hotel/data/habitaciones2.txt");
                empleado.cargarHabitaciones(archivoHabitaciones, this.tarifasEstandar, this.tarifasSuite,
                        this.tarifasSuite2, this.habitaciones);
            } else if (opcion == 2) {
                empleado.ejecutar_crearHabitacion(this.tarifasEstandar, this.tarifasSuite, this.tarifasSuite2,
                        habitaciones);
            } else if (opcion == 3) {
                File archivoTarifaEstandar = new File(
                        "../Proyecto3/proyecto3_hotel/data/tarifa.txt");
                File archivoTarifaSuite = new File(
                        "../Proyecto3/proyecto3_hotel/data/tarifa2.txt");
                File archivoTarifaSuite2 = new File(
                        "../Proyecto3/proyecto3_hotel/data/tarifa3.txt");
                empleado.cargarTarifa(archivoTarifaEstandar, this.tarifasEstandar);
                empleado.cargarTarifa(archivoTarifaSuite, this.tarifasSuite);
                empleado.cargarTarifa(archivoTarifaSuite2, this.tarifasSuite2);
            } else if (opcion == 4) {
                // empleado.cambiarTarifa(this.tarifasEstandar, this.tarifasSuite,
                // this.tarifasSuite2);
            } else if (opcion == 5) {
                File archivoMenu = new File(
                        "../Proyecto3/proyecto3_hotel/data/menu.txt");
                empleado.cargarMenu(archivoMenu, platos);
            } else if (opcion == 6) {
                // String nombrePlato = input("Ingrese el nombre del plato a modificar");
                // int opcion2 = Integer.parseInt(input(
                // "Que desea modificar? (NombrePlato: 1 NombreBebida: 2 Precio: 3 RangoHora: 4
                // Ubicacion:5)"));
                // String mod = input("Ingrese la modificacion");
                // empleado.configurarPlato(nombrePlato, opcion2, mod, this.platos);
            } else if (opcion == 7) {
                logOut();
            } else {
                System.out.println("Opcion Inválida");
            }
        } while (opcion != 7);
    }

    public void cargarDatabase() throws FileNotFoundException {
        System.out.println("Cargando DataBase de Usuarios");
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(
                    fileDatabase)));
            // "../proyecto2/entrega2/proyecto2_hotel/data/database.txt"
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String usuario = partes[0];
                String contrasena = partes[1];

                database.put(usuario, contrasena);

                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarUsuario(String usuario, String contrasena) {
        database.put(usuario, contrasena);
        for (String i : database.keySet()) {
            System.out.println(i);
        }
    }

    public void cargarHabitaciones() throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(new File(fileHabitaciones)));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                int numero = Integer.valueOf(partes[0]);
                String ubicacion = partes[1];
                int capacidad = Integer.valueOf(partes[2]);
                int tipo = Integer.valueOf(partes[3]);

                ArrayList<Cama> camas_habitacion = new ArrayList<>();
                if (partes[4].contains("/")) {
                    String[] camas_total = (partes[4].split("/"));
                    for (String i : camas_total) {
                        String[] caracteristicas = i.split("-");
                        Cama cama_nueva = new Cama(caracteristicas[0], Integer.parseInt(caracteristicas[1]));
                        camas_habitacion.add(cama_nueva);
                    }
                } else {
                    String camas_total = partes[4];
                    String[] caracteristicas = camas_total.split("-");
                    Cama cama_nueva = new Cama(caracteristicas[0], Integer.parseInt(caracteristicas[1]));
                    camas_habitacion.add(cama_nueva);

                }

                boolean vista = Boolean.valueOf(partes[5]);
                boolean balcon = Boolean.valueOf(partes[6]);
                boolean cocina = Boolean.valueOf(partes[7]);
                String estado = partes[8];

                int m2 = Integer.valueOf(partes[9]);
                boolean aireAcondicionado = Boolean.valueOf(partes[10]);
                boolean calefaccion = Boolean.valueOf(partes[11]);
                boolean tv = Boolean.valueOf(partes[12]);
                boolean cafetera = Boolean.valueOf(partes[13]);
                boolean ropaCama = Boolean.valueOf(partes[14]);
                boolean plancha = Boolean.valueOf(partes[15]);
                boolean secador = Boolean.valueOf(partes[16]);
                int voltaje = Integer.valueOf(partes[17]);
                boolean usba = Boolean.valueOf(partes[18]);
                boolean usbc = Boolean.valueOf(partes[19]);
                boolean desayuno = Boolean.valueOf(partes[20]);

                Habitacion habi_nueva;

                if (habitaciones.get(numero) == null) {
                    if (tipo == 1) {
                        habi_nueva = new Estandar(numero, ubicacion, capacidad, vista, balcon, cocina, camas_habitacion,
                                tarifasEstandar, estado, m2, aireAcondicionado, calefaccion, tv, cafetera, ropaCama,
                                plancha, secador, voltaje, usba, usbc, desayuno);
                    } else if (tipo == 2) {
                        habi_nueva = new Suite(numero, ubicacion, capacidad, vista, balcon, cocina, camas_habitacion,
                                tarifasSuite, estado, m2, aireAcondicionado, calefaccion, tv, cafetera, ropaCama,
                                plancha, secador, voltaje, usba, usbc, desayuno);
                    } else {
                        habi_nueva = new Suite_doble(numero, ubicacion, capacidad, vista, balcon, cocina,
                                camas_habitacion, tarifasSuite2, estado, m2, aireAcondicionado, calefaccion, tv,
                                cafetera, ropaCama, plancha, secador, voltaje, usba, usbc, desayuno);
                    }
                    habitaciones.put(numero, habi_nueva);
                } else {
                    System.out.println("Este número de habitacion ya existe");
                }
                linea = br.readLine();
            }
            setHabitaciones(habitaciones);
            br.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarTarifa(File archivo, HashMap<String, Integer> tarifa) throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String fecha = partes[0];
                int precio = Integer.valueOf(partes[1]);
                if (tarifa.get(fecha) != null) {
                    tarifa.replace(fecha, precio);
                } else {
                    tarifa.put(fecha, precio);
                }
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarPlatos() throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(new File(filePlatos)));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String nombre = partes[0];
                String nombreBebida = partes[1];
                int precio = Integer.valueOf(partes[2]);
                String rangoHora = partes[3];
                String ubicacion = partes[4];
                if (platos.get(nombre) == null) {
                    Plato plato_nuevo = new Plato(nombre, nombreBebida, precio, rangoHora, ubicacion);
                    platos.put(nombre, plato_nuevo);
                } else {
                    System.out.println("Este plato ya existe");
                }
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarHuespedes() throws FileNotFoundException {
        try {
            BufferedReader br;
            String linea;

            br = new BufferedReader(new FileReader(new File(
                    fileHuespedes)));
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String nombre = partes[0];
                int id = Integer.parseInt(partes[1]);
                String correo = partes[2];
                String celular = partes[3];
                String fecha = partes[4];
                Huesped nuevo_huesped = new Huesped(nombre, id, correo, celular, fecha);
                huespedes.put(id, nuevo_huesped);
                linea = br.readLine();
            }
            br.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cargarReservas() throws FileNotFoundException {
        BufferedReader br;
        String linea;
        try {
            br = new BufferedReader(new FileReader(new File(fileReservas)));
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                int id_reserva = Integer.parseInt(partes[0]);
                int id_grupo = Integer.parseInt(partes[1]);
                int tarifa = Integer.parseInt(partes[2]);
                String fecha_inicio = partes[3];
                String rango_fecha = partes[4];
                // String usuario_empleado = partes[5];
                Grupo grupo = grupos.get(id_grupo);

                // String info_empleado = database.get(usuario_empleado);

                Recepcionista empleado = new Recepcionista();

                reserva reserva = new reserva(id_reserva, grupo, tarifa, fecha_inicio, rango_fecha, empleado);
                reservas.put(id_reserva, reserva);

                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarServicios() throws FileNotFoundException {
        BufferedReader br;
        String linea;
        try {
            br = new BufferedReader(
                    new FileReader(new File(fileServicios)));
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String nombre_servicio = partes[0];
                // int tarifa = Integer.parseInt(partes[1]);
                // String ubicacion = partes[2];
                // String horario = partes[3];
                Servicios servicio;
                if (nombre_servicio.equals("Spa")) {
                    servicio = new Spa();
                    servicios.put(nombre_servicio, servicio);
                } else if (nombre_servicio.equals("Restaurante")) {
                    servicio = new Restaurante();
                    servicios.put(nombre_servicio, servicio);
                } else if (nombre_servicio.equals("GuiaTuristica")) {
                    servicio = new GuiaTuristica();
                    servicios.put(nombre_servicio, servicio);
                }
                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarConsumos() throws FileNotFoundException {
        BufferedReader br;
        String linea;
        try {
            br = new BufferedReader(
                    new FileReader(new File(fileConsumos)));
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                int id_consumo = Integer.parseInt(partes[0]);
                Boolean estado = (partes[1].equals("TRUE") ? true : false);
                int numero_reserva = Integer.parseInt(partes[2]);
                String nombre_servicio = partes[3];
                // String precio_consumo = partes[4];
                reserva reserva = reservas.get(numero_reserva);
                Servicios servicio = servicios.get(nombre_servicio);
                Consumo consumo = new Consumo(reserva, servicio, estado, id_consumo, nombre_servicio);

                consumos.put(id_consumo, consumo);

                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarTarjetas() {
        BufferedReader br;
        String linea;
        try {
            br = new BufferedReader(
                    new FileReader(new File("../Proyecto3/proyecto3_hotel/data/infoTarjetas.txt")));
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String plataforma = partes[0];

                if (plataforma.equals("payU")) {
                    String nombre = partes[1];
                    int id = Integer.parseInt(partes[2]);
                    String correo = partes[3];
                    String numTarjeta = partes[4];
                    int cvv = Integer.parseInt(partes[5]);
                    String fechaVencimiento = partes[6];
                    Double saldo = Double.parseDouble(partes[7]);
                    TarjetaPayU tarjeta = new TarjetaPayU(nombre, id, correo, numTarjeta, cvv, fechaVencimiento, saldo);
                    tarjetasPayU.put(numTarjeta, tarjeta);
                } else if (plataforma.equals("payPal")) {
                    String nombre = partes[1];
                    int id = Integer.parseInt(partes[2]);
                    String correo = partes[3];
                    String numTarjeta = partes[4];
                    int cvv = Integer.parseInt(partes[5]);
                    String fechaVencimiento = partes[6];
                    boolean estado = (partes[7].equals("True") ? true : false);
                    String token = partes[8];
                    Double saldo = Double.parseDouble(partes[9]);
                    TarjetaPayPal tarjeta = new TarjetaPayPal(nombre, id, correo, numTarjeta, cvv, fechaVencimiento,
                            estado, token, saldo);
                    tarjetasPayPal.put(numTarjeta, tarjeta);
                } else if (plataforma.equals("applePay")) {
                    String nombre = partes[1];
                    int id = Integer.parseInt(partes[2]);
                    String correo = partes[3];
                    String numTarjeta = partes[4];
                    int cvv = Integer.parseInt(partes[5]);
                    String fechaVencimiento = partes[6];
                    boolean estado = (partes[7].equals("True") ? true : false);
                    String codigoApple = partes[8];
                    Double saldo = Double.parseDouble(partes[9]);
                    TarjetaApplePay tarjeta = new TarjetaApplePay(nombre,
                            id, correo, numTarjeta, cvv, fechaVencimiento, estado, codigoApple, saldo);
                    tarjetasApplePay.put(numTarjeta, tarjeta);
                }

                linea = br.readLine();
            }
        } catch (IOException e) {
        }
    }

    public void cargarGrupos() throws FileNotFoundException {
        BufferedReader br;
        String linea;

        try {
            br = new BufferedReader(new FileReader(new File(fileGrupos)));
            linea = br.readLine();
            while (linea != null) {
                ArrayList<Huesped> huespedes_grupo = new ArrayList<>();
                ArrayList<Habitacion> habitaciones_grupo = new ArrayList<>();
                String[] partes = linea.split(";");
                int id_grupo = Integer.parseInt(partes[0]);

                if (partes[1].contains("/")) {
                    String[] huespedes2 = partes[1].split("/");
                    for (String i : huespedes2) {
                        Huesped huesped = huespedes.get(Integer.parseInt(i));
                        huespedes_grupo.add(huesped);
                    }
                } else {
                    Huesped huesped = huespedes.get(Integer.parseInt(partes[1]));
                    huespedes_grupo.add(huesped);
                }

                // System.out.println(partes[2].contains("/"));
                if (partes[2].contains("/")) {
                    String[] habitaciones2 = partes[2].split("/");
                    for (String i : habitaciones2) {
                        Habitacion habitacion = habitaciones.get(Integer.parseInt(i));
                        habitaciones_grupo.add(habitacion);
                    }
                } else {
                    Habitacion habitacion = habitaciones.get(Integer.parseInt(partes[2]));
                    habitaciones_grupo.add(habitacion);
                }

                Grupo grupo = new Grupo(huespedes_grupo, habitaciones_grupo, id_grupo);
                grupos.put(id_grupo, grupo);

                linea = br.readLine();
            }
            // System.out.println(grupos);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No se encontro el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void guardarDatabase(HashMap<String, String> lista) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto3/proyecto3_hotel/data/database.txt")))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                String usuario = k.toString();
                String password = lista.get(k).toString();
                cadena += usuario + ";" + password + "\n";
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void guardarInfoHabitacion(HashMap<Integer, Habitacion> lista) {

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        fileHabitaciones)))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                String habitacion = lista.get(k).toString();
                cadena += habitacion;
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void guardarTarifa(HashMap<String, Integer> lista, String archivo) {

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto3/proyecto3_hotel/data/"
                                + archivo)))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                cadena += k + ";";
                String tarifa = lista.get(k).toString();
                cadena += tarifa;
                cadena += "\n";
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void guardarPlato(HashMap<String, Plato> lista) {

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto3/proyecto3_hotel/data/menu.txt")))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                String plato = lista.get(k).toString();
                cadena += plato;
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void guardarHuesped(HashMap<Integer, Huesped> lista) {

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto3/proyecto3_hotel/data/hueped.txt")))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                String info = lista.get(k).toString();
                cadena += info;
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void guardarReserva(HashMap<Integer, reserva> lista) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto3/proyecto3_hotel/data/reserva.txt")))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                String info = lista.get(k).toString();
                cadena += info;
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void guardarConsumos(HashMap<Integer, Consumo> lista) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto3/proyecto3_hotel/data/consumos.txt")))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                String info = lista.get(k).toString();
                cadena += info;
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    // guardar grupos

    public void guardarGrupos(HashMap<Integer, Grupo> lista) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "../Proyecto3/proyecto3_hotel/data/grupos.txt")))) {
            String cadena = "";
            for (Object k : lista.keySet()) {
                String info = lista.get(k).toString();
                cadena += info;
            }
            bw.write(cadena);
            bw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String input(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
