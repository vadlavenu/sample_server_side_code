package com.acme.product.service;

import java.util.ArrayList;

import com.acme.product.ProductServiceException;
import com.acme.product.domine.ProductPojo;


public interface ProductCRUDOperations {
	
	ArrayList<ProductPojo>	getProducts()  throws ProductServiceException;
	
	ProductPojo	getProduct(Integer productId)  throws ProductServiceException;
	
	ProductPojo	createProduct(ProductPojo productPojo) throws ProductServiceException;
	
	void deleteProduct(Integer productId) throws ProductServiceException;
	
	ProductPojo	updateProduct(ProductPojo productPojo) throws ProductServiceException;
}
