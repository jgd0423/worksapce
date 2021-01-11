<%@page import="employees.dto.EmployeesDTO"%>
<%@page import="employees.dao.EmployeesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

EmployeesDAO dao = new EmployeesDAO();
EmployeesDTO dto = dao.getSelectOne("Steven", "King");

System.out.println(dto.getEmployee_id());
System.out.println(dto.getFirst_name());
System.out.println(dto.getLast_name());
System.out.println(dto.getEmail());
System.out.println(dto.getPhone_number());
System.out.println(dto.getHire_date());
System.out.println(dto.getJob_id());
System.out.println(dto.getSalary());
System.out.println(dto.getCommission_pct());
System.out.println(dto.getManager_id());
System.out.println(dto.getDepartment_id());

%>