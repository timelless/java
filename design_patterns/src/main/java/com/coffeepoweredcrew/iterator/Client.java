package com.coffeepoweredcrew.iterator;

public class Client {

	public static void main(String[] args) {
		System.out.println("ITERATOR OUTPUT");

		Iterator<ThemeColor> iterator = ThemeColor.getIterator();

		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
