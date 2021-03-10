package mail.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mail.common.Util;
import mail.model.dto.EmailDTO;
import mail.service.EmailService;

@WebServlet("/email_servlet/*")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Util util = new Util();
		int[] yearMonthDayHourMinSec = util.getDateTime();
		Map<String, Integer> yearMonthDayMap = new HashMap<>();
		yearMonthDayMap.put("nowYear", yearMonthDayHourMinSec[0]);
		yearMonthDayMap.put("nowMonth", yearMonthDayHourMinSec[1]);
		yearMonthDayMap.put("nowDay", yearMonthDayHourMinSec[2]);
		
		String serverInfo[] = util.getServerInfo(request);   // request.getContextPath();
		String referer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
		// String ip6 = serverInfo[5];
		
		String page = "/main/main.jsp";
		
		if (url.indexOf("write.do") != -1) {
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			List<String> eMailList = new ArrayList<>();
			eMailList.add("suseongland@gmail.com");
			eMailList.add("suseongland@gmail.com");
			eMailList.add("suseongland@gmail.com");
			eMailList.add("suseongland@gmail.com");
			eMailList.add("suseongland@gmail.com");
			
			List<EmailDTO> dtoList = new ArrayList<>();
			
			for (String email : eMailList) {
				String birthDateStr = "1989-04-23";
				java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
				Date birthDate = null;
				try {
					birthDate = format.parse(birthDateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				EmailDTO dto = new EmailDTO();
				dto.setFromEmail("suseongland@gmail.com");
				dto.setFromName("정금담");
				dto.setSubject(subject);
				dto.setContent(content);
				dto.setToEmail(email);
				dto.setBirthDate(birthDate);
				dtoList.add(dto);
			}

			EmailService service = new EmailService();
			try {
				service.mailSender(dtoList);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}

	}
}