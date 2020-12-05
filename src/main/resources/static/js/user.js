let index = {
	init: function(){
		//btn-save 버튼이 클릭 될때 실행하도록 바인딩
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
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
		
		$.ajax().done().fail();	//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	}
}

index.init();