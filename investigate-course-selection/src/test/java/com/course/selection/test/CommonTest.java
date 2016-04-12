package com.course.selection.test;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� E");
		System.out.println(sdf.format(new Date()));
	}

}
