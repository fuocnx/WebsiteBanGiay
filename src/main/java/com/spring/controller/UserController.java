package com.spring.controller;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.constain.SessionAtr;
import com.spring.entity.Users;
import com.spring.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	HttpServletRequest request;

	@Autowired
	private UsersRepository usersRepository;

	@GetMapping("/login")
	public String getLoginForm(Model model) {
		model.addAttribute("user", new Users());
		return "login";
	}

	@PostMapping("/login")
	public String checkLogin(Model model, @Valid @ModelAttribute("user") Users user, BindingResult result,
			HttpSession session, RedirectAttributes attributes) {
		// validate
		if (result.hasErrors()) {
			return "login";
		}

		// login success
		Users userFromDB = usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (userFromDB != null) {
			session.setAttribute(SessionAtr.CURRENT_USER, userFromDB);
			return "redirect:/home";
		}

		// login failed
		model.addAttribute("message", "Sai thong tin");
		return "login";

	}

	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		model.addAttribute("user1", new Users());
		return "register";
	}

	@PostMapping("/register")
	public String saveAcc(Model model, @Valid @ModelAttribute("user1") Users u, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return "register";
		}
		Users user = null;
		user = new Users(u.getUsername(), u.getPassword(), u.getFullname());
		user.setIsAdmin(Boolean.FALSE);
		usersRepository.save(user);
		Users userFromDB = usersRepository.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		session.setAttribute(SessionAtr.CURRENT_USER, userFromDB);
		return "redirect:/home";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute(SessionAtr.CURRENT_USER);
		return "redirect:/home";
	}

}
