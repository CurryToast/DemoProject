package org.iclass.controller.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iclass.controller.Controller;
import org.iclass.dao.BookcaseDao;
import org.iclass.vo.BookcaseBook;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookCaseSaveController implements Controller {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 업로드한 파일이 저장될 서버 경로 (파일 시스템 경로)
		// 서버의 로컬 경로에 대해 url을 지정합니다 -> 톰캣 server.xml
		//						ㄴ <Context docBase="D:/hyeonbeom/upload" path="/upload" />
		String path = "D:\\hyeonbeom\\upload";
		int maxSize = 10*1024*1024;

		MultipartRequest multiRequest = new MultipartRequest(
			request,
			path,
			maxSize,
			"UTF-8",
			new DefaultFileRenamePolicy()
		);

		String bcode = multiRequest.getParameter("bcode");
		String title = multiRequest.getParameter("title");
		String writer = multiRequest.getParameter("writer");
		String publisher = multiRequest.getParameter("publisher");
		String pubdate = multiRequest.getParameter("pubdate");
		String saleuser = multiRequest.getParameter("saleuser");
		int price = Integer.parseInt(multiRequest.getParameter("price"));
		
		String cover = multiRequest.getFilesystemName("cover");
		
		BookcaseBook book = new BookcaseBook(bcode, title, writer, publisher, pubdate, saleuser, price, cover, null);
		log.info("::: BOOK-{} :::", book);
		
		BookcaseDao dao = BookcaseDao.getInstance();
		int result = 0;
		String url = "bookcase";

		try {
			result = dao.register(book);

			if (result != 1) {
				// todo : 이전 예제 참고해서 등록 실패하면 쿠키 이용해서 메세지를 전달해주세요.
				url = "new";
			}
		} catch (Exception e) {
			log.info("도서 등록 예외 : {}", e.getMessage());
		}

		response.sendRedirect(url);
	}
}
