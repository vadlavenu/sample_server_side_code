package com.acme.product.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.acme.product.ProductDaoException;
import com.acme.product.dao.IProductCRUDOperationsDao;
import com.acme.product.domine.ProductPojo;

@Repository(value = "iProductCRUDOperationsDao")
public class IProductCRUDOperationsDaoImpl implements IProductCRUDOperationsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class ProductPojoRowMapper implements RowMapper<ProductPojo> {
		@Override
		public ProductPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductPojo pojo = new ProductPojo();
			pojo.setId(rs.getInt("ProductId"));
			pojo.setProductName(rs.getString("ProductName"));
			pojo.setProductCode(rs.getString("ProductCode"));
			// pojo.setTags(rs.getString("Tags"));
			if (rs.getDate("ReleaseDate") != null) {
				pojo.setReleaseDate(getDateForUI(rs.getDate("ReleaseDate")));
			}
			pojo.setDescription(rs.getString("Description"));
			pojo.setPrice(rs.getInt("Price"));
			pojo.setStarrating(rs.getFloat("StarRating"));
			pojo.setImageUrl(rs.getString("ImageUrl"));
			return pojo;
		}
	}

	@Override
	public ArrayList<ProductPojo> getProducts() throws ProductDaoException {
		return (ArrayList<ProductPojo>) jdbcTemplate.query("SELECT * FROM PRODUCTS WHERE HIST_FLAG ='N'",
				new ProductPojoRowMapper());
	}

	@Override
	public ProductPojo getProduct(Integer productId) throws ProductDaoException {
		ProductPojo poj = jdbcTemplate.queryForObject("SELECT * FROM PRODUCTS WHERE HIST_FLAG = 'N' AND PRODUCTID =?",
				new Object[] { productId }, new BeanPropertyRowMapper<ProductPojo>(ProductPojo.class));
		poj.setId(productId);
		return poj;
	}

	@Override
	public ProductPojo createProduct(ProductPojo productPojo) throws ProductDaoException {
		ProductPojo createdProductInfo = new ProductPojo();
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(
						"INSERT INTO PRODUCTS (PRODUCTNAME, PRODUCTCODE,  DESCRIPTION, STARRATING, PRICE, RELEASEDATE, HIST_FLAG) VALUES (?, ?, ?, ?, ?, ?,?) ",
						new String[]{"ProductId"});
				statement.setString(1, productPojo.getProductName());
				statement.setString(2, productPojo.getProductCode());
				statement.setString(3, productPojo.getDescription());
				statement.setFloat(4, productPojo.getStarrating());
				statement.setInt(5, productPojo.getPrice());
				statement.setDate(6, getDateForDb(productPojo.getReleaseDate()));
				statement.setString(7, "N");
				return statement;
			}
		}, holder);
		
		int primaryKey = holder.getKey().intValue();
		createdProductInfo = getProduct(primaryKey);
		return createdProductInfo;
	}

	@Override
	public void deleteProduct(Integer productId) throws ProductDaoException {
		int i = jdbcTemplate.update("UPDATE PRODUCTS SET HIST_FLAG='Y' WHERE PRODUCTID=?", new Object[] { productId });
		if (i == 1) {
			System.out.println("Deleted Object with an id **** :: " + productId);
		}
	}

	@Override
	public ProductPojo updateProduct(ProductPojo productPojo) throws ProductDaoException {
		ProductPojo updatedProductInfo = new ProductPojo();
		int result = jdbcTemplate.update(
				"UPDATE PRODUCTS SET PRODUCTNAME=?, PRODUCTCODE=?,  STARRATING=?, PRICE=?, RELEASEDATE=?, DESCRIPTION=?, HIST_FLAG=? WHERE PRODUCTID=?",
				new Object[] { productPojo.getProductName(), productPojo.getProductCode(), productPojo.getStarrating(),
						productPojo.getPrice(), getDateForDb(productPojo.getReleaseDate()), productPojo.getDescription(), "N", productPojo.getId() });
		if (result == 1) {
			updatedProductInfo = getProduct(productPojo.getId());
		}
		return updatedProductInfo;
	}

	
	java.sql.Date getDateForDb(String startDate){
		java.sql.Date sqlStartDate=null;	
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		try {
			date = sdf1.parse(startDate);
			sqlStartDate = new java.sql.Date(date.getTime());  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlStartDate;
	}
	
	String getDateForUI(java.sql.Date dateFromDb){
		DateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		String date;
		date = sdf1.format(dateFromDb);
		return date;
	}
}
