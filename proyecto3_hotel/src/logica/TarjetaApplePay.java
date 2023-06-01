package logica;

public class TarjetaApplePay extends Tarjeta {
    private String nombre;
    private int identificacion;
    private String correo;
    private String numTarjeta;
    private int cvv;
    private String fechaVencimiento;
    private boolean estado;
    private String codigoApplePay;
    private double saldo;

    public TarjetaApplePay(String nombre, int identificacion, String correo,
            String numTarjeta, int cvv,
            String fechaVencimiento, boolean estado, String codigoApplePay, double saldo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.codigoApplePay = codigoApplePay;
        this.saldo = saldo;
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
     * @return String return the numTarjeta
     */
    public String getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * @param numTarjeta the numTarjeta to set
     */
    public void setNumTarjeta(String numTarjeta) {
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
     * @return boolean return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return String return the codigoApplePay
     */
    public String getCodigoApplePay() {
        return codigoApplePay;
    }

    /**
     * @param codigoApplePay the codigoApplePay to set
     */
    public void setCodigoApplePay(String codigoApplePay) {
        this.codigoApplePay = codigoApplePay;
    }

    /**
     * @return double return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
