package com.project.demo.service;

import java.util.List;

import com.project.demo.entity.Product;

public interface ProductService {
	
	public void saveOrUpdate(Product product);
    
    public void deleteProduct(int id);
     
    public Product getProduct(int id);
     
    public List<Product> getProducts();

}
