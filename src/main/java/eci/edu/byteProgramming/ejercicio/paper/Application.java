package eci.edu.byteProgramming.ejercicio.paper;

import eci.edu.byteProgramming.ejercicio.paper.util.*;

public class Application {

	public static void main(String[] args) {

		Inventory inventory = new Inventory();

		Facturation facturation = new Facturation();

		Notification notification = new Notification();

		PaymentObserver observer =
				new PaymentEventObserver(
						inventory,
						facturation,
						notification
				);

		ECIPayment paymentSystem = new ECIPayment();

		paymentSystem.addObserver(observer);

		PaymentFactory factory =
				new CreditCardPaymentFactory();

		paymentSystem.processPayment(
				factory,
				1200,
				"123456",
				"Gaming Laptop",
				"Juan Perez",
				"juan@gmail.com",
				"LAPTOP001"
		);
	}
}