package com.yedam.admin;
//로그인
import java.sql.SQLException;

import com.yedam.app.common.DAO;

public class AdminDAO extends DAO{
	
	private static AdminDAO admin = null;
	private AdminDAO() {}
	public static AdminDAO getInstance() {
		if(admin == null) {
			admin = new AdminDAO();
		}
		return admin;
	}
	
	//관리자 정보 들고오기
	public AdminVO selectOne(AdminVO admin) {
		AdminVO loginInfo = null;
		//아이디 가지고오기
		try {
			connect();
			String sql = "SELECT * FROM admin WHERE admin_id = '"+admin.getAdminId()+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//아이디 존재확인
			if(rs.next()) {
				//비밀번호 확인
				if(rs.getInt("admin_password") == admin.getAdminPassword()) {
					loginInfo = new AdminVO();
					loginInfo.setAdminId(rs.getString("admin_id"));
					loginInfo.setAdminPassword(rs.getInt("admin_password"));
				}else {
					//비밀번호 맞지않을경우 문구 출력
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			}else {
				//아이디 없을 경우 문구 출력
				System.out.println("아이디가 존재하지 않습니다.");
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}	
		return loginInfo;
	}
}
