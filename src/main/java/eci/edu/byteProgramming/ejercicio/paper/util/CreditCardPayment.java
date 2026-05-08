package eci.edu.byteProgramming.ejercicio.paper.util;

public class CreditCardPayment extends PaymentMethod {

    private String number;
    private String name;
    private String expirationDate;
    private String cvv;
    private String address;

    public CreditCardPayment(
            double amount,
            String customerID,
            String description,
            String number,
            String name,
            String expirationDate,
            String cvv,
            String address) {

        super(amount, customerID, description);

        this.number = number;
        this.name = name;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.address = address;
    }

    @Override
    public boolean validatePaymentMethod() {

        return number != null
                && cvv != null
                && expirationDate != null;
    }

    @Override
    public boolean processPayment() {

        System.out.println("Processing Credit Card payment...");

        if (!validatePaymentMethod()) {

            setStatus(PaymentStatus.FAILED);
            return false;
        }

        setStatus(PaymentStatus.PROCESSING);

        try {

            Thread.sleep(500);

            System.out.println(
                    "Payment approved for card: ****1111"
            );

            setStatus(PaymentStatus.COMPLETED);

            return true;

        } catch (Exception e) {

            setStatus(PaymentStatus.FAILED);

            return false;
        }
    }

    @Override
    public String getPaymentMethod() {
        return "CREDIT_CARD";
    }
}