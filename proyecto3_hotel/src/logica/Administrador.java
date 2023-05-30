package logica;

import java.io.*;
import java.util.*;

import java.awt.Color;
import java.io.File;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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

    public void generarReportesRestaurante() {
        // reporte de venta de cada producto del restaurante
        HashMap<String, Integer> reporteRestaurante = new HashMap<>();
        HashMap<String, Integer> reporteRestaurante2 = new HashMap<>();
        HashMap<String, Integer> reporteLugares = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("../Proyecto3/proyecto3_hotel/data/base_reportes/restaurante.txt"));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String nombrePlato = partes[0];
                int precio = Integer.valueOf(partes[1]);
                int cantidad = Integer.valueOf(partes[2]);
                
                reporteRestaurante.put(nombrePlato, precio*cantidad);
                reporteRestaurante2.put(nombrePlato, cantidad);

                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("../Proyecto3/proyecto3_hotel/data/base_reportes/lugares_comida.txt"));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String nombrePlato = partes[0];
                int precio = Integer.valueOf(partes[1]);
                reporteLugares.put(nombrePlato, precio);
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a dataset for the bar graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String k : reporteRestaurante.keySet()) {
            dataset.addValue(reporteRestaurante.get(k), k, k);
        }
        
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for (String k : reporteRestaurante2.keySet()) {
            dataset2.addValue(reporteRestaurante2.get(k), k, k);
        }

        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        for (String k : reporteLugares.keySet()) {
            dataset3.addValue(reporteLugares.get(k), k, k);
        }
        
        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Reporte Restaurante Precio Total",     // Chart title
            "Productos",              // X-axis label
            "Valor en pesos",                 // Y-axis label
            dataset,                 // Dataset
            PlotOrientation.VERTICAL,// Plot orientation
            true,                    // Include legend
            true,                    // Use tooltips
            false                    // Use URLs
        );

        
        // Customize the chart
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setPaint(Color.BLACK);
        chart.getCategoryPlot().getRangeAxis().setLabelPaint(Color.BLACK);
        chart.getCategoryPlot().getDomainAxis().setLabelPaint(Color.BLACK);
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.BLACK);
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.BLACK);

        JFreeChart chart2 = ChartFactory.createBarChart3D(
            "Reporte Restaurante Cantidad Vendidos",     // Chart title
            "Productos",              // X-axis label
            "Cantidad",                 // Y-axis label
            dataset2,                 // Dataset
            PlotOrientation.VERTICAL,// Plot orientation
            true,                    // Include legend
            true,                    // Use tooltips
            false                    // Use URLs
        );

        chart2.setBackgroundPaint(Color.WHITE);
        chart2.getTitle().setPaint(Color.BLACK);
        chart2.getCategoryPlot().getRangeAxis().setLabelPaint(Color.BLACK);
        chart2.getCategoryPlot().getDomainAxis().setLabelPaint(Color.BLACK);
        chart2.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.BLACK);
        chart2.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.BLACK);

        JFreeChart chart3 = ChartFactory.createBarChart(
            "Reporte Lugares Consumo Productos",     // Chart title
            "Productos",              // X-axis label
            "Cantidad",                 // Y-axis label
            dataset3,                 // Dataset
            PlotOrientation.VERTICAL,// Plot orientation
            true,                    // Include legend
            true,                    // Use tooltips
            false                    // Use URLs
        );

        chart3.setBackgroundPaint(Color.WHITE);
        chart2.getTitle().setPaint(Color.BLACK);
        chart3.getCategoryPlot().getRangeAxis().setLabelPaint(Color.BLACK);
        chart3.getCategoryPlot().getDomainAxis().setLabelPaint(Color.BLACK);
        chart3.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.BLACK);
        chart3.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.BLACK);
        
        // // Create a chart frame and display the chart
        // ChartFrame frame = new ChartFrame("Bar Graph", chart);
        // frame.pack();
        // frame.setVisible(true);
        
        try {
            // Create a PDF document
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("../Proyecto3/proyecto3_hotel/data/Reportes_PDF/ReporteRestaurante.pdf"));
            document.open();

            // Convert the first chart into an image
            int width = 500;
            int height = 300;
            File chartImageFile1 = new File("chart_image.png");
            ChartUtilities.saveChartAsPNG(chartImageFile1, chart, width, height);

            // Add the first chart image to the PDF document
            PdfContentByte contentByte = writer.getDirectContent();
            com.itextpdf.text.Image chartImage1 = com.itextpdf.text.Image.getInstance(chartImageFile1.getAbsolutePath());
            chartImage1.setAbsolutePosition(document.leftMargin(), PageSize.A4.getHeight() - document.topMargin() - height);
            chartImage1.scaleToFit(width, height);
            contentByte.addImage(chartImage1);

            // Add a new page for the second chart
            document.newPage();

            // Convert the second chart into an image
            File chartImageFile2 = new File("chart_image2.png");
            ChartUtilities.saveChartAsPNG(chartImageFile2, chart2, width, height);

            // Add the second chart image to the PDF document
            com.itextpdf.text.Image chartImage2 = com.itextpdf.text.Image.getInstance(chartImageFile2.getAbsolutePath());
            chartImage2.setAbsolutePosition(document.leftMargin(), PageSize.A4.getHeight() - document.topMargin() - height);
            chartImage2.scaleToFit(width, height);
            contentByte.addImage(chartImage2);

            document.newPage();

            // Convert the second chart into an image
            File chartImageFile3 = new File("chart_image3.png");
            ChartUtilities.saveChartAsPNG(chartImageFile3, chart3, width, height);

            // Add the second chart image to the PDF document
            com.itextpdf.text.Image chartImage3 = com.itextpdf.text.Image.getInstance(chartImageFile3.getAbsolutePath());
            chartImage3.setAbsolutePosition(document.leftMargin(), PageSize.A4.getHeight() - document.topMargin() - height);
            chartImage3.scaleToFit(width, height);
            contentByte.addImage(chartImage3);

            document.close();

            // Delete the temporary chart image files
            chartImageFile1.delete();
            chartImageFile2.delete();
            chartImageFile3.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReportesFacturas() {
        // reporte de venta de cada producto del restaurante
        ArrayList<String> reportefacturas = new ArrayList<>();
        ArrayList<Integer> reportefacturas2 = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("../Proyecto3/proyecto3_hotel/data/base_reportes/facturas.txt"));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String mes = partes[0];
                int precio = Integer.valueOf(partes[1]);
                reportefacturas.add(mes);
                reportefacturas2.add(precio);
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a dataset for the bar graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i=0; i<reportefacturas.size();i++) {
            if (reportefacturas.get(i).contains("2022")){
                dataset.addValue(reportefacturas2.get(i), "2022", reportefacturas.get(i).substring(0,2));
            }
            else if (reportefacturas.get(i).contains("2023")){
                dataset.addValue(reportefacturas2.get(i), "2023", reportefacturas.get(i).substring(0,2));
            }
            else{
                dataset.addValue(reportefacturas2.get(i), "2021", reportefacturas.get(i).substring(0,2));
            }
           
        }
        
        // Create the bar chart
        JFreeChart chart = ChartFactory.createLineChart(
            "Reporte Valores de Facturas en el Tiempo",     // Chart title
            "Meses",              // X-axis label
            "Valor en pesos",                 // Y-axis label
            dataset,                 // Dataset
            PlotOrientation.VERTICAL,// Plot orientation
            true,                    // Include legend
            true,                    // Use tooltips
            false                    // Use URLs
        );

        
        // Customize the chart
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setPaint(Color.BLACK);
        chart.getCategoryPlot().getRangeAxis().setLabelPaint(Color.BLACK);
        chart.getCategoryPlot().getDomainAxis().setLabelPaint(Color.BLACK);
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.BLACK);
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.BLACK);
        
        try {
            // Create a PDF document
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("../Proyecto3/proyecto3_hotel/data/Reportes_PDF/ReporteFacturas.pdf"));
            document.open();

            // Convert the first chart into an image
            int width = 500;
            int height = 300;
            File chartImageFile1 = new File("chart_image.png");
            ChartUtilities.saveChartAsPNG(chartImageFile1, chart, width, height);

            // Add the first chart image to the PDF document
            PdfContentByte contentByte = writer.getDirectContent();
            com.itextpdf.text.Image chartImage1 = com.itextpdf.text.Image.getInstance(chartImageFile1.getAbsolutePath());
            chartImage1.setAbsolutePosition(document.leftMargin(), PageSize.A4.getHeight() - document.topMargin() - height);
            chartImage1.scaleToFit(width, height);
            contentByte.addImage(chartImage1);

            document.close();

            // Delete the temporary chart image files
            chartImageFile1.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReportesEdades() {
        // reporte de venta de cada producto del restaurante
        HashMap<String, Integer> resporteEdades = new HashMap<>();
        

        try {
            BufferedReader br = new BufferedReader(new FileReader("../Proyecto3/proyecto3_hotel/data/base_reportes/edades.txt"));
            String linea;
            linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");
                String rango = partes[0];
                int cantidad = Integer.valueOf(partes[1]);
                resporteEdades.put(rango,cantidad);
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a dataset for the bar graph
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String k : resporteEdades.keySet()) {
            dataset.setValue(k,resporteEdades.get(k));
           
        }
        
        // Create the bar chart
        JFreeChart chart = ChartFactory.createPieChart(
            "Reporte Capacidad por Edades",     // Chart title
            dataset,                 // Dataset
            true,                    // Include legend
            true,                    // Use tooltips
            false                    // Use URLs
        );

        
        // Customize the chart
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setPaint(Color.BLACK);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setLabelOutlinePaint(Color.BLACK);
        plot.setLabelShadowPaint(Color.GRAY);
        plot.setSectionPaint(1,new Color(0, 176, 80)); // Custom color for a section
        
        try {
            // Create a PDF document
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("../Proyecto3/proyecto3_hotel/data/Reportes_PDF/ReporteEdades.pdf"));
            document.open();

            // Convert the first chart into an image
            int width = 500;
            int height = 300;
            File chartImageFile1 = new File("chart_image.png");
            ChartUtilities.saveChartAsPNG(chartImageFile1, chart, width, height);

            // Add the first chart image to the PDF document
            PdfContentByte contentByte = writer.getDirectContent();
            com.itextpdf.text.Image chartImage1 = com.itextpdf.text.Image.getInstance(chartImageFile1.getAbsolutePath());
            chartImage1.setAbsolutePosition(document.leftMargin(), PageSize.A4.getHeight() - document.topMargin() - height);
            chartImage1.scaleToFit(width, height);
            contentByte.addImage(chartImage1);

            document.close();

            // Delete the temporary chart image files
            chartImageFile1.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
