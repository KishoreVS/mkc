package com.exercise.two;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AuthorTable")
public class AuthorTable {
	
	
	@Id
	@OneToOne
	private BookTable bookId;
	@Column
	private String name;
	
	
	
	public AuthorTable(BookTable bookId, String name){
		super();
		this.bookId = bookId;
		this.name = name;
	}
	public BookTable getBookId() {
		return bookId;
	}
	public void setBookId(BookTable bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
