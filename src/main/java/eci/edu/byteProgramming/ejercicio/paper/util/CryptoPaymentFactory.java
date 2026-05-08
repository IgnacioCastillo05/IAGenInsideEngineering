package eci.edu.byteProgramming.ejercicio.paper.util;

public class CryptoPaymentFactory implements PaymentFactory {

    @Override
    public PaymentMethod createPaymentMethod(
            double amount,
            String customerId,
            String description) {

        return new CryptoPayment(
                amount,
                customerId,
                description,
                "WALLET123456",
                "BITCOIN",
                5000
        );
    }
}