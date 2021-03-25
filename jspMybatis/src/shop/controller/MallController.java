package shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		String sessionId = sessionArray[3];
		
		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		request.setAttribute("sessionId", sessionId);
		
		ProductDAO productDao = new ProductDAO();
		ProductDTO productDto = new ProductDTO();
		
		CartDAO cartDao = new CartDAO();
		CartDTO cartDto = new CartDTO();
		
		String page = "/main/main.jsp";
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "mall_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("mall_list.do") != -1) {
			// paging
			int allRowsCount = productDao.getAllRowsCount(search_option, search_data);
			final int ONE_PAGE_ROWS = 12;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			List<ProductDTO> list = productDao.getPagingList(startNum, endNum, search_option, search_data);
			
			request.setAttribute("menu_gubun", "mall_list");
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
			int oneLineSize = 4;
			int loopNumforI = (int) Math.ceil((double) (productListSize / oneLineSize)) - 1;
			if (loopNumforI < 0) {
				loopNumforI = 0;
			}
			
			request.setAttribute("productListSize", productListSize);
			request.setAttribute("oneLineSize", oneLineSize);
			request.setAttribute("loopNumforI", loopNumforI);

			page = "/shop/mall/mall_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("mall_view.do") != -1) {
			productDto = productDao.getView(no);
			
			String temp = productDto.getDescription();
			if (temp != null) {
				temp = temp.replace("\n", "<br>");				
			}
			
			request.setAttribute("menu_gubun", "product_view");
			request.setAttribute("dto", productDto);
			
			page = "/shop/mall/mall_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("cart_list.do") != -1 || url.indexOf("cart_add.do") != -1) {
			if (url.indexOf("cart_add.do") != -1) {
				String amount_ = request.getParameter("amount");
				int amount = util.numberCheck(amount_, 1);
				cartDto.setProductNo(no);
				cartDto.setAmount(amount);
				
				// cookNo가 0이면 비회원이므로 세션을 받는 다른 테이블에 저장해야한다
				if (cookNo > 0) {
					cartDto.setMemberNo(cookNo);
					int result = cartDao.setInsert(cartDto);
				} else {
					cartDto.setSessionId(sessionId);
					int result = cartDao.setInsertNonMember(cartDto);
				}
				
			}
			
			// paging
			int allRowsCount = 0;
			if (cookNo > 0) {
				allRowsCount = cartDao.getAllRowsCount(cookNo);				
			} else {
				allRowsCount = cartDao.getAllRowsCount(sessionId);
			}
			final int ONE_PAGE_ROWS = 10;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			List<CartDTO> list = new ArrayList<>();
			if (cookNo > 0) {
				list = cartDao.getPagingList(startNum, endNum, cookNo);
			} else {
				list = cartDao.getPagingList(startNum, endNum, sessionId);
			}
			
			
			request.setAttribute("menu_gubun", "cart_list");
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
			
			page = "/shop/mall/cart_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("cart_clear.do") != -1) {
			String chkNos = request.getParameter("chkNo");
			chkNos = chkNos.substring(1);
			String[] chkNoArray = chkNos.split(",");
			int result = cartDao.setDeleteBatch(chkNoArray);
		}
	}

}
