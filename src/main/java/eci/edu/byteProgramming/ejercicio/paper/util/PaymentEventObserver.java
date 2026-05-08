package eci.edu.byteProgramming.ejercicio.paper.util;

public class PaymentEventObserver implements PaymentObserver {

    private Inventory inventory;
    private Facturation facturation;
    private Notification notification;

    public PaymentEventObserver(
            Inventory inventory,
            Facturation facturation,
            Notification notification) {

        this.inventory = inventory;
        this.facturation = facturation;
        this.notification = notification;
    }

    @Override
    public void onPaymentSuccess(
            PaymentMethod payment,
            String customerName,
            String customerEmail,
            String productId) {

        Product product = inventory.getProduct(productId);

        if (product != null) {
            inventory.discountProduct(productId, 1);
        }

        String productDetails =
                product != null ? product.getName() : "Unknown Product";

        facturation.generateInvoice(
                payment,
                customerName,
                productDetails
        );

        notification.sendConfirmationEmail(
                customerEmail,
                customerName,
                payment
        );
    }

    @Override
    public void onPaymentFailed(
            PaymentMethod payment,
            String customerEmail) {

        notification.sendFailureNotification(
                payment,
                customerEmail
        );
    }
}