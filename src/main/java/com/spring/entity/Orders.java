package com.spring.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Orders implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String orderStatus;
	private String orderDate;
	private double orderprice;
	private String shipaddress;

	public Orders(String orderStatus, String orderDate, double orderprice, String shipaddress,
			List<OrderDetail> orderDetail) {
		super();
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderprice = orderprice;
		this.shipaddress = shipaddress;
		this.orderDetail = orderDetail;
	}

	public String getShipaddress() {
		return shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	@OneToMany(mappedBy = "orders")
	private List<OrderDetail> orderDetail;

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(double orderprice) {
		this.orderprice = orderprice;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Orders(Integer id, String orderStatus, double orderprice, List<OrderDetail> orderDetail) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
		this.orderprice = orderprice;
		this.orderDetail = orderDetail;
	}

	public Orders(String orderStatus, double orderprice, List<OrderDetail> orderDetail) {
		super();
		this.orderStatus = orderStatus;
		this.orderprice = orderprice;
		this.orderDetail = orderDetail;
	}

	public Orders(Integer id, String orderStatus, String orderDate, double orderprice, List<OrderDetail> orderDetail) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderprice = orderprice;
		this.orderDetail = orderDetail;
	}

	public Orders() {
		super();
	}

}
