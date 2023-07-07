package com.spring.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Please enter your's image")
	private String image;
	@NotBlank(message = "Please enter your's name")
	private String name;

	private Double price;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product() {
		super();
	}

	public Product(Integer id, String image, String name, Double price) {
		super();
		this.image = image;
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product(Integer id, String image, String name, Double price, Category category) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product(String image, String name, Double price, Category category) {
		super();
		this.image = image;
		this.name = name;
		this.price = price;
		this.category = category;
	}

}
