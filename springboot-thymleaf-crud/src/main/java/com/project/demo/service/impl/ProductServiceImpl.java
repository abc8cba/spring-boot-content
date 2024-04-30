package com.project.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.entity.Product;
import com.project.demo.repo.ProductRepository;
import com.project.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public void saveOrUpdate(Product product) {
		System.out.println("******************: "+product);
		repository.save(product);

	}

	@Override
	public void deleteProduct(int id) {
		repository.deleteById(id);
	}

	@Override
	public Product getProduct(int id) {
		Optional<Product> optionalObject = repository.findById(id);
		if(optionalObject.isPresent()) {
			Product product = optionalObject.get();
			return product;
		}
		
		return optionalObject.get();
	}

	@Override
	public List<Product> getProducts() {
		
		return repository.findAll();
	}

}
