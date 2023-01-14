package com.yedam.app.BookList;

import java.util.List;

public interface BookDAO {

	//전체조회//
	List<BookVO> selectAll();
	
	//개별조회//
	BookVO selectOne(BookVO bookVO);
	
	//도서등록//
	void insertBook(BookVO bookVO);
	
	//도서수정
	void updateBook(BookVO bookVO);

	//도서 삭제 / 책 isbn 받아서 삭제 되도록
	void deleteBook(int isbn);

	//반납시 현재 보유 재고 +1 만들기 
	void updateNowStock(String bookName);
	
	//대출하면 현재 보유 재고 -1 만들기 
	void borrUpdateNowStock(BookVO bookVO);


	
}
