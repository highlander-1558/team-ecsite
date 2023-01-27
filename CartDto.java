package jp.co.internous.garnet.model.domain.dto;

public class CartDto {
	// tbl_cart.id -Primary key
	private int Id;
	// tbl_cart.product_count
	private int productCount;
	// mst_product.product_name
	private String productName;
	// mst_product.price
	private int price;
	// mst_product.image_full_path
	private String imageFullPath;
	// mst_product.price * tbl_cart.product_count
	private int total;
	
	/**
	 * カートIDを取得する
	 * @return 
	 */
	public int getId() {
		return Id;
	}

	/**
	 * カートIDを設定する
	 * @param id
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * 商品数を取得する
	 * @return
	 */
	public int getProductCount() {
		return productCount;
	}

	/**
	 * 商品数を設定する
	 * @param productCount
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	/**
	 * 商品名を取得する
	 * @return
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 商品名を設定する
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 商品1つ当たりの価格を取得する
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 商品1つ当たりの価格を設定する
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * 商品画像のパスを取得する
	 * @return
	 */
	public String getImageFullPath() {
		return imageFullPath;
	}

	/**
	 * 商品画像のパスを設定する
	 * @param imgFullPath
	 */
	public void setImageFullPath(String imgFullPath) {
		this.imageFullPath = imgFullPath;
	}

	/**
	 * 合計金額を取得する
	 * @return
	 */
	public int getTotal() {
		return total;
	}

	/**
	 *合計金額を設定する
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
}
