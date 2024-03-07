package org.iclass.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.dao.CommunityDao;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PagingTest {

	@Test
	void test() {
		// 2페이지 보고싶다. 글의 총 개수 271개, 1 페이지에 글 10개
		Paging page = new Paging(2, 271, 10);
		
		log.info("page 프로퍼티 계산 결과 : {}", page);
	}
	
	@Test
	void getPageList() {
		// 2페이지의 글 목록
		Paging page = new Paging(2, 271, 12);
		Map<String, Integer> map = new HashMap<>();
		map.put("start", page.getStartNo());
		map.put("end", page.getEndNo());
		CommunityDao dao = CommunityDao.getInstance();
		List<Community> list = dao.pagelist(map);
		log.info("2페이지의 글 목록 : {}", list);
	}
	
	@Test
	void getArticle() {
		// idx 259인 글 1개 가져오기
		CommunityDao dao = CommunityDao.getInstance();
		Community article = dao.selectByIdx(259);
		log.info("idx가 259인 글 : {}", article);
	}

}
