package jp.co.rakus.stockmanagement.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 新規で追加する書籍を表すフォームクラス.
 * 
 * @author hiroki.mae
 *
 */
public class EntryBookForm {

	/** 書籍名*/
	private String name;
	/** 著者*/
	private String author;
	/** 出版社*/
	private String publisher;
	/** 価格*/
	private String price;
	/** ISBNコード*/
//	[0-9\-] 0〜9か- が当てはまっているものを検索ってこと。
//	{9,16} は、[0-9\-]が9桁〜16桁であるものってこと。
//	[0-9X] が、0〜9かxが、当てはまっているってこと。
	@Pattern(regexp="[0-9¥-]{9,16}[0-9X]",message="#-####-####-#のようなフォーマットに従い入力してください")
	private String isbncode;
	/** 発売日*/
	private String saledate;
	/** 説明*/
	private String explanation;
	/** 画像*/
	private String image;
	/** 在庫数*/
	private String stock;
	
	
	public Date getDateSaledate() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date saledate = null ;
		try {
			saledate = formatter.parse(this.saledate);
		} catch (ParseException e) {
			System.err.println("getDateSaledateメソッドでエラー発生");
			e.printStackTrace();
		}
		return saledate;
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
	public String getPrice() {
		return price;
	}
	public Integer getIntegerPrice() {
		return Integer.parseInt(this.price);
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsbncode() {
		return isbncode;
	}
	public void setIsbncode(String isbncode) {
		this.isbncode = isbncode;
	}
	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
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
	public String getStock() {
		return stock;
	}
	public Integer getIntegerStock() {
		return Integer.parseInt(this.stock);
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	
}
