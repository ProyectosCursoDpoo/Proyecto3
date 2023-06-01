package logica;

import java.util.HashMap;

public class ApplePay extends PasarelaPago {
    private TarjetaApplePay card;

    // Metodos
    public boolean verificarTarjeta(String numTarjeta, int cvv, String fechaVencimiento, String nombrePropetario,
            String codigoApplePay, HashMap<String, TarjetaApplePay> tarjetas) {
        if (tarjetas.containsKey(numTarjeta)) {
            TarjetaApplePay tarjeta = tarjetas.get(numTarjeta);
            if (tarjeta.getCvv() == cvv && tarjeta.getFechaVencimiento().equals(fechaVencimiento)
                    && tarjeta.getNombre().equals(nombrePropetario)
                    && tarjeta.getCodigoApplePay().equals(codigoApplePay)) {
                this.card = tarjeta;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean pagar(Double monto) {

        if (this.card.isEstado()) {
            if (this.card.getSaldo() >= monto) {
                this.card.setSaldo(this.card.getSaldo() - monto);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * @return TarjetaApplePay return the card
     */
    public TarjetaApplePay getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(TarjetaApplePay card) {
        this.card = card;
    }

}
