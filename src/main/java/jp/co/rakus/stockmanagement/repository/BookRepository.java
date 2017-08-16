package jp.co.rakus.stockmanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.stockmanagement.domain.Book;

/**
 * booksテーブル操作用のリポジトリクラス.
 * @author igamasayuki
 */
@Repository
public class BookRepository {
	/**
	 * ResultSetオブジェクトからBookオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String author = rs.getString("author");
		String publisher = rs.getString("publisher");
		Integer price = rs.getInt("price");
		String isbncode = rs.getString("isbncode");
		Date saledate = rs.getDate("saledate");
		String explanation = rs.getString("explanation");
		String image = rs.getString("image");
		Integer stock = rs.getInt("stock");
		return new Book(id, name, author, publisher, price, isbncode, saledate, explanation, image, stock);
	};
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Book> findAll() {
		List<Book> books = jdbcTemplate.query(
				"SELECT id,name,author,publisher,price,isbncode,saledate,explanation,image,stock FROM books order by saledate DESC ;", 
				BOOK_ROW_MAPPER);
		return books;
	}
	
	public Book findOne(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id",id);
		Book book = jdbcTemplate.queryForObject(
				"SELECT id,name,author,publisher,price,isbncode,saledate,explanation,image,stock FROM books WHERE id=:id", 
				param, 
				BOOK_ROW_MAPPER);
		return book;
	}
	
	public Book update(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);
		if (book.getId() == null) {
			throw new NullPointerException();
		} 
		jdbcTemplate.update(
				"UPDATE books SET stock=:stock WHERE id=:id",
				param);
		return book;
	}
	
	/**
	 * 新規書籍登録します.
	 * 
	 * @param book 登録する書籍
	 * @return 書籍
	 */
	public Book insert(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);
		jdbcTemplate.update(
				"insert INTO books (id,name,author,publisher,price,isbncode,saledate,explanation,image,stock) values (:id,:name,:author,:publisher,:price,:isbncode,:saledate,:explanation,:image,:stock);",
				param);
		return book;
	}
	
	/**
	 * テーブル内で一番大きいidを取得.
	 * 
	 * @return id
	 */
	public Integer getMaxId(){
		String sql = "select max(id) from books id;";
		SqlParameterSource paramMap = new MapSqlParameterSource();
		Integer maxId = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
		return maxId;
	}
	
	
}
