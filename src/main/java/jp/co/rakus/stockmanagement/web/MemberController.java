package jp.co.rakus.stockmanagement.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * メンバー関連処理を行うコントローラー.
 * 
 * @author hiroki.mae
 *
 */
@Controller
@RequestMapping("/member")
@Transactional
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * フォームを初期化します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public MemberForm setUpForm() {
		return new MemberForm();
	}

	/**
	 * メンバー情報登録画面を表示します.
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form() {
		
		return "/member/form";
	}

	/**
	 * メンバー情報を登録します.
	 * @param form フォーム
	 * @param result リザルト
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated MemberForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,Model model
			) {
		
		
		//登録済のメールアドレスであるか判定
		Member member = new Member();
		String inputMailAddress = form.getMailAddress();
		member = memberService.findOneByMailAddress(inputMailAddress);
		
		if(member != null){
			bindingResult.rejectValue("mailAddress", null, "登録済みのメールアドレスです");
//			return "/member/form";
		}
		
		//入力されたパスワードと確認用パスワードが合致しているか判定
		String inputPassword = form.getPassword();
		String inputPasswordChecker = form.getPasswordChecker();
		
		if(!(inputPassword.equals(inputPasswordChecker))){
			bindingResult.rejectValue("passwordChecker", null, "確認用パスワードが一致しません");
//			return "/member/form";
		}
		//入力値チェックエラーがあるか判定
		if(bindingResult.hasErrors()){
			return "/member/form";
		}
		
		//上記すべてクリアであれば登録実行
		Member newMember = new Member();
		BeanUtils.copyProperties(form, newMember);
		StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		String encodePassword = passwordEncoder.encode(inputPassword);
		newMember.setPassword(encodePassword);
		memberService.save(newMember);
			
	
		return "redirect:/";
	}
}
