package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//import java.sql.Date;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logica.Hotel;
import logica.Huesped;
import logica.Recepcionista;
//import logica.Administrador;
import logica.Empleado;
import logica.Estandar;
import logica.Grupo;
import logica.Habitacion;
import logica.reserva;

public class ReservaTest {

    private Hotel hotel;

    @Before
    public void setUp() {
        hotel = new Hotel();
    }

    @Test
    public void testCrearReserva() {
        try {

            int numeroReserva = 1; // Número de reserva único

            Huesped huesped = new Huesped("Luis ", 2222, "Luis@", "315666", "25/04/2004");
            ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
            huespedes.add(huesped);

            Estandar habitacion = new Estandar(numeroReserva, "Primero", 1, false, false, false, null, null, null, 50,
                    false, false, false, false, false, false, false, 100, false, false, false);
            ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
            habitaciones.add(habitacion);

            Grupo grupo = new Grupo(huespedes, habitaciones, 2);

            Empleado empleado = new Recepcionista();

            String fechaInicio = "05.06.2023"; // Fecha de inicio de la reserva
            String rangoFechas = "506-510"; // Fecha de fin de la reserva

            reserva reserva = new reserva(numeroReserva, grupo, 150000, fechaInicio, rangoFechas,
                    empleado);

            hotel.getReservas().put(numeroReserva, reserva);

            // Verificar que la reserva se haya agregado correctamente||
            assertEquals(reserva, hotel.getReservas().get(numeroReserva));
        } catch (Exception e) {
            // Manejar cualquier excepción lanzada durante el proceso de prueba
            fail("Se produjo una excepción: " + e.getMessage());
        }
    }

}
