package com.fdmgroup.productWebclient.controller;


import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.fdmgroup.productWebclient.model.Cart;
import com.fdmgroup.productWebclient.service.CartService;
import com.fdmgroup.productWebclient.service.ProductService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class CartController {

	private CartService cartService;

	private ProductService productService;

	@GetMapping("/showAllCarts")
	public String getAllCarts(final Model model) {

		IReactiveDataDriverContextVariable reactiveDataDriverContextVariable = new ReactiveDataDriverContextVariable(
				cartService.findAllCarts(), 1);

		model.addAttribute("carts", reactiveDataDriverContextVariable);

		return "allCarts";
	}
	
	@GetMapping(path={"/showProduct","/showProduct/{cartId}"})
	public String getProductsInCart(Model model, @PathVariable("cartId") Optional<Long> cartId) {
		
		Mono<Cart> cart = cartService.findCartById(cartId.get());
		model.addAttribute("cart",cart);
		
		return "showProductsInCart";
	}
	
	@GetMapping(path= {"/{cartId}/add"})
	public String showProductsToAdd(Model model,@PathVariable("cartId") Optional<Long> cartId ) {
		
		IReactiveDataDriverContextVariable reactiveDataDriverContextVariable = new ReactiveDataDriverContextVariable(
				productService.findAllProducts(), 1);
		
		Mono<Cart> cart = cartService.findCartById(cartId.get());
		model.addAttribute("cart",cart);
		model.addAttribute("products", reactiveDataDriverContextVariable);

		return "allProducts";
	}
	
	@GetMapping(path= ("/{cartId}/add/{productId}"))
	public String addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
		
		cartService.addProductToCart(cartId, productId).subscribe();
		return "redirect:/";
	}
	
	@GetMapping(path= ("/{cartId}/remove/{productId}"))
	public String removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
		
		cartService.removeProductFromCart(cartId, productId).subscribe();
		return "redirect:/";
	}
	
	@GetMapping(path= ("/{cartId}/clear"))
	public String clearCart(@PathVariable Long cartId) {
		cartService.clearCart(cartId).subscribe();
		return "redirect:/";
	}
	





}
