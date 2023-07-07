package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.Category;
import com.spring.entity.Product;
import com.spring.repository.CategoryRepository;
import com.spring.repository.OrderRepository;
import com.spring.repository.OrderdetailRepository;
import com.spring.repository.ProductRepository;

@Controller

public class HomeController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	OrderdetailRepository orderdetailRepository;

	@GetMapping("/home")
	public String viewproduct(Model model, @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize,
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		Page<Product> page = productRepository.findAll(pageable);
		model.addAttribute("sl", orderdetailRepository.sl());
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("list", page.getContent());
		return "index";
	}

	@GetMapping("/detail/{id}")
	public String detailPage(Model model, @PathVariable(name = "id") Integer productId,
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize) {
		model.addAttribute("detail", productRepository.findById(productId).orElse(null));
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		Page<Product> page = productRepository.findAll(pageable);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("list", page.getContent());
		return "product/detail";
	}

	@GetMapping("/about")
	public String viewproduct(Model model) {
		model.addAttribute("sl", orderdetailRepository.sl());
		model.addAttribute("mess",
				"Toogl promotes their core value of remote working throughout their website and their About Us page is a great example of how you can promote your core values too. As a core value to their business, their About Us page is focused around this global team and the productivity that they bring to the company. Not only do they put their team front and center, but they share how the team works so that someone interested in applying for a position with the company can be sure they will enjoy working for the company before they start.");
		return "about";
	}

	@GetMapping("/sanpham")
	public String viewsp(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("sl", orderdetailRepository.sl());
		model.addAttribute("categories", categories);
		model.addAttribute("list", productRepository.findAll());
		return "sanpham";
	}

	@GetMapping("/category/{id}/product")
	public String getAllProductById(Model model, @PathVariable Integer id) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		List<Product> products = productRepository.findAllByCategoryId_Native(id);
		model.addAttribute("sl", orderdetailRepository.sl());
		model.addAttribute("products", products);

		return "sanpham2";
	}

}
