<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style type="text/css">
table {
	font-size: 30px;
}

span {
	font-size: 50px;
	text-align: right;
	line-height: 80px;
	border: 1px solid;
	display: inline-block;
	width: 425px;
	height: 80px;
}
</STYLE>
</head>
<body>

showingSpace: <span id="showingSpace"></span><br>
calcSpace: <span id="calcSpace"></span>

<table border="1" height="400px">
	<tr align=center>
		<td id="7" width="100px" onclick="getNumber('7')">7</td>
		<td id="8" width="100px" onclick="getNumber('8')">8</td>
		<td id="9" width="100px" onclick="getNumber('9')">9</td>
		<td id="+" width="100px" onclick="getSymbol('+')">+</td>
	</tr>
	<tr align=center>
		<td id="4" onclick="getNumber('4')">4</td>
		<td id="5" onclick="getNumber('5')">5</td>
		<td id="6" onclick="getNumber('6')">6</td>
		<td id="-" onclick="getSymbol('-')">-</td>
	</tr>
	<tr align=center>
		<td id="1" onclick="getNumber('1')">1</td>
		<td id="2" onclick="getNumber('2')">2</td>
		<td id="3" onclick="getNumber('3')">3</td>
		<td id="*" onclick="getSymbol('*')">*</td>
	</tr>
	<tr align=center>
		<td id="C">C</td>
		<td id="0" onclick="getNumber('0')">0</td>
		<td id="=" onclick="doCalc()">=</td>
		<td id="/" onclick="getSymbol('/')">/</td>
	</tr>
</table>

</body>

<script>
let preNum = "";
let operatorSymbol = "";

function doCalc() {
	let calcSpaceText = $("#calcSpace").text();
	let calcSpaceNum = eval(calcSpaceText);
	let showingSpaceText = $("#showingSpace").text();
	let showingSpaceNum = eval(showingSpaceText);
	calcedNum = eval(calcSpaceNum + operatorSymbol + showingSpaceNum);
	$("#showingSpace").text(calcedNum);
	$("#calcSpace").text(calcedNum);

}

function getSymbol(symbol) {
	$("#calcSpace").text(preNum);
	$("#showingSpace").text('');
	preNum = "";
	operatorSymbol = symbol;
}

function getNumber(num) {
	if (preNum.length !== 0 || num !== '0') {
		preNum += num;
	}
	
	$("#showingSpace").text(preNum);
}

</script>
</html>