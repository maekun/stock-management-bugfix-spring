package jp.co.rakus.stockmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import org.springframework.security.web.util.AntPathRequestMatcher;
//SpringSecurityの基本的な設定（認証フィルタ等）が行われる
@EnableWebMvcSecurity
//継承をすることでデフォルトの設定に対して「追加したい箇所」だけ「オーバーライド」して設定できるようになる
public class SecurityConfig extends WebSecurityConfigurerAdapter{ 

	@Override
	public void configure(WebSecurity web) throws Exception {
		//「静的リソース」に対するアクセスには「セキュリティ設定」は無視するようにする
		web.ignoring().antMatchers("/webjars/**" , "/css/**");
	}
	
	@Override
	//「configure(HttpSecurity)」メソッドをオーバーライドすることで認可の設定や、ログイン・ログアウトに関する設定ができる
	protected void configure(HttpSecurity http) throws Exception {
		//認可に関する設定
		//ここではログインフォームを表示する「/loginForm」には任意のユーザーがアクセスできるようにする。
		//パス指定（URL）だからRequestMappingを書いてあげる
		//それ以外のパスには認証なしでアクセスできないようにする。
		http.authorizeRequests()
						.antMatchers("/").permitAll()
						.antMatchers("/login").permitAll()
						.antMatchers("/member/form").permitAll()
						.antMatchers("/member/create").permitAll()
						.anyRequest().authenticated();
		//ログインフォームに関する設定
		http.formLogin()
						//ログイン認証処理のパス
						.loginProcessingUrl("/login")
						//ログインフォーム表示のパス(ログインフォーム
						.loginPage("/")
						//認証失敗時の遷移先
						.failureUrl("/?error=true")
						//認証成功時の遷移先(書籍一覧表示
						//true/falseでショッピングカートから会計に進むときにログインしていなければログイン画面に遷移し、
						//ログイン後にbooklistに移動せず直接会計に進むようにできる
						.defaultSuccessUrl("/book/list",true)
						//ユーザ名・パスワードのパラメータ名を設定(フォームクラスのプロパティに沿っていればいいのかな？？？？？？？？？？？
						.usernameParameter("mailAddress").passwordParameter("password")
						.and();
		//ログアウトに関する設定
		http.logout()
						//ログアウト処理のパス
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout/sessionInvalidate"))
						//ログアウト後の遷移先
						.logoutSuccessUrl("/");
	}
	
	@Configuration
	//継承した「JavaConfig」クラスで認証に関する設定をする
	static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter{
		
		@Autowired
		//インターフェース型をAutowiredすると既に実装しているクラスが勝手に選ばれるので
		//LoginMemberDetailsServiceがnewされて入ってくる。
		UserDetailsService userDetailsService;
		
		//パスワードをハッシュ化するための「PasswordEncoder」クラスを定義
		//PasswordEncoderの実相を選ぶことでハッシュ化アルゴリズムを決める。
		//SHA-256アルゴリズム + 1024回のストレッチでハッシュ化を行うエンコーダ
		PasswordEncoder passwordEncoder(){
			return new StandardPasswordEncoder();
		}
		
		@Override
		//「認証」に関する設定をする
		//ここでは「認証ユーザー」を取得する「UserDetailsService」の設定や「パスワード照合時」につかう「PasswordEncoder」の設定をする
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService)//実際にはLoginMemberDetailsServiceが入ってきている
					.passwordEncoder(passwordEncoder());
		}
		
		
	}
}
