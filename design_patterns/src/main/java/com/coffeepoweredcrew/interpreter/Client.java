package com.coffeepoweredcrew.interpreter;

public class Client {

	public static void main(String[] args) {
		System.out.println("INTERPRETER OUTPUT");

		Report report1  = new Report("Cashflow repot", "FINANCE_ADMIN OR ADMIN");
		ExpressionBuilder builder = new ExpressionBuilder();
		
		PermissionExpression  exp = builder.build(report1);
		System.out.println(exp);
		
		User user1 = new User("Dave", "USER");
		
		System.out.println("USer access report:"+ exp.interpret(user1));
	}

}
