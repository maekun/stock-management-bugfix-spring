package jp.co.rakus.stockmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.rakus.stockmanagement.domain.LoginMemberDetails;
import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.repository.MemberRepository;

/**
 * 入力されたメールアドレスをもとにユーザ情報を取り出すサービスクラス.
 * 
 * @author hiroki.mae
 *
 */
@Service
public class LoginMemberDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
		//入力されたメールアドレスからメンバーを一件検索
		Member member = memberRepository.findByMailAddress(mailAddress);
		
		//メンバーが見つからない場合はnullが返ってくるのでUsernameNotFoundExceptionをスローをすることとする
		if(member == null){
			throw new UsernameNotFoundException("ユーザが存在しません");
		}
		//メンバーが見つかった場合はLoginMemberDetailsにラップして返す
		return new LoginMemberDetails(member);
	}

	
}
