package shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.common.UtilProduct;
import shop.model.dao.ProductDAO;
import shop.model.dto.ProductDTO;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
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
		
		String page = "/main/main.jsp";
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "product_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "product_write");
			
			page = "/shop/product/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("list.do") != -1) {
			// paging
			int allRowsCount = dao.getAllRowsCount(search_option, search_data);
			final int ONE_PAGE_ROWS = 10;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			List<ProductDTO> list = dao.getPagingList(startNum, endNum, search_option, search_data);
			
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
			
			page = "/shop/product/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1 || url.indexOf("modifyProc.do") != -1) {
			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/product_img/");
			File isDir = new File(img_path01);
			if (!isDir.isDirectory()) {
				System.out.println("디렉토리가 존재하지 않습니다. 디렉토리를 생성합니다.");
				isDir.mkdir();
			}
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");
			final int MAX_SIZE = 10 * 1024 * 1024;
			
			System.out.println(img_path03);
			
			// MultipartRequest 선언되면 파일이 서버에 저장됨
			MultipartRequest multi = new MultipartRequest(request, img_path03, MAX_SIZE, "utf-8", new DefaultFileRenamePolicy());
			
			String tempNo = multi.getParameter("no");
			no = util.numberCheck(tempNo, 0);
			String name = multi.getParameter("name");
			String price_ = multi.getParameter("price");
			int price = Integer.parseInt(price_);
			String description = multi.getParameter("description");

			String[] fileNamesArray = new String[3];
			for (int i = 0; i < fileNamesArray.length; i++) {
				fileNamesArray[i] = "-";
			}
			
			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String formName = (String)files.nextElement();
				String fileName = multi.getFilesystemName(formName);
				String fileType = multi.getContentType(formName);
				
				System.out.println(fileType);
				
				if (fileName == null || fileName.trim().equals("")) {
					fileName = "-";
				}
								
				int fileNamesArrayIndex = Integer.parseInt(formName);
				fileNamesArray[fileNamesArrayIndex] = fileName;
				
				//String fileOrgName = multi.getOriginalFileName(formName);
				// 파일 타입으로 파일 체크해야함
				//String fileType = multi.getContentType(formName);
				//System.out.println("formName: " + formName);
				//System.out.println("fileName: " + fileName);		
				//System.out.println(formName + " : " + fileName);
				//System.out.println("fileOrgName: " + fileOrgName);
				//System.out.println("fileType: " + fileType);
			}
			
			for (int i = 0; i < fileNamesArray.length; i++) {
				// 배열 안에 파일명이 null일 경우
				String fileName = fileNamesArray[i];
				
				if (fileName == null) {
					continue;
				}
				
				if (fileName.equals("-")) {
					continue;
				}
				
				// 실제 경로에 파일이 존재하지 않을 경우
				String old_path = img_path03 + fileName;   // 원본이 업로드된 절대경로와 파일명
				File file = new File(old_path);
				if (!file.exists()) {
					fileNamesArray[i] = "-";
					continue;
				}
				
				// 파일명에 dot이 없을 경우
				int point_index = fileName.lastIndexOf(".");
				if (point_index == -1) {
					file.delete();
					fileNamesArray[i] = "-";
					continue;
				}
				
				// 확장자가 이미지가 아닐 경우
				String ext = "";
				ext = fileName.substring(point_index + 1).toLowerCase();
				if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("gif") || ext.equals("png"))) {
					file.delete();
					fileNamesArray[i] = "-";
					continue;
				}
				
				String uuid = util.createUuid();
				String newFileName = util.getDateTimeType() + "_" + uuid + "." + ext;
				File newFile = new File(img_path03 + newFileName);
				file.renameTo(newFile);   // 파일이동
				fileNamesArray[i] = fileNamesArray[i] + "|" + newFileName;
			}
			
			String product_img = "";
			for (int i = 0; i < fileNamesArray.length; i++) {
				String fileName = fileNamesArray[i];
				product_img += "," + fileName;
			}
			product_img = product_img.substring(1);
			
			dto.setNo(no);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			
			boolean isModifyProc = no > 0;
			String temp = "";
			int result;
			if (isModifyProc) {
				request.setAttribute("menu_gubun", "product_modifyProc");
				ProductDTO dbDto = dao.getView(no);
				String dbProduct_img = dbDto.getProduct_img();
				dto.setProduct_img(product_img);
				
				String deleteFileName = "";
				if (product_img.trim().equals("-,-,-")) {
					dto.setProduct_img(dbProduct_img);
				} else {
					String[] dbArray = dbProduct_img.split(",");
					for (int i = 0; i < fileNamesArray.length; i++) {
						if (fileNamesArray[i].equals("-")) {
							temp += "," + dbArray[i];
						} else {
							temp += "," + fileNamesArray[i];
							deleteFileName += "," + dbArray[i].substring(dbArray[i].lastIndexOf("|") + 1);
						}
					}
					deleteFileName = deleteFileName.substring(1);
					temp = temp.substring(1);
					dto.setProduct_img(temp);
				}
				result = dao.setUpdate(dto);
				
				String[] arrayDelete = deleteFileName.split(",");
				for (int i = 0; i < arrayDelete.length; i++) {
					if (!arrayDelete[i].trim().equals("-")) {
						File file = new File(img_path03 + arrayDelete[i]);
						file.delete();
					}
				}
			} else {
				request.setAttribute("menu_gubun", "product_writeProc");
				dto.setProduct_img(product_img);
				result = dao.setInsert(dto);
			}
			

		} else if (url.indexOf("view.do") != -1) {
			dto = dao.getView(no);
			
			String temp = dto.getDescription();
			if (temp != null) {
				temp = temp.replace("\n", "<br>");				
			}
			
			request.setAttribute("menu_gubun", "product_view");
			request.setAttribute("dto", dto);
			
			page = "/shop/product/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modify.do") != -1) {
			request.setAttribute("menu_gubun", "product_modify");
			
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			
			page = "/shop/product/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("delete.do") != -1) {
			request.setAttribute("menu_gubun", "product_delete");
			
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			
			page = "/shop/product/delete.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}  else if (url.indexOf("deleteProc.do") != -1) {
			request.setAttribute("menu_gubun", "product_deleteProc");
			dto = dao.getView(no);
			String product_img = dto.getProduct_img();
			
			if (!product_img.equals("-,-,-")) {
				String[] productImgArray = product_img.split(",");
				String deleteFileName = "";
				for (int i = 0; i < productImgArray.length; i++) {
					if (!productImgArray[i].equals("-")) {
						deleteFileName += "," + productImgArray[i].substring(productImgArray[i].lastIndexOf("|") + 1);
					}
				}
				
				deleteFileName = deleteFileName.substring(1);
				String[] arrayDelete = deleteFileName.split(",");
				
				String img_path01 = request.getSession().getServletContext().getRealPath("/attach/product_img/");
				File isDir = new File(img_path01);
				String img_path02 = img_path01.replace("\\", "/");
				String img_path03 = img_path01.replace("\\", "\\\\");
				for (int i = 0; i < arrayDelete.length; i++) {
					if (!arrayDelete[i].trim().equals("-")) {
						File file = new File(img_path03 + arrayDelete[i]);
						file.delete();
					}
				}
			}
			
			
			int result = dao.setDelete(dto);

			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}
