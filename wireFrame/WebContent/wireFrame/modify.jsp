<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
<div align="center">
	<form name="joinForm">
	  <table height="350px" width="450px">
	  	<tr>
	      <td colspan="3" style="border:1px solid black; background-color:Aquamarine" align="center">회원정보 수정</td>
	    </tr>
	    <tr>
	      <td colspan="3"><input type="text" value="suseongland@gmail.com" style="width:98%; height:100%" /></td>
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
	      <td colspan="3"><input type="text" name="opinion" value="닉네임수정" style="width:98%; height:100%" /></td>
	    </tr>
	    <tr>
	      <td colspan="3" class="item">
	      	<select name="interests" style="height:100%; width:100%;">
	          <option value="">의견 관심분야 선택</option>
	          <option value="game">게임</option>
	          <option value="SNS">SNS</option>
	          <option value="movie">동영상</option>
	          <option value="search">검색</option>
	        </select>
	      </td>
	    </tr>
	    <tr>
	      <td colspan="3">
	      	<button type="button" onclick="join();" style="width:200px; height:100%; background-color:Aquamarine; border:0; outline:0;">회원정보수정</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      	<button type="button" style="width:200px; height:100%; background-color:Aquamarine; border:0; outline:0;">취소</button>
	      </td>
	    </tr>
	  </table>
	</form>
</div>


</body>
</html>