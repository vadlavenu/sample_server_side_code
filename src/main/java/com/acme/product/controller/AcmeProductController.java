package com.acme.product.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.product.ProductServiceException;
import com.acme.product.domine.ProductPojo;
import com.acme.product.service.impl.ProductCRUDOperationsImpl;

@RestController
public class AcmeProductController {

	
	@Autowired
	private ProductCRUDOperationsImpl productService;
	

	@RequestMapping(value="/getProducts", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ArrayList<ProductPojo>> getProducts(){
		ArrayList<ProductPojo> listofPrds;
		try {
			listofPrds = productService.getProducts();
			if(listofPrds!=null && !listofPrds.isEmpty()) {
				return new ResponseEntity<ArrayList<ProductPojo>>(listofPrds, HttpStatus.OK);
			}else {
				return new ResponseEntity<ArrayList<ProductPojo>>(listofPrds, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<ArrayList<ProductPojo>>(HttpStatus.EXPECTATION_FAILED);
		}
	
	}
	
	@RequestMapping(value="/getProduct/{productId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ProductPojo> getProduct(@PathVariable Integer productId){
		ProductPojo product;
		try {
			product = productService.getProduct(productId);
			if(product!=null) {
				return new ResponseEntity<ProductPojo>(product, HttpStatus.OK);
			}else {
				return new ResponseEntity<ProductPojo>(product, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<ProductPojo>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value="/createProduct", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ProductPojo> createProduct(@RequestBody ProductPojo product){
		ProductPojo createdProduct;
		try {
			createdProduct = productService.createProduct(product);
			if(createdProduct!=null) {
				return new ResponseEntity<ProductPojo>(createdProduct, HttpStatus.OK);
			}else {
				return new ResponseEntity<ProductPojo>(createdProduct, HttpStatus.NO_CONTENT);
			}
		} catch (ProductServiceException e) {
			return new ResponseEntity<ProductPojo>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value="/updateProduct", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<ProductPojo> updateProduct(@RequestBody ProductPojo product){
		ProductPojo updatedProduct;
		try {
			updatedProduct = productService.updateProduct(product);
			if(updatedProduct!=null) {
				return new ResponseEntity<ProductPojo>(updatedProduct, HttpStatus.OK);
			}else {
				return new ResponseEntity<ProductPojo>(updatedProduct, HttpStatus.NO_CONTENT);
			}
		} catch (ProductServiceException e) {
			return new ResponseEntity<ProductPojo>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value="/deleteProduct/{productId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteProduct(@PathVariable Integer productId){
		try {
			productService.deleteProduct(productId);
		} catch (ProductServiceException exe) {
			exe.getMessage();
		}
	}
	
}
