package com.yedam.app.BookList;

import java.util.Scanner;

import com.yedam.app.Management;

public class BookManagement extends Management {
//개별조회//
	Scanner sc = new Scanner(System.in);
	BookDAO bookDAO = BookDAOIpml.getInstance();

	public BookManagement() {

		while (true) {

			menuPrint();

			int menuNo = menuSelect();

			if (menuNo == 1) {
				// ISBN
				selectISBN();
			} else if (menuNo == 2) {
				// 도서 제목
				selectBookName();
			} else if (menuNo == 3) {
				// 저자
				selectAuthor();
			} else if (menuNo == 4) {
				// 내용
				selectContent();

			} else if (menuNo == 0) {
				// 메인메뉴로 이동 //beak;만걸어줘도 가능
				new Management().run();
				break;
			} else {
				// 안내문구 출력
				printErrorMessage();
			}
		}

	}

// 단건조회//
	// ISBN 
	private void selectISBN() {
		BookVO findBook = inputBookInfo();
		BookVO bookVO = bookDAO.selectOne(findBook);
		if (bookVO == null) {
			System.out.println("해당도서는 존재하지 않습니다.");
		} else {
			System.out.print("검색결과 >> ");
			System.out.println(bookVO);
		}
	}

	private BookVO inputBookInfo() {
		BookVO bookVO = new BookVO();
		System.out.println("ISBN >");
		bookVO.setISBN(Integer.parseInt(sc.nextLine()));
		return bookVO;
	}

	// 도서 제목
	private void selectBookName() {
		BookVO findBook = inputBookName();
		BookVO bookVO = bookDAO.selectOne(findBook);
		if (bookVO == null) {
			System.out.println("해당도서는 존재하지 않습니다.");
		} else {
			System.out.print("검색결과 >> ");
			System.out.println(bookVO);
		}
	}

	private BookVO inputBookName() {
		BookVO bookVO = new BookVO();
		System.out.println("도서 제목 >");
		bookVO.setBookName(sc.nextLine());
		return bookVO;
	}

	// 저자
	private void selectAuthor() {
		BookVO findBook = inputBookAuthor();
		BookVO bookVO = bookDAO.selectOne(findBook);
		if (bookVO == null) {
			System.out.println("해당도서는 존재하지 않습니다.");
		} else {
			System.out.print("검색결과 >> ");
			System.out.println(bookVO);
		}
	}

	private BookVO inputBookAuthor() {
		BookVO bookVO = new BookVO();
		System.out.println("저자 >");
		bookVO.setAuthor(sc.nextLine());
		return bookVO;
	}

	// 내용
	// 내용이 같은 책들 다 출력되도록
	private void selectContent() {

		BookVO findBook = inputBookContent();
		BookVO bookVO = bookDAO.selectOne(findBook);
		if (bookVO == null) {
			System.out.println("해당도서는 존재하지 않습니다.");
		} else {
			System.out.print("검색결과 >> ");
			System.out.println(bookVO);
		}
	}

	private BookVO inputBookContent() {
		BookVO bookVO = new BookVO();
		System.out.println("내용 >");
		bookVO.setContent(sc.nextLine());
		return bookVO;
	}

	// 메뉴 출력//
	private void menuPrint() {
		System.out.println("============================================");
		System.out.println("1.ISBN | 2.도서 제목 | 3.저자 | 4.내용 | 0.뒤로가기");
		System.out.println("============================================");
	}

	// 안내문구 출력//
	private void printErrorMessage() {
		System.out.println("======================");
		System.out.println("메뉴를 잘못 입력하였습니다.");
		System.out.println("메뉴를 다시 한번 확인해주세요.");
		System.out.println("======================");
	}

	// 메뉴 선택
	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("메뉴는 숫자로 구성되어있습니다.");
		}
		return menuNo;
	}

}
