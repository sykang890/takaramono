<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page"/>
<jsp:setProperty property="userID" name="user"/>
<jsp:setProperty property="userPassword" name="user"/>
<jsp:setProperty property="userName" name="user"/>
<jsp:setProperty property="userGender" name="user"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>>JSP 게시판 웹 사이트</title>
</head>
<body>
 <%
 String userID = null;
 if(session.getAttribute("userID")!=null){
	 userID = (String)session.getAttribute("userID");
 }
if( userID != null){
		PrintWriter script =response.getWriter();
		script.println("<script>");
  		script.println("alert('이미로그인되어있습니다.')");
	    script.println("location.href = 'main.jsp'");
	    script.println("</script>");
  }
  
 
 
 	if(user.getUserID()== null || user.getUserPassword()==null 
 			||user.getUserName()==null || user.getUserGender()==null){
 		PrintWriter script =response.getWriter();
 		  script.println("<script>");
 		  script.println("alert('입력이  다 안됐습니다.')");
 		  script.println("history.back()");
 		  script.println("</script>");
 	  }else{
		  UserDAO userDAO = new UserDAO();
		  int result = userDAO.join(user);
		  if(result == -1){
			  PrintWriter script =response.getWriter();
			  script.println("<script>");
			  script.println("alert('이미 존재합니다.')");
			  script.println("history.back()");
			  script.println("</script>");
		  }
		  else {
			  session.setAttribute("userID", user.getUserID());
			  PrintWriter script =response.getWriter();
			  script.println("<script>");
			  script.println("alert('회원가입을 축하드립니다.')");
			  script.println("location.href = 'main.jsp'");
			  script.println("</script>");
		  }
 	  }
   %>
 
</body>
</html>