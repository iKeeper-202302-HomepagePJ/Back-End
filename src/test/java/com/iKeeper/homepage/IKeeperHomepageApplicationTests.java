package com.iKeeper.homepage;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IKeeperHomepageApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void stringEncryptor() {

		String url = "jdbc:mysql://172.31.34.128:3306/ikeeper_homepage?serverTimezone=UTC&characterEncoding=UTF-8";
		String username = "cprtmchzh";
		String password = "tjsqls07251107@";
		String jwt = "Vm0xd1IxVXhVWGhTV0d4WFlrZG9WMWxVU205VU1WcHhWR3RPYVUxV1ZqTlpWVlpQWVRGd05rMUVhejA9";

		System.out.println(jasyptEncoding(url));
		System.out.println(jasyptEncoding(username));
		System.out.println(jasyptEncoding(password));
		System.out.println(jasyptEncoding(jwt));
	}

	public String jasyptEncoding(String value) {

		String key = "my_jasypt_key";
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
		pbeEnc.setPassword(key);
		pbeEnc.setIvGenerator(new RandomIvGenerator());
		return pbeEnc.encrypt(value);
	}
}
