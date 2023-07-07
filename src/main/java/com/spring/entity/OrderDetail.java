package com.spring.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = true)
	private double price;
	@Column(nullable = true)
	private int quantity;
	@ManyToOne
	@JoinColumn(name = "orders_id")
	private Orders orders;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	private Boolean trangthai;

	public OrderDetail(Integer id, double price, int quantity, Orders orders, Product product, Boolean trangthai) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.orders = orders;
		this.product = product;
		this.trangthai = trangthai;
	}

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderDetail(Integer id, double price, int quantity, Orders orders, Product product) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.orders = orders;
		this.product = product;
	}

	public OrderDetail() {
		super();
	}

}
