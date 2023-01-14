package com.yedam.admin.Borrow;

public class BorrowVO {
	
	private String borforDate;
	private int borTel;
	private String bookName;
	private String bortoDate;
	private int borStatus;
	private String returnDate;
	
	
	
	
	public String getBorforDate() {
		return borforDate;
	}
	public void setBorforDate(String borforDate) {
		this.borforDate = borforDate;
	}
	public int getBorTel() {
		return borTel;
	}
	public void setBorTel(int borTel) {
		this.borTel = borTel;
	}
	public String getbookName() {
		return bookName;
	}
	public void setBookInfomation(String bookName) {
		this.bookName = bookName;
	}
	public String getBortoDate() {
		return bortoDate;
	}
	public void setBortoDate(String bortoDate) {
		this.bortoDate = bortoDate;
	}
	public int getBorStatus() {
		return borStatus;
	}
	public void setBorStatus(int borStatus) {
		this.borStatus = borStatus;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "대여 일자 = " + borforDate + ", 고객 번호 = " + borTel + ", 도서 정보 = " + bookName
				+ ", 반납 예정 일자 = " + bortoDate + ", 대출 여부 = " + borStatus + ", 반납 날짜 = " + returnDate ;
	}
	
	
	
}
