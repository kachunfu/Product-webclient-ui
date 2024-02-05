package com.fdmgroup.productWebclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;



import com.fdmgroup.productWebclient.service.ProductService;



@Controller
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	  @RequestMapping("/")
	  public String index(Model model){
		
		model.addAttribute("products", productService.findAllProducts());
	    return "index";
	  }
	  
	  @RequestMapping("/allCarts")
	  public String cartDetails() {
		  return "allCarts";
		  
	  }

}
