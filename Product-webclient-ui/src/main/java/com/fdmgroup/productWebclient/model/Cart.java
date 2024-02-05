package com.fdmgroup.productWebclient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
	
	@JsonProperty("cartId")
	private Long cartId;
	
	@JsonProperty("products")
	private List<Product> products;

}
