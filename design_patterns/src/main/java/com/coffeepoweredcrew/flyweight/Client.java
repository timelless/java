package com.coffeepoweredcrew.flyweight;

import com.coffeepoweredcrew.flyweight.ErrorMessageFactory.ErrorType;

public class Client {

	public static void main(String[] args) {
		System.out.println("FLYWEIGHT OUTPUT >>");

		SystemErrorMessage m1 = ErrorMessageFactory.getInstance().getError(ErrorType.GenericSystemError);
		System.out.println(m1.getText("4586"));

		UserBannedErrorMessage m2 = ErrorMessageFactory.getInstance().getUserBannedMessage("1202");
		System.out.println(m2.getText(null));
	}

}
