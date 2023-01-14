package com.yedam.app.BookList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;

public class BookDAOIpml extends DAO implements BookDAO {
//싱글톤 //
	private static BookDAO instance = null;

	public static BookDAO getInstance() {
		if (instance == null)
			instance = new BookDAOIpml();
		return instance;
	}

	// 각각 sql문으로 실행 > try catch문 필요
	// 전체조회//
	@Override
	public List<BookVO> selectAll() {
		List<BookVO> list = new ArrayList<>();
		try {
			connect();

			stmt = conn.createStatement();
			String sql = "SELECT * FROM book_list";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BookVO bookVO = new BookVO();

				bookVO.setNumber(rs.getInt("number"));
				bookVO.setISBN(rs.getInt("isbn"));
				bookVO.setBookName(rs.getString("book_name"));
				bookVO.setAuthor(rs.getString("author"));
				bookVO.setContent(rs.getString("content"));
				bookVO.setStock(rs.getInt("stock"));
				bookVO.setNowStock(rs.getInt("now_stock"));
				list.add(bookVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 개별조회//
	@Override
	public BookVO selectOne(BookVO bookVO) {
		BookVO findVO = null;
		try {
			connect();
			String sql = "SELECT * FROM book_list WHERE isbn = ? OR book_name = ? OR author = ? OR content = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bookVO.getISBN());
			pstmt.setString(2, bookVO.getBookName());
			pstmt.setString(3, bookVO.getAuthor());
			pstmt.setString(4, bookVO.getContent());

			rs = pstmt.executeQuery(); // 위에서 이미 sql사용 돼서 재사용 될 필요 없음

			if (rs.next()) {
				findVO = new BookVO();

				findVO.setNumber(rs.getInt("number"));
				findVO.setISBN(rs.getInt("isbn"));
				findVO.setBookName(rs.getString("book_name"));
				findVO.setAuthor(rs.getString("author"));
				findVO.setContent(rs.getString("content"));
				findVO.setStock(rs.getInt("stock"));
				findVO.setNowStock(rs.getInt("now_stock"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return findVO;
	}

//ISBN조회
	// 도서등록//
	@Override
	public void insertBook(BookVO bookVO) {
		try {
			connect();
			String sql = "INSERT INTO book_list VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(2, bookVO.getISBN());
			pstmt.setString(3, bookVO.getBookName());
			pstmt.setString(4, bookVO.getAuthor());
			pstmt.setString(5, bookVO.getContent());
			pstmt.setInt(6, bookVO.getNowStock());
			pstmt.setInt(7, bookVO.getStock());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("정상적으로 등록되었습니다.");
			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 도서수정//
	@Override
	public void updateBook(BookVO bookVO) {
		try {
			connect();
			// AND는 WHERE절에서만 사용됨!!!
			String sql = "UPDATE book_list SET isbn = ? , book_name = ? , author = ? , content = ? WHERE number = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bookVO.getISBN());
			pstmt.setString(2, bookVO.getBookName());
			pstmt.setString(3, bookVO.getAuthor());
			pstmt.setString(4, bookVO.getContent());
			pstmt.setInt(5, bookVO.getNumber());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 도서삭제//
	@Override
	public void deleteBook(int isbn) {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "DELETE FROM book_list WHERE isbn = " + isbn + " AND now_stock - stock >= 0";

			int result = stmt.executeUpdate(sql);
			if (result > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			} else {
				System.out.println("*현재 대출중인 도서입니다.*");
				System.out.println("삭제할 수 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();

		}
	}

//반납시 현재 보유재고 +1만들기 
	@Override
	public void updateNowStock(String bookName) {
		try {
			connect();
			String sql = "UPDATE book_list SET now_stock = now_stock + 1 WHERE book_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookName);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("정상적으로 현재 보유 재고에 추가 되었습니다.");
			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

//대출하면 현재 보유 재고 -1
	@Override
	public void borrUpdateNowStock(BookVO bookVO) {
		try {
			connect();
			String sql = "UPDATE book_list SET now_stock = now_stock - 1 WHERE isbn = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookVO.getISBN());
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("정상적으로 현재 보유 재고가 수정되었습니다.");
			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

}
