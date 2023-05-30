package logica;

import java.io.*;
import java.util.*;

public class Administrador extends Empleado {

    private String usuario;
    private String contrasena;

    public void cargarHabitaciones(File archivo, HashMap<String, Integer> precioEstandar,
            HashMap<String, Integer> precioSuite, HashMap<String, Integer> precioSuite2,
            HashMap<Integer, Habitacion> habitaciones) {
        System.out.println("Cargando Habitaciones desde Archivo");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
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

                int m2= Integer.valueOf(partes[9]);
                boolean aireAcondicionado= Boolean.valueOf(partes[10]);
                boolean calefaccion= Boolean.valueOf(partes[11]);
                boolean tv= Boolean.valueOf(partes[12]);
                boolean cafetera= Boolean.valueOf(partes[13]);
                boolean ropaCama= Boolean.valueOf(partes[14]);
                boolean plancha= Boolean.valueOf(partes[15]);
                boolean secador= Boolean.valueOf(partes[16]);
                int voltaje= Integer.valueOf(partes[17]);
                boolean usba= Boolean.valueOf(partes[18]);
                boolean usbc= Boolean.valueOf(partes[19]);
                boolean desayuno= Boolean.valueOf(partes[20]);

                crearHabitacion(numero, ubicacion, capacidad, tipo, vista, balcon, cocina, camas_habitacion,
                        precioEstandar, precioSuite, precioSuite2,
                        habitaciones, estado, m2,aireAcondicionado,calefaccion,tv,cafetera,ropaCama,plancha,secador,voltaje,usba,usbc,desayuno);
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ejecutar_crearHabitacion(HashMap<String, Integer> precioEstandar, HashMap<String, Integer> precioSuite, HashMap<String, Integer> precioSuite2, HashMap<Integer, Habitacion> habitaciones) {
        System.out.println("Deshabilitado por el momento");
    }

    public void crearHabitacion(int numero, String ubicacion, int capacidad, int tipo, boolean vista, boolean balcon,
            boolean cocina, ArrayList<Cama> camas, HashMap<String, Integer> precioEstandar,
            HashMap<String, Integer> precioSuite, HashMap<String, Integer> precioSuite2,
            HashMap<Integer, Habitacion> habitaciones, String estado, int m2,boolean aireAcondicionado,boolean calefaccion,
            boolean tv,boolean cafetera,boolean ropaCama,boolean plancha,boolean secador,int voltaje,boolean usba,boolean usbc,boolean desayuno) {
        Habitacion habi_nueva;

        if (habitaciones.get(numero) == null) {
            if (tipo == 1) {
                habi_nueva = new Estandar(numero, ubicacion, capacidad, vista, balcon, cocina, camas, precioEstandar,estado,m2,aireAcondicionado,calefaccion,tv,cafetera,ropaCama,plancha,secador,voltaje,usba,usbc,desayuno);
            } else if (tipo == 2) {
                habi_nueva = new Suite(numero, ubicacion, capacidad, vista, balcon, cocina, camas, precioEstandar,estado,m2,aireAcondicionado,calefaccion,tv,cafetera,ropaCama,plancha,secador,voltaje,usba,usbc,desayuno);
            } else {
                habi_nueva = new Suite_doble(numero, ubicacion, capacidad, vista, balcon, cocina, camas, precioEstandar,estado,m2,aireAcondicionado,calefaccion,tv,cafetera,ropaCama,plancha,secador,voltaje,usba,usbc,desayuno);
            }
            habitaciones.put(numero, habi_nueva);
        } else {
            System.out.println("Este número de habitacion ya existe");
        }
    }

    public void cargarTarifa(File archivo, HashMap<String, Integer> tarifa) {
        System.out.println("Cargando Tarifas desde Archivo");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
            
                String fecha=partes[0];
                int precio = Integer.valueOf(partes[1]);

                if (tarifa.get(fecha) != null) {
                    tarifa.replace(fecha, precio);
                } else {
                    tarifa.put(fecha, precio);
                }

                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarTarifa(int opcionHabitacion, String nueva_fecha, int nueva_tarifa,HashMap<String, Integer> tarifaEstandar,HashMap<String, Integer> tarifaSuite, HashMap<String, Integer> tarifaSuite2) {
        
        int fecha_ini = Integer.parseInt(nueva_fecha.substring(0,4));
        int fecha_fin = Integer.parseInt(nueva_fecha.substring(5));
        
        if (opcionHabitacion>=1 && opcionHabitacion<=3){
            while (fecha_ini != fecha_fin) {
                if (fecha_ini % 100 == 31) {
                    fecha_ini = (fecha_ini - 31) + 100;
                }
                if (opcionHabitacion == 1) {
                    if (tarifaEstandar.get(String.valueOf(fecha_ini)) != null) {
                        tarifaEstandar.replace(String.valueOf(fecha_ini), nueva_tarifa);
                    } else {
                        tarifaEstandar.put(String.valueOf(fecha_ini), nueva_tarifa);
                    }
                }
                
                else if (opcionHabitacion == 2) {
                        if (tarifaSuite.get(String.valueOf(fecha_ini)) != null) {
                            tarifaSuite.replace(String.valueOf(fecha_ini), nueva_tarifa);
                        } else {
                            tarifaSuite.put(String.valueOf(fecha_ini), nueva_tarifa);
                        }
                }

                else if (opcionHabitacion == 3) {
                    if (tarifaSuite2.get(String.valueOf(fecha_ini)) != null) {
                        tarifaSuite2.replace(String.valueOf(fecha_ini), nueva_tarifa);
                    } else {
                        tarifaSuite2.put(String.valueOf(fecha_ini), nueva_tarifa);
                    }
                }

                fecha_ini++;
            }

        } else{
            System.out.println("Opción de habitacion no disponible");
        }
    }

    public void cargarMenu(File archivo, HashMap<String,Plato> platos) {
        System.out.println("Cargando Menu desde Archivo");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String nombrePlato = partes[0];
                String nombreBebida = partes[1];
                int precio = Integer.valueOf(partes[2]);
                String rangoFecha = partes[3];
                String ubicacion = partes[4];

                Plato plato_nuevo= new Plato(nombrePlato,nombreBebida,precio,rangoFecha,ubicacion);
                if (platos.get(String.valueOf(nombrePlato)) != null) {
                    platos.replace(String.valueOf(nombrePlato), plato_nuevo);
                } else {
                    platos.put(String.valueOf(nombrePlato), plato_nuevo);
                }

                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configurarPlato(String nombreplato, String nuevonombre, String nuevabebida, String nuevoprecio, String nuevahora, String nuevaubi, HashMap<String, Plato> platos) {
        System.out.println("Configurar Plato " + nombreplato);
        Plato plato_mod = platos.get(nombreplato);
        if (plato_mod != null) {

            if (!nuevonombre.equals("")) {
                plato_mod.setNombrePlato(nombreplato);
            }
            if (!nuevabebida.equals("")) {
                plato_mod.setNombreBebida(nuevabebida);
            }
            if (!nuevoprecio.equals("")) {
                plato_mod.setPrecio(Integer.parseInt(nuevoprecio));
            } 
            if (!nuevahora.equals("")) {
                plato_mod.setRangoHora(nuevahora);
            }
            if (!nuevaubi.equals("")) {
                plato_mod.setLugar(nuevaubi);
            }
            platos.replace(nombreplato, plato_mod);
        } 
    }

    public void generarReportes() {
        System.out.println("Generando Reportes");
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
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
