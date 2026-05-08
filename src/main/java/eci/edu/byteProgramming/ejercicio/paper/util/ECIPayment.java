package eci.edu.byteProgramming.ejercicio.paper.util;

import java.util.ArrayList;
import java.util.List;

public class ECIPayment {

    private List<PaymentObserver> observers;

    public ECIPayment() {

        observers = new ArrayList<>();
    }

    public void addObserver(PaymentObserver observer) {

        observers.add(observer);
    }

    public boolean processPayment(
            PaymentFactory factory,
            double amount,
            String customerId,
            String description,
            String customerName,
            String customerEmail,
            String productId) {

        PaymentMethod payment =
                factory.createPaymentMethod(
                        amount,
                        customerId,
                        description
                );

        boolean success = payment.processPayment();

        if (success) {

            for (PaymentObserver observer : observers) {

                observer.onPaymentSuccess(
                        payment,
                        customerName,
                        customerEmail,
                        productId
                );
            }

        } else {

            for (PaymentObserver observer : observers) {

                observer.onPaymentFailed(
                        payment,
                        customerEmail
                );
            }
        }

        return success;
    }
}