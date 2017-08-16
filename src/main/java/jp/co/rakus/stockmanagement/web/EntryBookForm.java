package jp.co.rakus.stockmanagement.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * 新規で追加する書籍を表すフォームクラス.
 * 
 * @author hiroki.mae
 *
 */
public class EntryBookForm {

	/** 書籍名*/
	@NotBlank(message="入力してください")
	private String name;
	/** 著者*/
	@NotBlank(message="入力してください")
	private String author;
	/** 出版社*/	
	@NotBlank(message="入力してください")
	private String publisher;
	/** 価格*/
	@NotNull(message="入力してください")
	private String price;
	/** ISBNコード*/
//	[0-9\-] 0〜9か- が当てはまっているものを検索ってこと。
//	{9,16} は、[0-9\-]が9桁〜16桁であるものってこと。
//	[0-9X] が、0〜9かxが、当てはまっているってこと。
	@NotBlank(message="入力してください")
	@Pattern(regexp="[0-9¥-]{9,16}[0-9X]",message="#-####-####-#のようなフォーマットに従い入力してください")
	private String isbncode;
	/** 発売日*/
	@NotBlank(message="入力してください")
	@NotNull(message="数字入力してください")
	private String saledate;
	/** 説明*/
	@NotBlank(message="入力してください")
	private String explanation;
	/** 画像*/
	private MultipartFile image;
	/** 在庫数*/
	@NotBlank(message="入力してください")
	private String stock;

	public MultipartFile getImage() {
		return this.image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}


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
