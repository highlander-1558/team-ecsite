package jp.co.internous.garnet.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.internous.garnet.model.domain.TblCart;
import jp.co.internous.garnet.model.domain.dto.CartDto;

@Mapper
public interface TblCartMapper {
	/**
	 * tbl_cartのデータを作成します
	 * @param userId
	 * @param productId
	 * @param productCount
	 * @return 成功時1を返します
	 */
	int createTblCart(
			@Param("userId") int userId, 
			@Param("productId") int productId, 
			@Param("productCount") int productCount);
	
	/**
	 * ユーザーIDに紐づいたcartの情報を取得します
	 * @param userId
	 * @return CartDtoのリスト
	 */
	List<CartDto> findCartDto(@Param("userId") int userId);
	
	/**
	 * cartに既に商品があるか確認します
	 * @param userId
	 * @param productId
	 * @return TblCart
	 */
	List<TblCart> findProduct(
			@Param("userId") int userId, 
			@Param("productId") int productId);
	
	/**
	 * cart内の商品の数量と更新日時を更新します
	 * @param cartId
	 * @param productCount
	 * @return　成功時1を返します
	 */
	int updateCount(
			@Param("cartId") int cartId,
			@Param("productCount") int productCount);
	
	/**
	 * 仮ユーザーID時に作ったカートのデータを、ユーザーIDに更新します
	 * @param tmpUserId
	 * @param userId
	 * @return　成功時はカート内のデータ数を返します
	 */
	int updateUserId(
			@Param("tmpUserId") int tmpUserId, 
			@Param("userId") int userId);
	
	/**
	 * 与えられたカートIDのリストに含まれるIDのデータを削除します
	 * @param cartIds
	 * @return　成功時は削除したデータ数を返します
	 */
	int deleteTblCart(@Param("cartIds") List<Integer> cartIds);
	
	/**
	 * ユーザーIDに紐づくカートのデータを削除します
	 * @param userId
	 * @return カートのデータの削除件数を返します
	 */
	int deleteByUserId(@Param("userId") int userId);
	
	/**
	 * ユーザーID, 商品IDに紐づき、指定したid以外のデータを削除します
	 * @param userId
	 * @param productId
	 * @param id
	 * @return
	 */
	int deleteByUserIdAndProductIdAndNotId(@Param("userId") int userId, @Param("productId") int productId, @Param("id") int id);
	
}
