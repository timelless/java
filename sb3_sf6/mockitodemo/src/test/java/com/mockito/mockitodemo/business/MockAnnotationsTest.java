package com.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MockAnnotationsTest {

	@Mock
	private DataService dataServiceMock;
	@InjectMocks
	private BusinessClass businessClass;

	@Test
	void test() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25, 15, 5});
		int result = businessClass.findGreatest();
		assertEquals(25, result);
	}

}