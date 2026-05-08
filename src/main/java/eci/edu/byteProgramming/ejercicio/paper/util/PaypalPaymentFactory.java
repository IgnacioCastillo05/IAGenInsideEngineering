package eci.edu.byteProgramming.ejercicio.paper.util;

public class PaypalPaymentFactory implements PaymentFactory {

    @Override
    public PaymentMethod createPaymentMethod(
            double amount,
            String customerId,
            String description) {

        return new PaypalPayment(
                amount,
                customerId,
                description,
                "cliente@gmail.com",
                "TOKEN123"
        );
    }
}