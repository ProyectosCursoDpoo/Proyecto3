package pruebas;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Hotel;

public class HotelTest {
    private Hotel hotel;
    
    @BeforeEach
    public void setUp() throws Exception {
        hotel = new Hotel();
    }

    @Test
    public void cargarHabitaciones_ValidFile_Test() throws FileNotFoundException, IOException {
        assertEquals(0, hotel.getHabitaciones().size());
        hotel.cargarHabitaciones();
        assertEquals(10, hotel.getHabitaciones().size());
    }

    @Test
    public void cargarHabitaciones_FileNotFoundException_Test() {

        // Modificamos la ruta del archivo para que no exista
        String rutaArchivo = "ruta/inexistente/habitaciones.txt";
        hotel.setFileHabitaciones(rutaArchivo);

        // Verificamos que se lance la excepción FileNotFoundException
        assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarHabitaciones();
        });
    }

    @Test
    public void cargarDatabase_ValidFile_Test() throws FileNotFoundException, IOException {
        assertEquals(0, hotel.getDatabase().size());
        hotel.cargarDatabase();
        assertEquals(10, hotel.getDatabase().size());
    }
    
    @Test
    public void cargarDatabase_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/database.txt";
        hotel.setFileDataBase(rutaArchivo);
    
        assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarDatabase();
        });
    }
    
    @Test
    public void cargarTarifa_ValidFile_Test() throws FileNotFoundException, IOException {
        HashMap<String, Integer> tarifa = new HashMap<>();
        assertEquals(0, tarifa.size());
    
        File archivo = new File("../Proyecto3/proyecto3_hotel/data/tarifa2.txt");    
        hotel.cargarTarifa(archivo, tarifa);
        assertEquals(361, tarifa.size());
    }
    
    @Test
    public void cargarTarifa_FileNotFoundException_Test() {
        HashMap<String, Integer> tarifa = new HashMap<>();
        assertEquals(0, tarifa.size());
    
        // Modificamos la ruta del archivo para que no exista
        File archivo = new File("ruta/inexistente/tarifas.txt");
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarTarifa(archivo, tarifa);
        });
    
        assertEquals(0, tarifa.size());
    }
    
    @Test
    public void cargarPlatos_ValidFile_Test() throws FileNotFoundException, IOException {
        assertEquals(0, hotel.getPlatos().size());
        assertDoesNotThrow(() -> {
            hotel.cargarPlatos();
        });
        assertEquals(9, hotel.getPlatos().size());
    }
    
    @Test
    public void cargarPlatos_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/platos.txt";
        hotel.setFilePlatos(rutaArchivo);
    
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarPlatos();
        });
    
        assertEquals(0, hotel.getPlatos().size());
    }
    
    @Test
    public void cargarHuespedes_ValidFile_Test() throws FileNotFoundException, IOException {
        assertEquals(0, hotel.getHuespedes().size());
        assertDoesNotThrow(() -> {
            hotel.cargarHuespedes();
        });
        assertEquals(5, hotel.getHuespedes().size());
    }
    
    @Test
    public void cargarHuespedes_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/huespedes.txt";
        hotel.setFileHuespedes(rutaArchivo);
    
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarHuespedes();
        });
            assertEquals(0, hotel.getHuespedes().size());
    }
    
    @Test
    public void cargarReservas_ValidFile_Test() throws FileNotFoundException, IOException {
        assertEquals(0, hotel.getReservas().size());
        assertDoesNotThrow(() -> {
            hotel.cargarReservas();
        });
        assertEquals(2, hotel.getReservas().size());
    }
    
    @Test
    public void cargarReservas_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/reservas.txt";
        hotel.setFileReservas(rutaArchivo);
    
        assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarReservas();
        });
        assertEquals(0, hotel.getReservas().size());
    }
    
    @Test
    public void cargarServicios_ValidFile_Test() throws FileNotFoundException, IOException {
        assertEquals(0, hotel.getServicios().size());
        assertDoesNotThrow(() -> {
            hotel.cargarServicios();
        });
        assertEquals(3, hotel.getServicios().size());
    }
    
    @Test
    public void cargarServicios_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/servicios.txt";
        hotel.setFileServicios(rutaArchivo);
    
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarServicios();
        });
        assertEquals(0, hotel.getServicios().size());
    }
    
    @Test
    public void cargarConsumos_ValidFile_Test() throws FileNotFoundException, IOException {
        assertEquals(0, hotel.getConsumos().size());
        assertDoesNotThrow(() -> {
            hotel.cargarConsumos();
        });
        assertEquals(0, hotel.getConsumos().size());
    }
    
    @Test
    public void cargarConsumos_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/consumos.txt";
        hotel.setFileConsumos(rutaArchivo);
    
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarConsumos();
        });
        assertEquals(0, hotel.getConsumos().size());
    }

    @Test
    public void cargarTarjetas_ValidFile_Test() throws FileNotFoundException {
        assertEquals(0, hotel.getTarjetasPayU().size());
        hotel.cargarTarjetas();
        assertEquals(3, hotel.getTarjetasPayU().size());
    }
    
    @Test
    public void cargarTarjetas_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/tarjetas.txt";
        hotel.setFileTarjetas(rutaArchivo);
    
        assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarTarjetas();
        });
    }

    @Test
    public void cargarGrupos_ValidFile_Test() throws FileNotFoundException {
        assertEquals(0, hotel.getGrupos().size());
        hotel.cargarGrupos();
        assertEquals(2, hotel.getGrupos().size());
    }
    
    @Test
    public void cargarGrupos_FileNotFoundException_Test() {
        String rutaArchivo = "ruta/inexistente/grupos.txt";
        hotel.setFileGrupos(rutaArchivo);
    
        assertThrows(FileNotFoundException.class, () -> {
            hotel.cargarGrupos();
        });
    }
}
