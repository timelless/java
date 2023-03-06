package com.coffeepoweredcrew.chainofresponsibility;

import java.time.LocalDate;

public class Client {

	public static void main(String[] args) {
		System.out.println("CHAIN OF RESPONSIBILITY OUTPUT");

		LeaveApplication application = LeaveApplication.getBuilder()
				.withType(LeaveApplication.Type.Sick)
				.from(LocalDate.now())
				.to(LocalDate.of(2018, 2, 28))
				.build();

		System.out.println(application);

		LeaveApprover approver = createChain();
		approver.processLeaveApplication(application);

		System.out.println(application);
	}

	private static LeaveApprover createChain() {
		Director director = new Director(null);
		Manager manager = new Manager(director);
		ProjectLead lead = new ProjectLead(manager);

		return lead;
	}
}
