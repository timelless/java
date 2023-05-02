package com.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StubTest {

	@Test
	void test() {
		BusinessClass businessClass = new BusinessClass(new DataServiceStub());
		int result = businessClass.findGreatest();
		assertEquals(25, result);
	}

}

class DataServiceStub implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[] {25, 15, 5};
	}
}