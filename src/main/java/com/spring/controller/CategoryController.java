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

import com.spring.entity.Category;
import com.spring.entity.Product;
import com.spring.repository.CategoryRepository;
import com.spring.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;

	@GetMapping("admin/view-category")
	public String viewCate(Model model) {
		model.addAttribute("list", categoryRepository.findAll());
		return "view-category";
	}

	@GetMapping("admin/add-category")
	public String viewadd(Model model) {
		 model.addAttribute("category", new Category()); 
		return "add-category";
	}

	@PostMapping("admin/add-category")
	public String addproduct(Model model, @Valid Category cat, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "add-category";
		}
		/*
		 * Category p = null; p = new Category(cat.getName());
		 */
		categoryRepository.save(cat);
		return "redirect:/admin/view-category";
	}

	@GetMapping("admin/delete-category/{id}")
	public String deletep(Model model, @PathVariable(name = "id") Integer id) {
		categoryRepository.deleteById(id);
		model.addAttribute("list", categoryRepository.findAll());
		return "redirect:/admin/view-category";
	}

	@GetMapping("admin/update-category/{id}")
	public String updatepd(Model model, @PathVariable(name = "id") Integer id) {
		Optional<Category> otf = categoryRepository.findById(id);
		Category dto = otf.get();
		model.addAttribute("cate1", dto);
		return "update-category";
	}

	@PostMapping("admin/update-category/{id}")
	public String updateproduct(Model model, Category cat, @PathVariable Integer id) {

		Category foundpd = categoryRepository.findById(id).orElse(null);
		foundpd.setName(cat.getName());
		categoryRepository.save(foundpd);

		return "redirect:/admin/view-category";
	}

}
