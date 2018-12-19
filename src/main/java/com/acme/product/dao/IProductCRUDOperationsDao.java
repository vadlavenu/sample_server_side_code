package com.acme.product.dao;

import java.util.ArrayList;

import com.acme.product.ProductDaoException;
import com.acme.product.domine.ProductPojo;


public interface IProductCRUDOperationsDao {
	
	ArrayList<ProductPojo> getProducts() throws ProductDaoException;
	ProductPojo	getProduct(Integer productId) throws ProductDaoException;
	ProductPojo	createProduct(ProductPojo productPojo)throws ProductDaoException;
	void deleteProduct(Integer productId)throws ProductDaoException;
	ProductPojo	updateProduct(ProductPojo productPojo)throws ProductDaoException;

}
