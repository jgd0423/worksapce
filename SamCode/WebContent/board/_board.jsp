<script>
	window.onload = function(){
		//alert('aaa1');
		sijak();
	}

	function sijak() {
		suntaek_proc('list', '1', '');
	}

	function suntaek_all() {
 		$("#span_search_option").text("");
 		$("#span_search_data").text("");
 		suntaek_proc('list', '1', '');
 	}
	
	// 0-건너뜀, ''-공백
	function suntaek_proc(value1, value2, value3) {
		//alert('bbb');
		$("#span_proc").text(value1);

		if (value2 != "0") {
			$("#span_pageNumber").text(value2);
		}

		if (value3 != "0") {
			$("#span_no").text(value3);
		}

 		GoPage(value1);	
 	}
	
	function GoPage(value1) {
		var url = "${path}/board_servlet/" + value1 + ".do";
		console.log(url);

		if (value1 == "list") {
			var param = {
				"tbl" : $("#span_tbl").text(),
				"pageNumber" : $("#span_pageNumber").text(),
				"search_option" : $("#span_search_option").text(),
				"search_data" : $("#span_search_data").text()
			}
		} else if (value1 == "chuga") {
			var param = {}
			
		} else if (value1 == "reply" || value1 == "sujung" || value1 == "sakje") {
			var param = {
				"no" : $("#span_no").text()
			}
			
		} else if (value1 == "chugaProc" || value1 == "replyProc" || value1 == "sujungProc" || value1 == "sakjeProc") {
			var param = {
				"no" : $("#span_no").text(),
				"tbl" : $("#span_tbl").text(),
				"writer" : $("#writer").val(),
				"email" : $("#email").val(),
				"passwd" : $("#passwd").val(),
				"subject" : $("#subject").val(),
				"content" : $("#content").val(),
				"noticeGubun" : $("#noticeGubun").val(),
				"secretGubun" : $("#secretGubun").val()
			}

		} else if (value1 == "view") {
			var param = {
				"tbl" : $("#span_tbl").text(),
				"pageNumber" : $("#span_pageNumber").text(),
				"no" : $("#span_no").text(),
				"search_option" : $("#span_search_option").text(),
				"search_data" : $("#span_search_data").text(),
				"view_passwd" : $("#view_passwd").val()
		    } 
		}

		$.ajax({
			type: "post",
			data: param,
			url: url,	
			success: function(data){
				if (value1 == "list") {
					$("#result").html(data);
				} else if (value1 == "view") {
					$("#result").html(data);
				} else if (value1 == "chuga") {
					$("#result").html(data);	
				} else if (value1 == "chugaProc") {
					suntaek_proc('list', '1', '');
				} else if (value1 == "reply") {
					$("#result").html(data);
				} else if (value1 == "replyProc") {
					suntaek_proc('list', $("#span_pageNumber").text(), '');
				} else if (value1 == "sujung") {
					$("#result").html(data);
				} else if (value1 == "sujungProc") {
					$("#result").html(data);
				} else if (value1 == "sakje") {
					$("#result").html(data);
				} else if (value1 == "sakjeProc") {
					suntaek_proc('list', '1', '');
					
				} else {
					//$("#result").html(data);
				}
			}		
		});
	}

	function clickChk(value1) {
		if (value1 == 'noticeGubun') {
			if($("input:checkbox[name=noticeGubunCheckBox]").is(":checked") == true) {
				$("#noticeGubun").val("T");
			} else {
				$("#noticeGubun").val("");
			}
		} else {
			if($("input:checkbox[name=secretGubunCheckBox]").is(":checked") == true) {
				$("#secretGubun").val("T");
			} else {
				$("#secretGubun").val("");
			}
		}
	}	
</script>