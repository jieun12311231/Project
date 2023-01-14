package com.yedam.app.BookList;

public class BookVO {

//도서재고 관리 
	private int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	private int ISBN;
	private String bookName;
	private String author;
	private String content;
	private int stock;
	private int nowStock;
	
	public int getNowStock() {
		return nowStock;
	}
	public void setNowStock(int nowStock) {
		this.nowStock = nowStock;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "도서 번호 = " + number + " \t ISBN = " + ISBN + " \t 도서 제목 = " + bookName + " \t 저자 = " + author
				+ " \t 내용 = " + content + " \t 총 재고 = " + stock + "권 \t 현재 보유 재고 = " + nowStock + "권";
	}
	
	

}
