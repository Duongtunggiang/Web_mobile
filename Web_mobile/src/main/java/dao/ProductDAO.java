package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Category;
import entity.Product;


public class ProductDAO {
	Connection connect = null; 			//ket noi SQL
	PreparedStatement prepare = null;   //Nem cau lenh sang SQL
	ResultSet resultSet = null; 		//Nhan ket qua tra ve
	
	public List<Product> getAllProduct(){
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM favorite.product;";
		try {
			new DBContext();
			connect = DBContext.getConnection(); //Mo ket noi SQL
			prepare = connect.prepareStatement(query);
			resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				list.add(new Product(
						resultSet.getInt("id"),
						resultSet.getString("productName"),
						resultSet.getString("productCategoryPath"),
						resultSet.getString("img"),
						resultSet.getInt("unitPrice")
						));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Product> getTop1(){
		List<Product> top1 = new ArrayList<>();
		String top = "SELECT * FROM product ORDER BY id DESC LIMIT 1;";
		try {
			new DBContext();
			connect = DBContext.getConnection();
			prepare = connect.prepareStatement(top);
			resultSet = prepare.executeQuery();
			while (resultSet.next()) {
				top1.add(new Product(
						resultSet.getInt("id"),
						resultSet.getString("productName"),
						resultSet.getString("productCategoryPath"),
						resultSet.getString("img"),
						resultSet.getInt("unitPrice")
						));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return top1;
	}
	public static List<Product> getProductById(int productId) {
	    List<Product> byID = new ArrayList<>();
	    String getByID = "SELECT * FROM product WHERE id = ?";
	    try {
	        Connection connect = DBContext.getConnection();
	        PreparedStatement statement = connect.prepareStatement(getByID);
	        // Truyền tham số productId vào câu truy vấn
	        statement.setInt(1, productId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            byID.add(new Product(
	                    resultSet.getInt("id"),
	                    resultSet.getString("productName"),
	                    resultSet.getString("productCategoryPath"),
	                    resultSet.getString("img"),
	                    resultSet.getInt("unitPrice")
	            ));
	        }
	        resultSet.close();
	        statement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return byID;
	}
	public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> productList = new ArrayList<>();

        try {
            // Tạo câu lệnh SQL với tham số categoryId
            String sql = "SELECT * FROM product WHERE cid = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, categoryId);

            // Thực thi câu lệnh SQL
            ResultSet resultSet = statement.executeQuery();

            // Lặp qua kết quả và tạo các đối tượng Product
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                String productCategoryPath = resultSet.getString("productCategoryPath");
                String img = resultSet.getString("img");
                double unitPrice = resultSet.getDouble("unitPrice");

                Product product = new Product(id, productName, productCategoryPath, img, unitPrice);
                productList.add(product);
            }

            // Đóng kết nối và giải phóng tài nguyên
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
