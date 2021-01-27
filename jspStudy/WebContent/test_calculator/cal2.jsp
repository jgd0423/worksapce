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

.colorOn {
	background-color: #088A85;
}
</STYLE>
</head>
<body>

<span id="showingSpace"></span>
<span id="calcSpace" style="display: none"></span>

<table border="1" height="400px" id="calcTable">
	<tr align=center>
		<td class="color" id="7" width="100px" onclick="getNumber('7')">7</td>
		<td class="color" id="8" width="100px" onclick="getNumber('8')">8</td>
		<td class="color" id="9" width="100px" onclick="getNumber('9')">9</td>
		<td class="color" id="+" width="100px" onclick="getSymbol('+')">+</td>
	</tr>
	<tr align=center>
		<td class="color" id="4" onclick="getNumber('4')">4</td>
		<td class="color" id="5" onclick="getNumber('5')">5</td>
		<td class="color" id="6" onclick="getNumber('6')">6</td>
		<td class="color" id="-" onclick="getSymbol('-')">-</td>
	</tr>
	<tr align=center>
		<td class="color" id="1" onclick="getNumber('1')">1</td>
		<td class="color" id="2" onclick="getNumber('2')">2</td>
		<td class="color" id="3" onclick="getNumber('3')">3</td>
		<td class="color" id="*" onclick="getSymbol('*')">*</td>
	</tr>
	<tr align=center>
		<td class="color" id="C" onclick="resetCalc()">C</td>
		<td class="color" id="0" onclick="getNumber('0')">0</td>
		<td class="color" id="=" onclick="doCalc()">=</td>
		<td class="color" id="/" onclick="getSymbol('/')">/</td>
	</tr>
</table>

</body>

<script>
let preCalcStr = "";
let showingNum = "";

function resetCalc() {
	preCalcStr = "";
	showingNum = "";
	$("#showingSpace").text('');
	$("#calcSpace").text('');
}

function doCalc() {
	calcDoneNum = eval(preCalcStr);
	$("#showingSpace").text(calcDoneNum);
	$("#calcSpace").text('');
	preCalcStr = calcDoneNum;
	showingNum = '';
}

function getSymbol(symbol) {
	$("#showingSpace").text(showingNum);
	preCalcStr += symbol;
	$("#calcSpace").text(preCalcStr);
	showingNum = "";
}

function getNumber(num) {
// 	if ($("#calcSpace").text() === '' && preCalcStr !== '') {
// 		preCalcStr = '';
// 	}
	if (preCalcStr.length !== 0 || num !== '0') {
		showingNum += num;
		preCalcStr += num;
	}
	
	$("#showingSpace").text(showingNum);
}

const calcTable = document.querySelector("#calcTable");

function showColor(e) {
  if (e.target.className === 'color') {
	  console.log(e.target);
	  e.target.classList.add('colorOn');
  }
}

function removeColor(e) {
	if (e.target.className === 'color colorOn') {
		  console.log(e.target);
		  e.target.classList.remove('colorOn');
	  }
}

function mouseDownColor(e) {
	if (e.target.className === 'color colorOn') {
		e.target.style.backgroundColor = "#FE9A2E";		
	}
}

function mouseUpColor(e) {
	e.target.style.backgroundColor = '';
}

calcTable.addEventListener("mouseover", showColor);
calcTable.addEventListener("mouseout", removeColor);
calcTable.addEventListener("mousedown", mouseDownColor);
calcTable.addEventListener("mouseup", mouseUpColor);

</script>
</html>