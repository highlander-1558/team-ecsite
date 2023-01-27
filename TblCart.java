package jp.co.internous.garnet.model.domain;

import java.sql.Timestamp;

public class TblCart {
	private int id;
	private int userId;
	private int productId;
	private int productCount;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	/**
	 * カートIDを取得する
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * カートIDを設定する
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * ユーザーIDを取得する
	 * @return
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * ユーザーIDを設定する
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * プロダクトIDを取得する
	 * @return
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * プロダクトIDを設定する
	 * @param productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * 商品の数量を取得する
	 * @return
	 */
	public int getProductCount() {
		return productCount;
	}
	
	/**
	 * 商品の数量を設定する
	 * @param productCount
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	/**
	 * 作成日時を取得する
	 * @return
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * 作成日時を設定する
	 * @param createdAt
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * 更新日時を取得する
	 * @return
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	/**
	 * 更新日時を設定する
	 * @param updatedAt
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
