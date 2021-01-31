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
</style>
</head>
<body>

<span id="showingSpace"></span><br>

<table border="1" height="400px" id="calcTable">
	<tr align=center>
		<td class="color number" id="7" width="100px" onclick="getNumber('7')">7</td>
		<td class="color number" id="8" width="100px" onclick="getNumber('8')">8</td>
		<td class="color number" id="9" width="100px" onclick="getNumber('9')">9</td>
		<td class="color symbol" id="+" width="100px" onclick="getSymbol('+')">+</td>
	</tr>
	<tr align=center>
		<td class="color number" id="4" onclick="getNumber('4')">4</td>
		<td class="color number" id="5" onclick="getNumber('5')">5</td>
		<td class="color number" id="6" onclick="getNumber('6')">6</td>
		<td class="color symbol" id="-" onclick="getSymbol('-')">-</td>
	</tr>
	<tr align=center>
		<td class="color number" id="1" onclick="getNumber('1')">1</td>
		<td class="color number" id="2" onclick="getNumber('2')">2</td>
		<td class="color number" id="3" onclick="getNumber('3')">3</td>
		<td class="color symbol" id="*" onclick="getSymbol('*')">*</td>
	</tr>
	<tr align=center>
		<td class="color resetCalc" id="Delete" onclick="resetCalc()">C</td>
		<td class="color number" id="0" onclick="getNumber('0')">0</td>
		<td class="color doCalc" id="Enter" onclick="doCalc()">=</td>
		<td class="color symbol" id="/" onclick="getSymbol('/')">/</td>
	</tr>
</table>

</body>

<script>

let preCalcStr = '';
let showingNum = '';
let isCalculating = false;

function resetCalc() {
	preCalcStr = '';
	showingNum = '';
	$('#showingSpace').text('');
	isCalculating = false;
}

function doCalc() {
	calcDoneNum = eval(preCalcStr);
	preCalcStr = calcDoneNum;
	showingNum = calcDoneNum;
	$('#showingSpace').text(calcDoneNum);
	isCalculating = false;
}

function getSymbol(symbol) {
	isCalculating = true;
	$('#showingSpace').text(showingNum);
	preCalcStr += symbol;
	showingNum = '';
}

function getNumber(num) {	
	if (isCalculating === false) {
		resetCalc();
		isCalculating = true;
	}
	
	if (preCalcStr.length !== 0 || num !== '0') {
		showingNum += num;
		preCalcStr += num;
	}
	
	$('#showingSpace').text(showingNum);
}

function showColor(e) {
  if (e.target.classList.contains('color')) {
	  e.target.classList.add('colorOn');
  }
}

function removeColor(e) {
	if (e.target.classList.contains('colorOn')) {
		  e.target.classList.remove('colorOn');
	  }
}

function mouseDownColor(e) {
	if (e.target.classList.contains('colorOn')) {
		e.target.style.backgroundColor = "#FE9A2E";		
	}
}

function mouseUpColor(e) {
	e.target.style.backgroundColor = '';
}

const html = document.querySelector('html');

html.addEventListener('keydown', (e) => {
	const eventBtn = document.querySelector(`[id='\${e.key}']`);
	eventBtn.style.backgroundColor = "#FE9A2E";
	if (eventBtn.classList.contains('number')) {
		getNumber(e.key);
	} else if (eventBtn.classList.contains('symbol')) {
		getSymbol(e.key);
	} else if (eventBtn.classList.contains('doCalc')) {
		doCalc();
	} else if (eventBtn.classList.contains('resetCalc')) {
		resetCalc();
	}
});

html.addEventListener('keyup', (e) => {
	const eventBtn = document.querySelector(`[id='\${e.key}']`);
	eventBtn.style.backgroundColor = '';
});

const calcTable = document.querySelector('#calcTable');
calcTable.addEventListener("mouseover", showColor);
calcTable.addEventListener("mouseout", removeColor);
calcTable.addEventListener("mousedown", mouseDownColor);
calcTable.addEventListener("mouseup", mouseUpColor);


</script>
</html>