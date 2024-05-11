package entity;

public class Product {
	private int id;
	private String productName;
	private String productCategoryPath;
	private String img;
	private double unitPrice;
	public Product(int id, String productName, String productCategoryPath, String img,
			double unitPrice) {
		super();
		this.id = id;
		this.productName = productName;
		this.productCategoryPath = productCategoryPath;
		this.img = img;
		this.unitPrice = unitPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategoryPath() {
		return productCategoryPath;
	}
	public void setProductCategoryPath(String productCategoryPath) {
		this.productCategoryPath = productCategoryPath;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}	
