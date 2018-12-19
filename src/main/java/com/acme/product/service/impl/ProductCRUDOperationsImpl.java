package com.acme.product.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.product.ProductDaoException;
import com.acme.product.ProductServiceException;
import com.acme.product.dao.IProductCRUDOperationsDao;
import com.acme.product.domine.ProductPojo;
import com.acme.product.service.ProductCRUDOperations;

@Service(value="productService")
public class ProductCRUDOperationsImpl implements ProductCRUDOperations {

	@Autowired
	private IProductCRUDOperationsDao iProductCRUDOperationsDao;
	
	@Override
	public ArrayList<ProductPojo> getProducts() throws ProductServiceException {
		// TODO Auto-generated method stub
		try {
			return iProductCRUDOperationsDao.getProducts();
		} catch (ProductDaoException e) {
			e.getMessage();
			throw new ProductServiceException("Exception Occured while getting products. Service Class Name : ProductCRUDOperationsImpl, Method Name : getProducts()");
		}
	}

	@Override
	public ProductPojo getProduct(Integer productId) throws ProductServiceException {
		// TODO Auto-generated method stub
		try {
			return iProductCRUDOperationsDao.getProduct(productId);
		} catch (ProductDaoException e) {
			throw new ProductServiceException("Exception Occured while getting product. Service Class Name : ProductCRUDOperationsImpl, Method Name : getProduct()");
		}
	}

	@Override
	public ProductPojo createProduct(ProductPojo productPojo) throws ProductServiceException {
		// TODO Auto-generated method stub
		try {
			return iProductCRUDOperationsDao.createProduct(productPojo);
		} catch (ProductDaoException e) {
			throw new ProductServiceException("Exception Occured while creating product. Service Class Name : ProductCRUDOperationsImpl, Method Name : createProduct()");
		}
	}

	@Override
	public void deleteProduct(Integer productId) throws ProductServiceException {
		// TODO Auto-generated method stub
		try {
			iProductCRUDOperationsDao.deleteProduct(productId);
		} catch (ProductDaoException e) {
			throw new ProductServiceException("Exception Occured while deleting product. Service Class Name : ProductCRUDOperationsImpl, Method Name : deleteProduct()");
		}
		
	}

	@Override
	public ProductPojo updateProduct(ProductPojo productPojo) throws ProductServiceException{
		// TODO Auto-generated method stub
		try {
			return iProductCRUDOperationsDao.updateProduct(productPojo);
		} catch (ProductDaoException e) {
			throw new ProductServiceException("Exception Occured while updating product. Service Class Name : ProductCRUDOperationsImpl, Method Name : updateProduct()");
		}
	}

	

}
