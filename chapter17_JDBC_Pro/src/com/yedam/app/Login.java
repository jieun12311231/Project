package com.yedam.app;

import java.util.Scanner;

import com.yedam.admin.AdminDAO;
import com.yedam.admin.AdminVO;

public class Login {
	private Scanner sc = new Scanner(System.in);

	// 싱글톤 형태 만들어서 로그인 정보 계속 가지고 갈 수 있도록 만들기
	// 필드는 숨겨놓고 얘로만 출력 얘로 하나의 제품만 생산하겠다.
	private static AdminVO loginInfo = null;

	private static AdminVO getLoginInfo() {
		return loginInfo;
	}

	// 로그인
	public Login() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 로그인
				login();
			} else if (menuNo == 0) {
				// 종료
				exit();
				break;
			} else {
				// 안내문구 출력
				printErrorMessage();
			}

		}
	}

	// 메뉴출력 menuPrint()
	private void menuPrint() {
		System.out.println("===============");
		System.out.println("1.로그인 | 0.종료");
		System.out.println("===============");
		System.out.println("메뉴선택 > ");

	}

	// 메뉴 선택 menuSelect()
	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println();
		}
		return menuNo;
	}

	// 로그인 login()
	private void login() {
		AdminVO inputInfo = inputAdmin();

		// 로그인 성공
		loginInfo = AdminDAO.getInstance().selectOne(inputInfo);

		// 로그인 실패하면 종료
		if (loginInfo == null)
			return;

		// 로그인 성공하면 프로그램 실행.
		new Management().run();

	}

	// login 에 정보 집어넣기
	private AdminVO inputAdmin() {
		AdminVO info = new AdminVO();
		System.out.print("id > ");
		info.setAdminId(sc.nextLine());
		System.out.println("password > ");
		info.setAdminPassword(Integer.parseInt(sc.nextLine()));

		return info;
	}

	// 종료 exit()
	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	// 안내문구 출력 printErrorMessage()
	private void printErrorMessage() {
		System.out.println("======================");
		System.out.println("메뉴를 잘못 입력하였습니다.");
		System.out.println("메뉴를 다시 한번 확인해주세요.");
		System.out.println("======================");
	}

}
