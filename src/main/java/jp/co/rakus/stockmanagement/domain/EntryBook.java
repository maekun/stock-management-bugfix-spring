package jp.co.rakus.stockmanagement.domain;

import java.sql.Date;

/**
 * 新規で追加する書籍を表すクラス.
 * 
 * @author hiroki.mae
 *
 */
public class EntryBook {

	/** 書籍id*/
	private Integer id;
	/** 書籍名*/
	private String name;
	/** 著者*/
	private String author;
	/** 出版社*/
	private String publisher;
	/** 価格*/
	private Integer price;
	/** ISBNコード*/
	private String isbncode;
	/** 発売日*/
	private Date saledate;
	/** 説明*/
	private String explanation;
	/** 画像*/
	private String image;
	/** 在庫数*/
	private Integer stock;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getIsbncode() {
		return isbncode;
	}
	public void setIsbncode(String isbncode) {
		this.isbncode = isbncode;
	}
	public Date getSaledate() {
		return saledate;
	}
	public void setSaledate(Date saledate) {
		this.saledate = saledate;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}