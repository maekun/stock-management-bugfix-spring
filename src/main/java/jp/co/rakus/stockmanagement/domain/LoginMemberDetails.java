package jp.co.rakus.stockmanagement.domain;


import org.springframework.security.core.authority.AuthorityUtils;

public class LoginMemberDetails extends org.springframework.security.core.userdetails.User{

	/**
	 * 黄色い警告出ちゃったので入れました
	 */
	private static final long serialVersionUID = 1L;
	
	
	//springSecurityの認証ユーザから実際のメンバ～オブジェクトを取得できるようにフィールド追加
	private final Member member;

	//org.springframework.security.core.userdetails.Userクラスのコンストラクタを使って
	//「ユーザ名」「パスワード」「認可用のロール」を設定する。ロールの作成には「AuthorityUtils」を使うと便利
	
	public LoginMemberDetails(Member member) {

		//親クラスのコンストラクタにアドレスやパスワードを渡してnewすることでuserdetails.Userクラスのオブジェクトが生成される
		//ROLEにAdminを与えるのかUserを与えるのかはデータベースからメンバーを引っ張ってきたときにisAdminカラムにtrueが入っているかfalseが入っているかで分ける
		super(member.getMailAddress(), member.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
	
	

	
}
