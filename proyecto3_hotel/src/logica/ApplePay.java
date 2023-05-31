package logica;

public class ApplePay extends PasarelaPago {
    private String nombre;
    private int identificacion;
    private String correo;
    private int numTarjeta;
    private int cvv;
    private String fechaVencimiento;
    private int monto;

    // Constructor
    public ApplePay(String nombre, int identificacion, String correo, int numTarjeta, int cvv, String fechaVencimiento,
            int monto) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
    }

    // Metodos
    public void verificarTarjeta(int numTarjeta) {
        // TODO
    }

    public boolean pagar(int numTarjeta, int cvv, String fechaVencimiento, int monto) {
        // TODO
        return true;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return int return the identificacion
     */
    public int getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return String return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return int return the numTarjeta
     */
    public int getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * @param numTarjeta the numTarjeta to set
     */
    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    /**
     * @return int return the cvv
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * @return String return the fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return int return the monto
     */
    public int getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }

}
