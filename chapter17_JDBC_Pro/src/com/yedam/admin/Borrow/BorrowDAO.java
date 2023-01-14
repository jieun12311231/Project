package com.yedam.admin.Borrow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;

public class BorrowDAO extends DAO {
//싱글톤//
	private static BorrowDAO dao = null;

	private BorrowDAO() {
	}

	public static BorrowDAO getInstance() {
		if (dao == null) {
			dao = new BorrowDAO();
		}
		return dao;
	}

	// 대출도서 등록
	public void inserbor(BorrowVO info) {
		try {
			connect();
			String sql = "INSERT INTO borrow_book VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, info.getBorforDate());
			pstmt.setInt(2, info.getBorTel());
			pstmt.setString(3, info.getbookName());
			pstmt.setString(4, info.getBortoDate());
			pstmt.setInt(5, info.getBorStatus());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(" ** 대출 완료 ** ");
			} else {
				System.out.println("정상적으로 대출되지 않았습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 도서 반납 단건 조회
	public BorrowVO selectOne(int borTel, String bookName) {
		BorrowVO borrow = null;
		try {
			connect();
			String sql = "SELECT * FROM borrow_book WHERE bor_tel = ? AND book_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, borTel);
			pstmt.setString(2, bookName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				borrow = new BorrowVO();
				borrow.setBorforDate(rs.getString("borfor_date"));
				borrow.setBorTel(rs.getInt("bor_tel"));
				borrow.setBookInfomation(rs.getString("book_name"));
				borrow.setBortoDate(rs.getString("borto_date"));
				borrow.setBorStatus(rs.getInt("bor_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return borrow;
	}

//대출 목록 출력
	public List<BorrowVO> selectAll() {
		List<BorrowVO> list = new ArrayList<>();
		try {
			connect();

			stmt = conn.createStatement();
			String sql = "SELECT * FROM borrow_book";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BorrowVO borrowVO = new BorrowVO();

				borrowVO.setBorforDate(rs.getString("borfor_date"));
				borrowVO.setBorTel(rs.getInt("bor_tel"));
				borrowVO.setBookInfomation(rs.getString("book_name"));
				borrowVO.setBortoDate(rs.getString("borto_date"));
				borrowVO.setBorStatus(rs.getInt("bor_status"));
				list.add(borrowVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}

	// 대출테이블의 대출여부 0으로 바꾸기 >> 반납 완료
	public void updateSatus(BorrowVO borrowVO) {
		try {
			connect();
			String sql = "UPDATE borrow_book SET bor_status = 0 WHERE bor_tel = ? AND book_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, borrowVO.getBorTel());
			pstmt.setString(2, borrowVO.getbookName());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(" ** 반납 완료 ** ");
			} else {
				System.out.println("정상적으로 반납되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}

	//대출테이블에서 대출여부가 0이면 지우기 
	public void deleteReturnBool(int borTel) {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "DELETE FROM borrow_book WHERE bor_tel = " + borTel + " AND bor_status = 0";  //empNo변수로 넘겨주기
			
			int result =stmt.executeUpdate(sql);
			if(result > 0) {
				System.out.println("정상적으로 미납 도서 목록에서 삭제되었습니다.");
			}else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
			
		}
	}
}
