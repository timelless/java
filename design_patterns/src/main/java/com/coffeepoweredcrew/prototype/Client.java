package com.coffeepoweredcrew.prototype;

public class Client {

	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println("PROTOTYPE OUTPUT >>");

        Swordsman sm = new Swordsman();
		sm.move(new Point3D(-10, 0, 0), 20);
		sm.attack();
		System.out.println(sm);

		Swordsman sm2 = (Swordsman) sm.clone();
		System.out.println(sm2);
	}

}
