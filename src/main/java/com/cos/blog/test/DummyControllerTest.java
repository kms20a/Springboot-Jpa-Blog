package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html 파일이 아니라 data를 리턴해주는 controller  = RestController
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
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
	//http://localhost:8000/blog/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//예외 사항이 없는 경우
		//User user = userRepository.findById(id).get();
		
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해!!
		/*
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				// TODO Auto-generated method stub
				return new User();
			}
		});
		*/
		
		/* 람다식
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다. id:" + id);
		});
		*/
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id:" + id);
			}
		});
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트 
		//변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		//스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		//만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한페이지당 2건에 데이터를 리턴받아 볼 예정
	//http://localhost:8000/blog/dummy/user
	//http://localhost:8000/blog/dummy/user/?page=0
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pageUser = userRepository.findAll(pageable);
		
		List<User> users = pageUser.getContent();
		return users;
	}
}
