package com.exercise.two;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BookTable")
public class BookTable {

	@Id
	private int isbn;
	@Column
	private String title;
	@Column
	private int price;
	
	
    
	
	
	public BookTable(int isbn, String title, int price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
