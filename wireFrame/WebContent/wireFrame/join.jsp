<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리(멤버십)</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

function test01() {
	$("#d1").show();
	$("#d2").hide();
	$("#d3").hide();
}

function test02() {
	$("#d1").hide();
	$("#d2").show();
	$("#d3").hide();
}

function test03() {
	$("#d1").hide();
	$("#d2").hide();
	$("#d3").show();
}

function join() {
	alert('회원가입이 완료되었습니다.')
	location.href="login.jsp";
}
</script>
</head>
<body>
<div align="center">
	<form name="joinForm">
	  <table height="700px">
	  	<tr>
	      <td colspan="3"  style="border:1px solid black; background-color:Aquamarine" align="center">회원관리(멤버십)</td>
	    </tr>
	    <tr>
	      <td colspan="3"><input type="text" placeholder="이메일주소 입력" style="width:98%; height:100%" /></td>
	    </tr>
	    <tr>
	      <td colspan="3">* 반드시 올바른 이메일 사용</td>
	    </tr>
	    <tr>
	      <td colspan="3"><input type="password" name="password" placeholder="비밀번호 (8자리 이상)" style="width:98%; height:100%"/></td>
	    </tr>
	    <tr>
	      <td colspan="3"><input type="password" name="passwordRecheck" placeholder="비밀번호 확인" style="width:98%; height:100%" /></td>
	    </tr>
	    <tr>
	      <td colspan="3"><input type="text" name="opinion" placeholder="의견 작성시 사용하는 이름을 적어 주세요." style="width:98%; height:100%" /></td>
	    </tr>
	    <tr>
	      <td colspan="3" class="item">
	      	<select name="interests" style="height:100%; width:50%;">
	          <option value="">의견 관심분야 선택</option>
	          <option value="game">게임</option>
	          <option value="SNS">SNS</option>
	          <option value="movie">동영상</option>
	          <option value="search">검색</option>
	        </select>
	      </td>
	    </tr>
	    <tr>
	      <td colspan="2">
	        <input type="text" name="phone" value="" placeholder="휴대폰 전화번호 입력('-' 제외)" style="width:96%; height:100%" />
	      </td>
	      <td><button type="button">인증번호 전송</button></td>
	    </tr>
	    <tr>
	      <td colspan="3"><input type="text" name="opinion" placeholder="인증번호 입력" style="width:98%; height:100%" /></td>
	    </tr>
	    <tr>
	      <td colspan="3" class="item">* 인증번호 전송을 확인해 주시기 바랍니다</td>
	    </tr>
	    <tr>
	    	<td style="border:1px solid black; text-align:center" onclick="test01();">서비스 이용약관</td>
	    	<td style="border:1px solid black; text-align:center" onclick="test02();">개인정보 취급방침</td>
	    	<td style="border:1px solid black; text-align:center" onclick="test03();">개인정보 제공동의</td>
	    </tr>
	    <tr>
	      <td colspan="3" style="border:1px solid black; height:100px;">
	      
		      <div id="d1">
						<textarea id="t1" style="width:98%; height:50px">서비스 이용약관 내용</textarea>
					</div>
		
					<div id="d2" style="display: none;">
						<textarea id="t2" style="width:98%; height:50px">개인정보 취급방침 내용</textarea>
					</div>
		
					<div id="d3" style="display: none;">
						<textarea id="t3" style="width:98%; height:50px">개인정보 제공동의 내용</textarea>
					</div>
	      
	      </td>
	    </tr>
	    <tr>
	      <td colspan="3" class="item"><input type="checkbox">서비스 이용약관, 개인정보 취급방침, 개인정보 제공동의</td>
	    </tr>
	    <tr>
	      <td colspan="3">
	      	<button type="button" onclick="join();" style="width:200px; height:100%; background-color:Aquamarine; border:0; outline:0;">회원가입</button>
	      	&nbsp;&nbsp;&nbsp;
	      	<button type="button" style="width:200px; height:100%; background-color:Aquamarine; border:0; outline:0;">취소</button>
	      </td>
	    </tr>
	  </table>
	</form>
</div>


</body>
</html>