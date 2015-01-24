<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.freetmp.investigate.jsp.TestBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<%
			TestBean testBean = new TestBean("This is a test java bean.");
			request.setAttribute("bean", testBean);
		%>
		Java bean name is:${bean.name}
	</center>
</body>
</html>
