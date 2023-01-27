package jp.co.internous.garnet.model.form;

import java.io.Serializable;

public class CartForm implements Serializable{
	private static final long serialVersionUID = 1L;

	private int productId;
	private int productCount;
	
	/**
	 * 商品IDを取得します
	 * @return
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * 商品IDを設定します
	 * @param productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * 商品の数量を取得します
	 * @return
	 */
	public int getProductCount() {
		return productCount;
	}
	
	/**
	 * 商品の数量を設定します
	 * @param productCount
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
}
