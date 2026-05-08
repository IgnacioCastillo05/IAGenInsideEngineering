package eci.edu.byteProgramming.ejercicio.paper.util;

public class CryptoPayment extends PaymentMethod {

    private String walletAddress;
    private String cryptoType;
    private double walletBalance;

    public CryptoPayment(
            double amount,
            String customerId,
            String description,
            String walletAddress,
            String cryptoType,
            double walletBalance) {

        super(amount, customerId, description);

        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;
        this.walletBalance = walletBalance;
    }

    @Override
    public boolean validatePaymentMethod() {

        return walletBalance >= amount;
    }

    @Override
    public boolean processPayment() {

        System.out.println("Processing Crypto payment...");

        if (!validatePaymentMethod()) {

            setStatus(PaymentStatus.FAILED);
            return false;
        }

        setStatus(PaymentStatus.PROCESSING);

        try {

            Thread.sleep(500);

            System.out.println(
                    "Crypto transaction approved"
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
        return "CRYPTO";
    }
}