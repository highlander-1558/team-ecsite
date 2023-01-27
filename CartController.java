package jp.co.internous.garnet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.garnet.model.domain.TblCart;
import jp.co.internous.garnet.model.domain.dto.CartDto;
import jp.co.internous.garnet.model.form.CartForm;
import jp.co.internous.garnet.model.mapper.TblCartMapper;
import jp.co.internous.garnet.model.session.LoginSession;

@Controller
@RequestMapping("/garnet/cart")
public class CartController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	TblCartMapper tblCartMapper;
	
	private Gson gson = new Gson();
	
	/**
	 * ユーザーIDに紐づいたカートの情報を表示します
	 * @param m
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model m) {
		boolean isLogin = loginSession.getUserId() != 0;
		
		int userId;
		if(isLogin) {
			userId = loginSession.getUserId();
		}else {
			userId = loginSession.getTmpUserId();
		}
		m.addAttribute("loginSession", loginSession);
		
		/*
		 * （仮）ユーザーIDに紐づくカートの情報を取得する
		 * 呼び出すメソッドはカート情報のMapperに定義される
		 * 呼び出す際に必要な引数は、ユーザーID（ログイン時） or 仮ユーザーID（非ログイン時）
		 * 　ログイン時も非ログイン時もセッションにデータがある
		 * 
		 * cartテーブルにproductテーブルを結合させる
		 * 
		 * 受け取る戻り値はカートのDTOのリスト
		 * 
		 */
		List<CartDto> cartDtoList =tblCartMapper.findCartDto(userId); 
		m.addAttribute("cartDtoList", cartDtoList);
		
		if (cartDtoList != null && cartDtoList.size() > 0) {
			m.addAttribute("isEmpty", 0);
		}else {
			m.addAttribute("isEmpty", 1);
		}
		
		return "cart";
	}
	
	/**
	 * カートに商品を追加し、カート画面に遷移します
	 * @param CartForm c (プロダクトID, 商品数)
	 * @return カート画面
	 */
	@RequestMapping("/add")
	public String add(CartForm c) {
		boolean isLogin = loginSession.getUserId() != 0;
		
		int userId;
		if(isLogin) {
			userId = loginSession.getUserId();
		}else {
			userId = loginSession.getTmpUserId();
		}
		
		/*
		 * カートテーブルからデータを取得
		 * 呼び出すメソッドはカート情報のMapperに定義される
		 * 呼び出す際に必要な引数は、(仮)ユーザーID, プロダクトID
		 * 　(仮)ユーザーIDはセッションから、
		 * 　プロダクトIDはaddメソッドの引数から取得
		 * 
		 * 受け取る戻り値は、カートドメインのリスト or null
		 */
		List<TblCart> cartList = tblCartMapper.findProduct(userId, c.getProductId());
		
		if(cartList != null && cartList.size() > 0) {
			/*
			 * カートテーブルのデータの更新（上記でデータを取得した場合）
			 * 呼び出すメソッドはカート情報のMapperに定義される
			 * 呼び出す際に必要な引数は、カートID, 商品の数量
			 * 　カートIDは上記で取得したデータから
			 * 　商品の数量は、addメソッドの引数と上記で取得したデータの数量の合計値
			 * 
			 * 受け取る戻り値は、カートのデータの更新行数（1なら成功）
			 * 
			 */
			int total = c.getProductCount();
			for(TblCart cart : cartList) {
				total += cart.getProductCount();
			}
			
			TblCart cart = cartList.get(0);
			if(cartList.size() > 1) {
				tblCartMapper.deleteByUserIdAndProductIdAndNotId(cart.getUserId(), cart.getProductId(), cart.getId());
			}
			
			tblCartMapper.updateCount(cartList.get(0).getId(), total);
		}else {
			/*
			 * カートテーブルへのデータの挿入（上記でデータが空の場合）
			 * 呼び出すメソッドはカート情報のMapperに定義される
			 * 呼び出す際に必要な引数は、(仮)ユーザーID, プロダクトID, 商品の数量
			 *  (仮)ユーザーIDはセッションから、
			 *  他はaddメソッドの引数で取得
			 * 
			 * 受け取る戻り値は、カートへの登録の更新行数（1なら成功）
			 * 
			 */
			tblCartMapper.createTblCart(userId, c.getProductId(), c.getProductCount());
		}
		
		return "redirect:/garnet/cart/";
	}
	
	/**
	 * カート内の商品を削除します
	 * @param int[] cartIds カートIDの配列
	 * @return boolean　成功可否
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping("/delete")
	public boolean deleteApi(@RequestBody String cartIds) {
		Map<String, List<Integer>> map = gson.fromJson(cartIds, Map.class);
		List<Integer> cartIdsList = map.get("cartIds");
		
		/*
		 * カートテーブルからデータの削除する
		 * 呼び出すメソッドはカート情報のMapperに定義される
		 * 呼び出す際に必要な引数は、カートID（のリスト）
		 * 　カートIDはdeleteApiメソッドの引数で受け取る
		 * 受け取る戻り値は、カートからの削除の更新行数（0より大きい場合成功）
		 */
		int result = tblCartMapper.deleteTblCart(cartIdsList);
		
		return result == cartIdsList.size();
	}
	
}
