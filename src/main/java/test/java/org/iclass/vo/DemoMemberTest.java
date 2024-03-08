package test.java.org.iclass.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.iclass.service.MemberService;
import org.iclass.vo.DemoMember;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class DemoMemberTest {

	@Test
	void test() {
//		testMemberJoin();
 	    testMemberLogin();
	}
	
	void testMemberJoin() {
		MemberService service = new MemberService();
		int result = service.join(new DemoMember(
			"gusqja4515",
			"임현범",
			"123456",
			"gusqja4515@naver.com",
			"19950712",
			"MALE",
			"010-2666-4515",
			null,
			null,
			null,
			null,
			null,
			null,
			"Y",
			null
		));
		
		log.info("회원가입 결과 : {}", result == 1 ? "성공" : "실패");
	}
	
	void testMemberLogin() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", "gusqja4515");
		map.put("password", "123456");

		MemberService service = new MemberService();
		DemoMember result = service.login(map);
		Boolean isSuccess = result != null && result.getUserid().isEmpty() == false;
		
		log.info("로그인 {}!!", isSuccess ? "성공" : "실패");
	}

}
