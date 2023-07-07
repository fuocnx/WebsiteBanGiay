package com.spring.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.OrderDetail;
import com.spring.entity.Orders;
import com.spring.entity.Product;
import com.spring.repository.OrderRepository;
import com.spring.repository.OrderdetailRepository;
import com.spring.repository.ProductRepository;

@Controller
public class ShoppingCartController {

	@Autowired
	OrderdetailRepository orderdetailRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/cart")
	public String viewcart(Model model) {
		List<OrderDetail> cartItems = orderdetailRepository.findAllOderdeatil1();

		double s = 0;
		for (OrderDetail item : cartItems) {
			s += item.getQuantity() * item.getProduct().getPrice().doubleValue();
		}
		model.addAttribute("sl", orderdetailRepository.sl());
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("total", s);
		return "cart-view";
	}

	@GetMapping("addcart/{id}")
	public String add(Model model, @PathVariable Integer id) {

		Product product = productRepository.findById(id).orElse(null);
		product.getId();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(product);
		orderDetail.setPrice(product.getPrice());
		orderDetail.setQuantity(1);
		orderDetail.setTrangthai(Boolean.TRUE);
		orderdetailRepository.save(orderDetail);
		List<OrderDetail> cartItems = orderdetailRepository.findAll();
		model.addAttribute("cartItems", cartItems);
		return "redirect:/cart";
	}

	@PostMapping("/addorder")
	public String addor(Model model, Orders or) {

		List<OrderDetail> cartItems = orderdetailRepository.findAllOderdeatil1();
		List<OrderDetail> cartItems1 = orderdetailRepository.findAllOderdeatil1();
		LocalDate now = LocalDate.now();
		double s = 0;
		for (OrderDetail item : cartItems1) {
			s += item.getQuantity() * item.getProduct().getPrice().doubleValue();
		}
		Orders orders = new Orders();
		orders.setOrderDate(now.toString());
		orders.setOrderStatus("cho xac nhan");
		orders.setOrderprice(s);
		orders.setShipaddress(or.getShipaddress());

		orderRepository.save(orders);
		orders.getId();
		for (OrderDetail orderDetail : cartItems) {
			orderDetail.setOrders(orders);
			orderDetail.setTrangthai(Boolean.FALSE);
			orderdetailRepository.save(orderDetail);
		}
		return "redirect:/home";
	}

	@GetMapping("/addorder")
	public String addorview(Model model) {

		model.addAttribute("or", new Orders());
		return "shipping";
	}

	@RequestMapping(value = "/update-cart/{id}", method = RequestMethod.POST, params = "action=update")
	public String updateCart(@PathVariable Integer id, OrderDetail orderDetail, Model model, Principal principal) {

		OrderDetail orderDetail1 = orderdetailRepository.findById(id).orElse(null);
		orderDetail1.setQuantity(orderDetail.getQuantity());
		orderDetail1.setPrice(orderDetail1.getQuantity() * orderDetail1.getPrice());
		orderdetailRepository.save(orderDetail1);
		return "redirect:/cart";

	}

	@RequestMapping(value = "/update-cart/{id}", method = RequestMethod.POST, params = "action=delete")
	public String deleteItem(@PathVariable Integer id, Model model, Principal principal) {
		orderdetailRepository.deleteById(id);
		return "redirect:/cart";
	}

	/*
	 * @GetMapping("/list-hang-dat") public String viewHangDat(Model
	 * model, @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize,
	 * 
	 * @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer
	 * pageNum) { Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
	 * Page<OrderDetail> page =
	 * orderdetailRepository.findAllOderdeatifasle(pageable);
	 * model.addAttribute("sl", orderdetailRepository.sl());
	 * model.addAttribute("totalPage", page.getTotalPages());
	 * model.addAttribute("listhangdat", page.getContent());
	 * 
	 * return "listhangdat"; }
	 */
}
