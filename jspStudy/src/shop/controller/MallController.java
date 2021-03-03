package shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.common.UtilProduct;
import shop.model.dao.CartDAO;
import shop.model.dao.ProductDAO;
import shop.model.dto.CartDTO;
import shop.model.dto.ProductDTO;

/**
 * Servlet implementation class MallController
 */
@WebServlet("/mall_servlet/*")
public class MallController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		UtilProduct util = new UtilProduct();
		int[] yearMonthDayHourMinSec = util.getDateTime();
		HashMap<String, Integer> yearMonthDayMap = new HashMap<>();
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
		
		String pageNum_ = request.getParameter("pageNumber");
		int pageNum = util.numberCheck(pageNum_, 1);
		
		String no_ = request.getParameter("no");
		int no = util.numberCheck(no_, 0);
		
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];

		String[] sessionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sessionArray[0]);
		String cookId = sessionArray[1];
		String cookName = sessionArray[2];
		
		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		
		CartDAO cartDao = new CartDAO();
		CartDTO cartDto = new CartDTO();
		
		String page = "/main/main.jsp";
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "mall_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("list.do") != -1) {
			// paging
			int allRowsCount = dao.getAllRowsCount(search_option, search_data);
			final int ONE_PAGE_ROWS = 9;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			ArrayList<ProductDTO> list = dao.getPagingList(startNum, endNum, search_option, search_data);
			
			request.setAttribute("menu_gubun", "product_list");
			request.setAttribute("list", list);
			
			request.setAttribute("ONE_PAGE_ROWS", ONE_PAGE_ROWS);
			request.setAttribute("MAX_PAGING_WIDTH", MAX_PAGING_WIDTH);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("tableRowNum", tableRowNum);
			
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			int productListSize = list.size();
			int oneLineSize = 3;
			int loopNumforI = (int) Math.ceil((double) (productListSize / oneLineSize));
			
			request.setAttribute("productListSize", productListSize);
			request.setAttribute("oneLineSize", oneLineSize);
			request.setAttribute("loopNumforI", loopNumforI);
			
			page = "/shop/mall/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}
	}

}
