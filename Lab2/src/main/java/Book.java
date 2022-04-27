package Lab2.src.main.java;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String ISBN;
	private String author;
	private double price;
	private Date publishData;

	public Book() {
	}

	public Book(String title, String ISBN, String author, double price, Date publishData) {
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
		this.price = price;
		this.publishData = publishData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublishData() {
		return publishData;
	}

	public void setPublishData(Date publishData) {
		this.publishData = publishData;
	}
}
