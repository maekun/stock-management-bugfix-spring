package jp.co.rakus.stockmanagement.web;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.service.BookService;

/**
 * 書籍関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/book")
@Transactional
public class BookController {
	
	@Autowired
	private BookService bookService;
	@ModelAttribute
	public EntryBookForm setEntryBookFormUp(){
		return new EntryBookForm();
	}
	
	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public BookForm setUpForm() {
		return new BookForm();
	}
	
	/**
	 * 書籍リスト情報を取得し書籍リスト画面を表示します.
	 * @param model モデル
	 * @return 書籍リスト表示画面
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book/list";
	}
	
	/**
	 * 書籍詳細情報を取得し書籍詳細画面を表示します.
	 * @param id 書籍ID
	 * @param model　モデル
	 * @return　書籍詳細画面
	 */
	@RequestMapping(value = "show/{bookId}")
	public String show(@PathVariable("bookId") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "book/show";
	}
	
	/**
	 * 書籍更新を行います.
	 * @param form フォーム
	 * @param result リザルト情報
	 * @param model　モデル
	 * @return　書籍リスト画面
	 */
	@RequestMapping(value = "update")
	public String update(@Validated BookForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return show(form.getId(), model);
		}
		Book book = bookService.findOne(form.getId());
		book.setStock(form.getStock());
		bookService.update(book);
		return list(model);
	}
	/**
	 * 書籍追加画面を表示します.
	 * @param model　モデル
	 * @return　書籍追加画面
	 */
	@RequestMapping(value = "entryPage")
	public String entryPage() {
		return "book/entryBook";
	}
	/**
	 * 書籍追加を行います.
	 * @param form フォーム
	 * @param result リザルト情報
	 * @param model　モデル
	 * @return　書籍リスト画面
	 */
	@RequestMapping(value = "insert")
	public String insert(@Validated EntryBookForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return entryPage();
		}
		Book book = new Book();
		BeanUtils.copyProperties(form,book);
		book.setPrice(form.getIntegerPrice());
		book.setSaledate(form.getDateSaledate());
		book.setStock(form.getIntegerStock());
		//idの最大を取得し、1を足してbookにセットする
		Integer maxId = bookService.getMaxId();
		book.setId( maxId + 1 );
		
		MultipartFile inputFile = form.getImage();
		byte[] filecontents = null;
		try {
			filecontents = inputFile.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Encoder encoder = Base64.getEncoder();
		String encodeFile = encoder.encodeToString(filecontents);
		book.setImage(encodeFile);
		
		bookService.insert(book);
		
//		String fileName = inputFile.getOriginalFilename();
//		book.setImage(fileName);
//		
//		try {
//			inputFile.transferTo(new File("/c:/env/workspace-spring/stock-management-bugfix-spring/src/main/webapp/img/" + fileName));
//		} catch (IllegalStateException e) {
//			System.err.println("画像読み込み失敗");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.err.println("画像読み込み失敗");
//			e.printStackTrace();
//		}
		
		return list(model);
	}

}
