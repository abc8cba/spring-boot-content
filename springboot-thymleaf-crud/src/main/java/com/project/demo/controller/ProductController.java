package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.demo.entity.Product;
import com.project.demo.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String homePage(Model model) {
		//model.addAttribute("productList", productService.getProducts());
		return "home";
	}
	
	@GetMapping(value = "/product-page")
	public String getProductPage(@ModelAttribute("productDTO") Product productDTO) {
		System.out.println("getProductPage() method called....");
		return "product";
	}
	
	@PostMapping(value = "/add-product")
	public String addProduct(@ModelAttribute("productDTO") Product productDTO) {
		System.out.println("addProduct() method called....");
		System.out.println(productDTO);
		productService.saveOrUpdate(productDTO);
		return "redirect:products";
	}
	
	@GetMapping(value = "/products")
	public ModelAndView getProducts() {
		List<Product> productList = productService.getProducts();
		System.out.println(productList);
		ModelAndView model = new ModelAndView();
		model.addObject("productList", productList);
		model.setViewName("products");
		return model;
	}
	
	//@GetMapping(value = "/editProduct/{id}")//Using PathVariable
	//public ModelAndView updateProduct(@PathVariable("id") Integer id) {
	@GetMapping(value = "/editProduct")//Using RequestParam
	public ModelAndView updateProduct(@RequestParam(value = "id", required = false) Integer id) {	
		if(id != null) {
			Product product = productService.getProduct(id);
			ModelAndView view = new ModelAndView();
			view.addObject("productDTO",product);
			view.setViewName("product");
			return view;
		}
		return null;
	}
	
	//@GetMapping(value = "/deleteProduct/{id}")
	//public ModelAndView removeProduct(@PathVariable("id") Integer id) {
	@GetMapping(value = "/deleteProduct")
	public ModelAndView removeProduct(@RequestParam(value = "id", required = false) Integer id) {
		System.out.println("product_id = =========== "+id);
		productService.deleteProduct(id);
		List<Product> productList = productService.getProducts();
		ModelAndView model = new ModelAndView();
		model.addObject("productList", productList);
		model.setViewName("products");
		return model;
	}

}