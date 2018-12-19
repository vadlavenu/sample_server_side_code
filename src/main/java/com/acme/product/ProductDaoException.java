package com.acme.product;


public class ProductDaoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String str1;
	
	/* Constructor of custom exception class
	 * here I am copying the message that we are passing while
	 * throwing the exception to a string and then displaying 
	 * that string along with the message.
	 */
	public ProductDaoException(String str2) {
		str1=str2;
	}
	public String toString(){ 
		return ("ProductDaoException Occurred: "+str1) ;
	}
}
