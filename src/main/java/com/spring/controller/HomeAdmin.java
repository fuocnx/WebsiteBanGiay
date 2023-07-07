package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.constain.SessionAtr;
import com.spring.entity.Orders;
import com.spring.entity.Product;
import com.spring.entity.Users;
import com.spring.repository.OrderRepository;
import com.spring.repository.OrderdetailRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeAdmin {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderdetailRepository orderdetailRepository;

	@GetMapping("/admin")
	public String homeAD(Model model, HttpSession session,
			@RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize,
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
		Users currentUser = (Users) session.getAttribute(SessionAtr.CURRENT_USER);
		if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
			Page<Orders> page = orderRepository.findAll(pageable);
			model.addAttribute("totalPage", page.getTotalPages());
			model.addAttribute("listhd", page.getContent());
			model.addAttribute("tong", orderRepository.count());
			model.addAttribute("tongtien", orderRepository.tongtiendonhang());
			return "homeAdmin";
		}
		return "redirect:/home";
	}

	@GetMapping("/admin/{id}")
	public String home(Model model, HttpSession session, @PathVariable Integer id,
			@RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize,
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
		Users currentUser = (Users) session.getAttribute(SessionAtr.CURRENT_USER);
		if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			model.addAttribute("idhd", id);
			model.addAttribute("listct", orderdetailRepository.findAllByid(id));
			Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
			Page<Orders> page = orderRepository.findAll(pageable);
			model.addAttribute("totalPage", page.getTotalPages());
			model.addAttribute("listhd", page.getContent());
			model.addAttribute("tong", orderRepository.count());
			model.addAttribute("tongtien", orderRepository.tongtiendonhang());
			return "homeAdmin";
		}
		return "redirect:/home";

	}

	@GetMapping("/admins/{id}")
	public String home1(Model model, HttpSession session, @PathVariable Integer id) {
		Users currentUser = (Users) session.getAttribute(SessionAtr.CURRENT_USER);
		if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {

			Orders or = orderRepository.findById(id).orElse(null);
			or.setOrderStatus("đa xac nhan");
			orderRepository.save(or);

			return "redirect:/admin";
		}
		return "redirect:/home";

	}
}
