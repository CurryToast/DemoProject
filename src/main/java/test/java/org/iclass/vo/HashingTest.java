package test.java.org.iclass.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.iclass.service.MemberService;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class HashingTest {

	@Test
	void test() {
		testHashing();
	}
	
	void testHashing() {
		MemberService service = new MemberService();
		String hashValue = null;
		StringBuffer str = new StringBuffer("1111");
		
		try {
			hashValue = service.encrypt(str.toString());
			log.info("{}의 해시 값 : {}", str, hashValue);
			
			str = new StringBuffer("thGH123!@4");
			hashValue = service.encrypt(str.toString());
			log.info("{}의 해시 값 : {}", str, hashValue);
		} catch (Exception e) {
			log.info("암호화 예외 : {}", e.getMessage());
		}
	}

}
