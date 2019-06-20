
package com.boot.todo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.todo.exception.DuplicateUserException;
import com.boot.todo.model.User;
import com.boot.todo.service.TodoService;
import com.boot.todo.util.PasswordEncoder;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam(value = "error", required = false) String error) {
		ModelAndView modelAndView = new ModelAndView("register");
		if (error != null)
			modelAndView.addObject("errorMsg", "User already Exists. Please enter details again or log in");
		User user = new User();
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(value = "/processRegistration", method = RequestMethod.POST)
	public ModelAndView processRegistration(@Valid User user, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return new ModelAndView("register");
			}
			user.setPassword(PasswordEncoder.encode(user.getPassword()));
			todoService.saveUserDetails(user);
		} catch (DuplicateUserException e) {
			return new ModelAndView("redirect:/register?error");
		}
		return new ModelAndView("redirect:/welcome");

	}

	@ModelAttribute("rolesList")
	public Map<String, String> getRoleList() {
		Map<String, String> roleList = new HashMap<String, String>();
		roleList.put("Admin", "ADMIN");
		roleList.put("User", "USER");
		return roleList;
	}

	@GetMapping("/welcome")
	public ModelAndView getWelcomePage() {
		ModelAndView modelView = new ModelAndView("welcome");
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		if (authorities.stream().anyMatch(p -> p.getAuthority().equals("ROLE_ADMIN")))
			modelView.addObject("role", "ROLE_ADMIN");
		return modelView;
	}

	@GetMapping("/getAllUsers")
	public ModelAndView getAllUsers(String deleted) {
		String role = "User";
		ModelAndView modelView = new ModelAndView("welcome");
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		if (authorities.stream().anyMatch(p -> p.getAuthority().equals("ROLE_ADMIN"))) {
			modelView.addObject("role", "ROLE_ADMIN");
			if (deleted != null) {
				modelView.addObject("deleteMsg", "User Deleted from the Database.");
			}
		}

		role = "Admin";

		List<User> usersList = todoService.getAllUsers(role);
		modelView.addObject("userList", usersList);
		return modelView;
	}

	@GetMapping("/delete-person")
	public ModelAndView deleteUser(@RequestParam(value = "id") String userName) {
		String role = "User";
		ModelAndView modelView = new ModelAndView("redirect:/getAllUsers?deleted");
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		if (authorities.stream().anyMatch(p -> p.getAuthority().equals("ROLE_ADMIN"))) {
			modelView.addObject("role", "ROLE_ADMIN");
			role = "Admin";
			todoService.deleteUser(userName);
		} else {
			modelView.setViewName("login");
		}

		return modelView;
	}

	@GetMapping("update-person")
	public ModelAndView updateUser(@RequestParam(value = "id",required=false) String userName,

			@RequestParam(value = "error", required = false) String error) {

		ModelAndView modelView = new ModelAndView("update");
		modelView.addObject("user", todoService.findUser(userName));
		if (error != null)
			modelView.addObject("errorMsg", "User with Username already Exists. Please enter details again.");
		return modelView;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("redirect:/update-person");
		}

		try {
			todoService.updateDetails(user);
		} catch (DuplicateUserException e) {
			return new ModelAndView("redirect:/update-person?error&id="+user.getUsername());
		}

		return new ModelAndView("redirect:/getAllUsers");

	}

}
