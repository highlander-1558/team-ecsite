package jp.co.internous.garnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.garnet.model.domain.MstUser;
import jp.co.internous.garnet.model.mapper.MstUserMapper;
import jp.co.internous.garnet.model.session.LoginSession;

@Controller
@RequestMapping("/garnet/mypage")
public class MyPageController {
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * ユーザー情報を表示する
	 * @param m
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model m) {
		/*
		 * マイページ機能なので、未ログイン時は想定しない
		 * 
		 * ユーザー情報を取得する
		 * 呼び出されるメソッドはMstUserMapperに定義される
		 * 必要な引数は、ユーザーネーム, パスワード
		 * 　引数は全てloginSessionから取得
		 * 
		 * 受け取る戻り値は、MstUserクラス
		 * 
		 */
		MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		m.addAttribute("user", user);
		
		m.addAttribute("loginSession", loginSession);
		
		return "my_page";
	}

}
