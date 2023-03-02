package com.coffeepoweredcrew.proxy;

import javafx.geometry.Point2D;

public class Client {

	public static void main(String[] args) {
		System.out.println("VIRTUAL PROXY OUTPUT");

		Image img = ImageFactory.getImage("A1.bmp");
		
		img.setLocation(new Point2D(10,10));
		System.out.println("Image location :"+img.getLocation());
		System.out.println("rendering image now.....");
		img.render();
	}

}
