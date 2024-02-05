package com.fdmgroup.productWebclient.service;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.productWebclient.model.Product;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {

//	private WebClient webClient;
//
//	public ProductService(WebClient webClient) {
//		this.webClient = webClient;
//	}
	
	private final WebClient.Builder webClientBuilder;

	public Mono<Product> findProductById(Long productId) {

		// @formatter:off
				return this.webClientBuilder
							.build()
							.get()
							.uri("http://localhost:8080/api/v1/products/" + productId)
							.retrieve()
							.bodyToMono(Product.class)
							.timeout(Duration.ofMillis(10_000));
		// @formatter:on

	}
//	
//	public Mono<Product> findProductByName(String name) {
//
//		// @formatter:off
//				return this.webClient
//							.get()
//							.uri("/api/v1/products/find-by-name/" + name)
//							.retrieve()
//							.bodyToMono(Product.class)
//							.timeout(Duration.ofMillis(10_000));
//		// @formatter:on
//
//	}
	
	public Flux<Product> findAllProducts(){
		// @formatter:off
		return webClientBuilder
				.build()
				.get()
				.uri("http://localhost:8080/api/v1/products")
				.retrieve()
				.bodyToFlux(Product.class)
				.timeout(Duration.ofMillis(10_000));
		// @formatter:on
	}
	
}
