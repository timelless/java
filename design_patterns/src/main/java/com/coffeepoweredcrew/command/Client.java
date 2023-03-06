package com.coffeepoweredcrew.command;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("COMMAND OUTPUT");

		EWSService service = new EWSService();
		Command c1 = new AddMemberCommand("test@test.com", "spam", service);
		MailTasksRunner.getInstance().addCommand(c1);

		Command c2 = new AddMemberCommand("test2@test.com", "spam", service);
		MailTasksRunner.getInstance().addCommand(c2);

		Thread.sleep(3000);
		MailTasksRunner.getInstance().shutdown();
	}
}
