let index = {
	init: function(){
		//btn-save 버튼이 클릭 될때 실행하도록 바인딩
		//function(){} , ()=>{} 을 사용하는 것은 this를 바인딩하기 위해서!!
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		$("#btn-login").on("click", ()=>{
			this.login();
		});
	},
	/*
	function을 선언하고 내부에서 this를 사용하는 경우는
	윈도우 객체의 this를 가리키게 된다.
	만약 꼭 function을 사용해야 한다면 아래와 같이 사용을 한다.
	
	let _this = this;
	init: function(){
		$("#btn-save").on("click", function() {
			_this.save();
		});
	}
	*/
	
	
	save: function(){
		//alert('user의 save함수 호출됨');
		// username, password, email 의 값을 찾아서
		// object 객체로 만들어 준다.
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		//브라우저 콘솔로 데이터 확인
		//console.log(data);
		
		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 떄 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트로 변경해줌 
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			location.href="/blog";
			//alert(resp);
			console(resp);
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	login: function(){
		//alert('user의 save함수 호출됨');
		// username, password, email 의 값을 찾아서
		// object 객체로 만들어 준다.
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		
		//브라우저 콘솔로 데이터 확인
		//console.log(data);
		
		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 2개의 데이터를 json으로 변경하여 login 요청
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 떄 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트로 변경해줌 
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			location.href="/blog";
			//alert(resp);
			console(resp);
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();