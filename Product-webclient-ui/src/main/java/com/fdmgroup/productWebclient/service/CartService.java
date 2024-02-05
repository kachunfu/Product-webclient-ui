package com.fdmgroup.productWebclient.service;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.productWebclient.model.Cart;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CartService {

	private final WebClient.Builder webClientBuilder;

	public Mono<Cart> findCartById(Long cartId) {
		
		//@formatter:off
		return this.webClientBuilder.build()
				.get()
				.uri("http://localhost:8082/api/v1/carts/" + cartId)
				.retrieve()
				.bodyToMono(Cart.class).timeout(Duration.ofMillis(10_000));
		// @formatter:on
	}
	
	public Flux<Cart>findAllCarts(){
		
		//@formatter:off
		return this.webClientBuilder.build()
				.get()
				.uri("http://localhost:8082/api/v1/carts/")
				.retrieve()
				.bodyToFlux(Cart.class).timeout(Duration.ofMillis(10_000));
		// @formatter:on
	}
	
	public Mono<Cart> updateCart(Cart cart){
		
		return this.webClientBuilder.build()
				.put()
				.uri("http://localhost:8082/api/v1/carts/")
				.body(BodyInserters.fromValue(cart))
				.retrieve()
				.bodyToMono(Cart.class);
	}
	
	public Mono<Cart> addProductToCart( Long cartId, Long productId) {

		//@formatter:off
		return this.webClientBuilder.build()
				.put()
				.uri("http://localhost:8082/api/v1/carts/" + cartId + "/add/" + productId)
				.retrieve()
				.bodyToMono(Cart.class)
				.timeout(Duration.ofMillis(10_000));
		// @formatter:on
	}

	public Mono<Cart> removeProductFromCart(Long cartId, Long productId) {

		//@formatter:off
		return this.webClientBuilder.build()
				.put()
				.uri("http://localhost:8082/api/v1/carts/" + cartId + "/remove/" + productId)
				.retrieve()
				.bodyToMono(Cart.class)
				.timeout(Duration.ofMillis(10_000));
		// @formatter:on
	}
	
	public Mono<Cart> clearCart(Long cartId) {

		//@formatter:off
		return this.webClientBuilder.build()
				.put()
				.uri("http://localhost:8082/api/v1/carts/" + cartId + "/clear" )
				.retrieve()
				.bodyToMono(Cart.class)
				.timeout(Duration.ofMillis(10_000));
		// @formatter:on
	}

}
