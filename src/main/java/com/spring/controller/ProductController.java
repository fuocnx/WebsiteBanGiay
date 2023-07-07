package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.constain.SessionAtr;
import com.spring.entity.Category;
import com.spring.entity.Product;
import com.spring.entity.Users;
import com.spring.repository.CategoryRepository;
import com.spring.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class ProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("admin/view-product")
	public String viewproduct(Model model, HttpSession session) {
		Users currentUser = (Users) session.getAttribute(SessionAtr.CURRENT_USER);
		if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			model.addAttribute("list", productRepository.findAll());
			return "product/view-product";
		}
		return "redirect:/home";
	}

	@GetMapping("admin/add-product")
	public String viewadd(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("cate", categoryRepository.findAll());
		return "product/add-product";
	}

	@PostMapping("admin/add-product")
	public String addproduct(Model model, @Valid Product product, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("cate", categoryRepository.findAll());
			return "product/add-product";
		}

		productRepository.save(product);
		return "redirect:/admin/view-product";
	}

	/*
	 * @ModelAttribute(name = "cate") public List<Category> getAllCate() { return
	 * categoryRepository.findAll(); }
	 */

	@GetMapping("admin/delete-product/{id}")
	public String deletep(Model model, @PathVariable(name = "id") Integer id) {
		productRepository.deleteById(id);
		model.addAttribute("list", productRepository.findAll());
		return "redirect:/admin/view-product";
	}

	@GetMapping("admin/update-product/{id}")
	public String updatepd(Model model, @PathVariable(name = "id") Integer id) {
		Optional<Product> otf = productRepository.findById(id);
		Product dto = otf.get();
		if (otf.isPresent()) {
			model.addAttribute("product1", dto);
			model.addAttribute("cate", categoryRepository.findAll());
		}
		return "product/update-product";
	}

	@PostMapping("admin/update-product/{id}")
	public String updateproduct(Model model, @ModelAttribute("product1") Product product, @PathVariable Integer id) {

		Product foundpd = productRepository.findById(id).orElse(null);
		foundpd.setName(product.getName());
		foundpd.setImage(product.getImage());
		foundpd.setCategory(product.getCategory());
		foundpd.setPrice(product.getPrice());
		productRepository.save(foundpd);

		return "redirect:/admin/view-product";
	}
}
