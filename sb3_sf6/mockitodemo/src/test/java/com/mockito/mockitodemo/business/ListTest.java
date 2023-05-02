package com.mockito.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ListTest {
	@Test
	void test() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(3);
		assertEquals(3, listMock.size());
	}

	@Test
	void multiple() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(1).thenReturn(2);
		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());

	}

	@Test
	void parameters() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn(55);
		assertEquals(55, listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	@Test
	void genericParameters() {
		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn(77);
		assertEquals(77, listMock.get(0));
		assertEquals(77, listMock.get(1));
		assertEquals(77, listMock.get(11));
	}
}