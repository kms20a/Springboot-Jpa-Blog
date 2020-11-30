package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 x-www-form-urlencoded MIME 타입으로 username, password, email 데이터를 가지고 (요청)
	//username=hong&password=1234&email=hong@nate.com
	@PostMapping("/dummy/join")
	public String join(@RequestParam("username") String u, String password, String email) { //key=value(약속된 규칙)
		System.out.println("u : " + u);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		return "회원가입이 완료되었습니다.";
	}
	
	//Object로 파라미터 받기
	@PostMapping("/dummy/join2")
	public String join(User user) { //key=value(약속된 규칙)
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
