package com.coffeepoweredcrew.bridge;

public class Client {

	public static void main(String[] args) {
		System.out.println("BRIDGE OUTPUT >>");
		FifoCollection<Integer> collection = new Queue<>(new SinglyLinkedList<>());
		collection.offer(10);
		collection.offer(20);
		collection.offer(30);

		System.out.println(collection.poll());
		System.out.println(collection.poll());
		System.out.println(collection.poll());
	}

}
