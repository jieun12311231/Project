package com.yedam.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.admin.Borrow.BorrowDAO;
import com.yedam.admin.Borrow.BorrowVO;
import com.yedam.app.BookList.BookDAO;
import com.yedam.app.BookList.BookDAOIpml;
import com.yedam.app.BookList.BookManagement;
import com.yedam.app.BookList.BookVO;

public class Management {

	Scanner sc = new Scanner(System.in);
	BookDAO bookDAO = BookDAOIpml.getInstance();
	BorrowDAO borrDAO = BorrowDAO.getInstance();

	public void run() {
		while (true) {
			menuPrint();

			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 전체조회//
				selectAll();
			} else if (menuNo == 2) {
				// 개별조회//
				new BookManagement();
			} else if (menuNo == 3) {
				// 도서 등록
				insertBook();
			} else if (menuNo == 4) {
				// 도서 수정
				updateBook();
			} else if (menuNo == 5) {
				// 도서 삭제
				deleteBook();
			} else if (menuNo == 6) {
				// 도서 대출
				borrowBook();
			} else if (menuNo == 7) {
				// 도서 반납
				returnBook();
			} else if (menuNo == 8) {
				// 미납도서 조회
				noReturnBookList();
			} else if (menuNo == 0) {
				// 로그아웃  > 로그인 창으로 이동
				exit();
				break;
			} else {
				// 안내문구출력
				printErrorMessage();
			}
		}
	}

// 종료 exit()
	private void exit() {
		System.out.println("정상적으로 로그아웃 되었습니다.");
	}

// 안내문구 출력 printErrorMessage()
	private void printErrorMessage() {
		System.out.println("======================");
		System.out.println("메뉴를 잘못 입력하였습니다.");
		System.out.println("메뉴를 다시 한번 확인해주세요.");
		System.out.println("======================");
	}

// 메인 메뉴 출력 menuPrint()
	private void menuPrint() {
		System.out.println(
				"========================================================================================================");
		System.out.println("1.전체 조회 | 2.개별 조회 | 3.도서 등록 | 4.도서 수정 | 5.도서 삭제 | 6.도서 대출 | 7.도서 반납 | 8. 미납도서 조회 | 0.로그아웃");
		System.out.println(
				"========================================================================================================");
	}

// 메뉴 선택 menuSelect()
	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("메뉴는 숫자로 구성되어있습니다.");
			System.out.println("숫자를 입력 해 주세요.");
		}
		return menuNo;
	}

// 전체조회 selectAll()
	private void selectAll() {

		List<BookVO> list = bookDAO.selectAll();
		if (list.isEmpty()) {
			System.out.println("정보가 존재하지 않습니다.");
			return;
		}
		System.out.println("== 전체 도서 조회 ==");
		System.out.println();
		for (BookVO bookVO : list) {
			System.out.printf("도서 번호 = %d \t ISBN = %s \t 도서 제목 = %s  \t 저자 = %s \t 내용 = %s \t 총 재고 = %s권 \t현재 보유 재고 = %s권 \n",
					bookVO.getNumber(), bookVO.getISBN(), bookVO.getBookName(), bookVO.getAuthor(), bookVO.getContent(),
					bookVO.getStock(), bookVO.getNowStock());

		}
		System.out.println();
	}

// 도서 등록 insertBook() //도서관에 없는 새로운 도서 등록
	private void insertBook() {
		BookVO bookVO = inputBookAll();
		bookDAO.insertBook(bookVO);
	}

// 도서 등록 값 집어넣기
	private BookVO inputBookAll() {
		BookVO bookVO = new BookVO();

		System.out.print("ISBN > ");
		System.out.println(" * 네자리 숫자를 입력해주세요.* ");
		bookVO.setISBN(Integer.parseInt(sc.nextLine()));

		System.out.println("도서제목 > ");
		bookVO.setBookName(sc.nextLine());

		System.out.println("저자 > ");
		bookVO.setAuthor(sc.nextLine());

		System.out.println("내용 > ");
		bookVO.setContent(sc.nextLine());

		System.out.println("현재 보유 재고 > ");
		bookVO.setNowStock(Integer.parseInt(sc.nextLine()));

		System.out.println("총 재고 > ");
		bookVO.setStock(Integer.parseInt(sc.nextLine()));

		return bookVO;

	}

// 도서 수정 updateBook()
	private void updateBook() {
		BookVO bookVO = inputBookInfo();
		bookDAO.updateBook(bookVO);
	}

//도서 수정할 데이터 입력
	private BookVO inputBookInfo() {
		BookVO bookVO = new BookVO();

		System.out.println("수정할 도서의 번호 >");
		bookVO.setNumber(Integer.parseInt(sc.nextLine()));

		System.out.println("ISBN >");
		bookVO.setISBN(Integer.parseInt(sc.nextLine()));

		System.out.println("도서 제목 > ");
		bookVO.setBookName(sc.nextLine());

		System.out.println("저자 > ");
		bookVO.setAuthor(sc.nextLine());

		System.out.println("내용 > ");
		bookVO.setContent(sc.nextLine());

		return bookVO;

	}

// 도서 삭제 deleteBook()
// ISBN입력하면 도서 삭제
	private void deleteBook() {
		int isbn = inputIsbn();
		bookDAO.deleteBook(isbn);
	}

	private int inputIsbn() {
		int isbn = 0;
		System.out.println("ISBN > ");
		try {
			isbn = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("ISBN는 숫자로 구성되어있습니다.");
		}
		return isbn;
	}

// 도서 대출 borrowBook()
// ISBN, 책 제목 입력하면 대출 가능 여부 출력
	public void borrowBook() {

		BookVO findBook = inputBookISBN();
		BookVO bookVO = bookDAO.selectOne(findBook);
		// 도서관에 등록되지않은 도서의 ISBN을 입력했을때 //
		if (bookVO == null) {
			System.out.println("해당 도서가 존재하지않습니다.");
			System.out.println("ISBN을 다시 한번 확인 해 주세요.");
			return;
		}
		// 도서관에 등록된 도서지만 현재보유 재고가 없는 경우//
		if (bookVO.getNowStock() == 0) {
			System.out.println("현재 대출 가능한 재고가 부족합니다.");
			return;
			// 현재 대출가능한 도서//
		} else if (bookVO.getNowStock() != 0) {
			System.out.println(" *대출 가능한 도서입니다.* ");
			System.out.println();
			// borrow_book테이블에 데이터 추가
			BorrowVO info = new BorrowVO();

			System.out.println("대출 일자 >");
			info.setBorforDate(sc.nextLine());

			System.out.print("연락처 > ");
			System.out.println(" *휴대폰번호 뒷자리 네자리만 입력하여주세요.* ");
			info.setBorTel(Integer.parseInt(sc.nextLine()));

			System.out.println("도서 제목 >");
			info.setBookInfomation(sc.nextLine());

			System.out.println("반납 예정 일자 >");
			info.setBortoDate(sc.nextLine());
			// 1은 대출중, 0은 반납
			System.out.println("대출여부 > ");
			info.setBorStatus(Integer.parseInt(sc.nextLine()));
			// 대출 정보 등록
			borrDAO.inserbor(info);
			// 대출이 되면 book_list 테이블의 now_stock에서 -1이 되도록
			bookDAO.borrUpdateNowStock(findBook);
		}

	}

//ISBN입력받기//
	private BookVO inputBookISBN() {
		BookVO bookVO = new BookVO();
		System.out.println("ISBN >");
		bookVO.setISBN(Integer.parseInt(sc.nextLine()));
		return bookVO;
	}

//미납도서 조회//
	public void noReturnBookList() {
		List<BorrowVO> list = borrDAO.selectAll();
		System.out.println("== 미납도서 목록 ==");
		System.out.println();
		for (BorrowVO borrowVO : list) {
			System.out.printf("대출 일자 = %s \t 연락처 = %d \t 도서 제목 = %s  \t 반납 예정 일자 = %s \t 대출 여부 = %d \n",
					borrowVO.getBorforDate(), borrowVO.getBorTel(), borrowVO.getbookName(), borrowVO.getBortoDate(),
					borrowVO.getBorStatus());
		}
		System.out.println();
	}

	// 도서 반납 returnBook(); 대출여부 : 대출중 1 /반납시 0
	// >>연락처, 책제목 입력하면 미납여부 출력
	// 정상반납 시 대출여부 0으로 출력 >>0으로 출력되는 항목은 삭제
	public void returnBook() {
		// 연락처 입력하면 대출여부 0//
		// 연락처 먼저 입력하면 반납 도서 유무 출력
		int bookTel = inputTel();
		String bookName = inputName();
		BorrowVO book = borrDAO.selectOne(bookTel, bookName);

		if (book == null) {
			System.out.println("반납가능한 도서가 없습니다.");
			return;
		} else {

			// 도서 대출 0만들기 >>연락처, 책제목 같으면 대출여부 0으로 변경
			borrDAO.updateSatus(book);

			// 대출여부 0인 도서 미납도서 목록에서 지우기
			borrDAO.deleteReturnBool(bookTel);

			// 반납하면 보유재고 1로 만들기
			// 반납되는 도서에서 책 제목이 같으면
			bookDAO.updateNowStock(bookName);
		}

	}

	private int inputTel() {
		System.out.println("반납 할 연락처 > ");
		return Integer.parseInt(sc.nextLine());
	}

	private String inputName() {
		System.out.println("반납 할 도서 제목 > ");
		return sc.nextLine();
	}
}
