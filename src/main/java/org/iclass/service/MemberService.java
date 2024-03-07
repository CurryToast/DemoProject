package org.iclass.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.iclass.dao.DemoMemberDao;
import org.iclass.vo.DemoMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	private DemoMemberDao dao = DemoMemberDao.getInstance();
	
	// password 해싱
	public int join(DemoMember member) {
		int result = 0;

		try {
			String password = encrypt(member.getPassword());
			member.setPassword(password);
			result = dao.join(member);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	// password 해싱
	public DemoMember login(Map<String,String> map) {
		DemoMember result=null;
		try {
			String password = encrypt(map.get("password"));
			map.put("password", password);
			result = dao.login(map);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 암호화 : 평문 -> 알고리즘 -> 암호문(가독X)
	// 복호화 : 암호문 -> 알고리즘 -> 평문(가독O)
	// 해싱 : 평문 -> 해시함수 -> 같은 평문에 대해서는 가독 불가능한 동일한 암호문
	//		ㄴ 암호화된 문자열을 평문으로 복구 불가능
	//		ㄴ sha256 해시함수 : 암호 문자열 256비트 16진수 64개 문자
	public String encrypt(String text) throws NoSuchAlgorithmException {
		// 자바에서는 MessageDigest 클래스가 해시함수를 제공
		// 1) 실행 객체 생성
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // 2) 평문을 바이트 배열로 변환하여 저장
        md.update(text.getBytes());
        
        // 3) md.digest 메소드가 해시함수를 실행 => 해싱 결과가 바이트 배열
        return bytesToHex(md.digest());
    }

	// 바이트 배열을 16진수 문자열로 변환
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
        	// 바이트 배열에서 1바이트씩 가져와서 16진수 2자리 문자로 변환
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }

}
