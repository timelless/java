package com.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MockTest {

	@Test
	void test() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25, 15, 5});

		BusinessClass businessClass = new BusinessClass(dataServiceMock);
		int result = businessClass.findGreatest();
		assertEquals(25, result);
	}

}