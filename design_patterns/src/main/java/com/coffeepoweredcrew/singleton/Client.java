package com.coffeepoweredcrew.singleton;

public class Client {

	public static void main(String[] args) {
		System.out.println("EAGER SINGLETON OUTPUT >>");

		EagerRegistry registry = EagerRegistry.getInstance();
		EagerRegistry registry2 = EagerRegistry.getInstance();
		System.out.println(registry == registry2);

		LazyRegistryWithDCL lz1 = LazyRegistryWithDCL.getInstance();
		LazyRegistryWithDCL lz2 = LazyRegistryWithDCL.getInstance();
		System.out.println(registry == registry2);

		LazyRegistryIODH iodh1 = LazyRegistryIODH.getInstance();
		LazyRegistryIODH iodh2 = LazyRegistryIODH.getInstance();
		System.out.println(iodh1 == iodh2);
	}

}
