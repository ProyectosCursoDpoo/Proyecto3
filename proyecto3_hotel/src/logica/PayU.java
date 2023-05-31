package logica;

import java.util.HashMap;

public class PayU extends PasarelaPago {
    private TarjetaPayU card;
    private int monto;

    // Método para realizar el pago simulado con PayU

    public boolean verificarTarjeta(HashMap<String, TarjetaPayU> tarjetas, String numTarjeta) {

        // Verificar que la tarjeta exista en la base de datos de PayU
        if (tarjetas.containsKey(numTarjeta)) {
            this.card = tarjetas.get(numTarjeta);

            return true;
        } else {
            return false;
        }

    }

    public boolean pagar(TarjetaPayU card, double monto) {
        // Simulación del proceso de pago con PayU
        // Aquí puedes incluir la lógica que desees para simular el proceso de pago con
        // PayU

        // Verificar que la tarjeta tenga fondos suficientes para el monto a pagar
        System.out.println(monto);
        System.out.println(card.getSaldo());
        if (card.getSaldo() >= monto) {
            // Restar el monto del saldo de la tarjeta
            card.setSaldo(card.getSaldo() - monto);

            // Retorno exitoso del pago simulado
            return true;
        } else {
            // Registro de la transacción fallida en un archivo de registro específico para
            // PayU
            // ...

            // Retorno de pago fallido debido a fondos insuficientes
            return false;
        }
    }

    /**
     * @return TarjetaPayU return the card
     */
    public TarjetaPayU getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(TarjetaPayU card) {
        this.card = card;
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
