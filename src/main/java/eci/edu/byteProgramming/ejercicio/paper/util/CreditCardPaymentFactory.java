package eci.edu.byteProgramming.ejercicio.paper.util;

public class CreditCardPaymentFactory implements PaymentFactory {

    @Override
    public PaymentMethod createPaymentMethod(
            double amount,
            String customerId,
            String description) {

        return new CreditCardPayment(
                amount,
                customerId,
                description,
                "4111111111111111",
                "Juan Perez",
                "12/28",
                "123",
                "Bogotá"
        );
    }
}