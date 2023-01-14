package com.yedam.admin;

public class AdminVO {
	
	
	//admin 테이블 필드 생성
	private String adminId;
	private int adminPassword;

	//getter,setter생성
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public int getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(int adminPassword) {
		this.adminPassword = adminPassword;
	}
	
}
