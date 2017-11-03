package com.minsx.authorization;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class AppTest {
	
	@Test
	public void test() {
		System.out.println(DigestUtils.md5Hex("ssss"));
	}
}
