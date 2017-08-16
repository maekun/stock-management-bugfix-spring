package jp.co.rakus.stockmanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * ログイン関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
//	@Autowired
//	private MemberService memberService;
//	@Autowired
//	private HttpSession session;
	@ModelAttribute
	public LoginForm setUpLoginForm(){
		return new LoginForm();
	}

	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}

	/**
	 * ログイン画面を表示します.
	 * @return ログイン画面
	 */
	@RequestMapping
	//SecurityConfigのhttp.formLogin().failureurlのところでリクエストパラメータにerror=trueが設定されている
	//errorがtrueの時は入力値チェックが終わってログイン失敗した時のエラーメッセージを設定しておく
	public String index(String error,Model model) {
		if(Boolean.valueOf(error)){
			model.addAttribute("errorFrag", error);
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが違います。");
		}
		return "loginForm";
	}
	
///**
// * ログイン処理を行います.
// * @param form　フォーム
// * @param result　リザルト
// * @param model　モデル
// * @return　ログイン成功時：書籍リスト画面
// */
//@RequestMapping(value = "/login")
//public String login(@Validated LoginForm form,
//		BindingResult result,RedirectAttributes redirectAttributes, Model model,
//		@AuthenticationPrincipal LoginMemberDetailsService loginMemberDetailsService ) {
//	
//	if (result.hasErrors()){
//		return index(false,model);
//	}
//	
//	String inputMailAddress = form.getMailAddress();
//	String inputPassword = form.getPassword();
//	Member member = memberService.findOneByMailAddressAndPassword(inputMailAddress,inputPassword );
//	if (member == null) {
//		ObjectError error = new ObjectError("loginerror", "メールアドレスまたはパスワードが違います。");
//		result.addError(error);
//		return index(false,model);
//	}
//	session.setAttribute("member",member);
//	return "redirect:/book/list";
//}
}

//	/**
//	 * ログイン処理を行います.
//	 * @param form　フォーム
//	 * @param result　リザルト
//	 * @param model　モデル
//	 * @return　ログイン成功時：書籍リスト画面
//	 */
//	@RequestMapping(value = "/login")
//	public String login(@Validated LoginForm form,
//			BindingResult result,RedirectAttributes redirectAttributes, Model model) {
//		if (result.hasErrors()){
//			return index(false,model);
//		}
//		String mailAddress = form.getMailAddress();
//		LoginMemberDetailsService detailsService = new LoginMemberDetailsService();
//		LoginMemberDetails memberDetails = (LoginMemberDetails) detailsService.loadUserByUsername(mailAddress);
//		Member member = memberDetails.getMember();
//////		
//////		Member member = memberService.findOneByMailAddressAndPassword(mailAddress, password);
//////		if (member == null) {
//////			ObjectError error = new ObjectError("loginerror", "メールアドレスまたはパスワードが違います。");
//////            result.addError(error);
//////			return index(false,model);
//////		}
//		session.setAttribute("member",member);
//		return "redirect:/book/list";
//	}
