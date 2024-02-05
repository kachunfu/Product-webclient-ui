package com.fdmgroup.productWebclient.model;

import java.math.BigDecimal;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	@JsonProperty("productId")
	private Long productId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("price")
	private BigDecimal price;
	
	
}
