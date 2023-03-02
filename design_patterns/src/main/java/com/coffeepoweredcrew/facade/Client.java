package com.coffeepoweredcrew.facade;

import com.coffeepoweredcrew.facade.email.*;
import com.coffeepoweredcrew.facade.email.Template.TemplateType;

public class Client {

	public static void main(String[] args) {
		System.out.println("FACADE OUTPUT >>");

		EmailFacade facade = new EmailFacade();
		Order order = new Order("101", 99.99);

		boolean result = facade.sendOrderEmail(order);
//		boolean result = sendOrderEmailWithoutFacade(order);

		System.out.println("Order Email "+ (result?"sent!":"NOT sent..."));
	}

//	private static boolean sendOrderEmailWithoutFacade(Order order) {
//		Template template = TemplateFactory.createTemplateFor(TemplateType.Email);
//		Stationary stationary = StationaryFactory.createStationary();
//		Email email = Email.getBuilder()
//					  .withTemplate(template)
//					  .withStationary(stationary)
//					  .forObject(order)
//					  .build();
//		Mailer mailer = Mailer.getMailer();
//		return mailer.send(email);
//	}
	
}
