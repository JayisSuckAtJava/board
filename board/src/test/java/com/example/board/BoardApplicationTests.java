package com.example.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.board.model.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.UserRepository;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BoardRepository boardRepository;
	@Test
	void contextLoads() {
		User user =userRepository.findById(1L).get();
		System.out.println(user.getBoards() );
	}

	@Test
	void test() { // 패스워드 인코다
		PasswordEncoder pe = new BCryptPasswordEncoder();
		//String password = pe.encode("ad");
		//System.out.println(password);
		
		//System.out.println(pe.matches("ad","$2a$10$KWrUpzzvQhatG3mrANoB.eIb5.nye7eiND1B.dc2Rd0lbRJAwCydK"));
	}
}
