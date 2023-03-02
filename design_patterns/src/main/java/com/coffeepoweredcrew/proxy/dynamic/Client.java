package com.coffeepoweredcrew.proxy.dynamic;

import com.coffeepoweredcrew.proxy.Image;
import javafx.geometry.Point2D;

public class Client {

	public static void main(String[] args) {
		System.out.println("DYNAMIC PROXY OUTPUT");

		Image img = new ImageFactory().getImage();
		img.setLocation(new Point2D(-10, 0));
		
	}
}
