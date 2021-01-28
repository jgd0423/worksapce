<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.io.PrintWriter" %>

<%

String name = request.getParameter("name");
String kor_ = request.getParameter("kor");
String eng_ = request.getParameter("eng");
String mat_ = request.getParameter("mat");
String sci_ = request.getParameter("sci");
String his_ = request.getParameter("his");

int kor = Integer.parseInt(kor_);
int eng = Integer.parseInt(eng_);
int mat = Integer.parseInt(mat_);
int sci = Integer.parseInt(sci_);
int his = Integer.parseInt(his_);

int tot = kor + eng + mat + sci + his;
double avg = (double)tot / 5;


JSONObject jsonObj = new JSONObject();
jsonObj.put("name", name);
jsonObj.put("kor", kor);
jsonObj.put("eng", eng);
jsonObj.put("mat", mat);
jsonObj.put("sci", sci);
jsonObj.put("his", his);
jsonObj.put("tot", tot);
jsonObj.put("avg", avg);
String json_sj = jsonObj.toJSONString();
out.println(json_sj);

// System.out.println("json: " + json_sj);

// PrintWriter pw = response.getWriter();
// pw.print(json_sj);


%>
