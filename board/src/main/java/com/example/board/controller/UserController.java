package com.example.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.model.Board;
import com.example.board.model.User;
import com.example.board.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpSession session;

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user) {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String pw = pe.encode(user.getPwd());
		System.out.println(user.getPwd()+" 변경 전");
		user.setPwd(pw);
		System.out.println(user.getPwd()+" 변경 후");
		userRepository.save(user);
		return "redirect:/signin";
	}

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user) {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String pw = pe.encode(user.getPwd());	
		
		User dbUser =userRepository.findByEmail(user.getEmail());

		if(pw.matches(dbUser.getPwd())) {
			System.out.println("matches 통과");
		if(dbUser != null) {
			session.setAttribute("user_info", dbUser);
		}
	}else {
		System.out.println("matches 에서 else");
	}
		return "redirect:/";
	}

	@GetMapping("/signout")
	public String signout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		User user =(User) session.getAttribute("user_info");
		User result =userRepository.findById(user.getId()).get();
		List<Board> board = result.getBoards();
		model.addAttribute("list", board);
		model.addAttribute("user", result);
		return "mypage";
	}
	
	@PostMapping("/update")
	public String mypage(@ModelAttribute User user) {
		User before = (User) session.getAttribute("user_info");
		User result =userRepository.findById(before.getId()).get();
		user.setId(result.getId());
		user.setEmail(result.getEmail());
		if(user.getPwd().equals("")) {
			user.setPwd(result.getPwd());
		}
		userRepository.save(user);
		return "redirect:/mypage";
		
	}
}