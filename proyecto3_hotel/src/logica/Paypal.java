package logica;

import java.util.HashMap;

public class Paypal extends PasarelaPago {
    private TarjetaPayPal card;
    // private double monto;

    public boolean verificarTarjeta(HashMap<String, TarjetaPayPal> tarjetas, String numTarjeta) {

        // Verificar que la tarjeta exista en la base de datos de PayU
        // for (Object k : tarjetas.keySet()) {
        // System.out.println(k);
        // TarjetaPayPal t = tarjetas.get(k);
        // System.out.println(t.getSaldo());
        // }
        if (tarjetas.containsKey(numTarjeta)) {
            this.card = tarjetas.get(numTarjeta);

            return true;
        } else {
            return false;
        }

    }

    public boolean pagar(double monto, String tokenAutenticacion) {
        // Verificar que la tarjeta antes validada esta activa
        if (this.card.isEstado()) {
            // Si la tarjeta esta activa, se procede a revisar si el token es el correcto
            boolean autenticacion = autentificarTarjeta(this.card, tokenAutenticacion);
            System.out.println(autenticacion);
            if (autenticacion) {
                // si el token es correcto, se procede a verificar que la tarjeta tenga fondos
                // suficientes para el monto a pagar
                boolean pagoExitoso = procesarPago(monto);
                if (pagoExitoso) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // si el token es incorrecto, se retorna un error
                return false;
            }
        } else {
            // si la tarjeta no esta activa, se retorna un error
            return false;
        }

    }

    public boolean autentificarTarjeta(TarjetaPayPal card, String tokenAutenticacion) {
        // System.out.println(card.getLlaveAutenticacion());
        // System.out.println(tokenAutenticacion);
        if (card.getLlaveAutenticacion().equals(tokenAutenticacion)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean procesarPago(double monto) {
        // Simulación del proceso de pago con PayPal

        // Verificar que la tarjeta tenga fondos suficientes para el monto a pagar

        if (this.card.getSaldo() >= monto) {
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
}
