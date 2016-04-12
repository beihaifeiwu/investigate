<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理平台</title>
</head>

<frameset rows="61,*,24" cols="*" framespacing="0" frameborder="no" border="0">
  <frame src="<s:url action="top"/>" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
  <!-- <frame src="<s:url action="center"/>" name="mainFrame" id="mainFrame" />-->
  <frameset cols="165,*" frameborder="no" border="0" id="centerFrame">
  	<frame src="<s:url action="left"/>" style="border-right:solid 1px #9ad452;" name="leftFrame" id="leftFrame"/>
  	<frame src="<s:url action="welcome"/>" name="tabFrame" id="tabFrame"/>
  </frameset>
  <frame src="<s:url action="down"/>" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
<body>

</body>
</html>