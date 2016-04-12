<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
-->
</style></head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed">
  <tr height="100%">
    <td background="images/main_40.gif" style="width:3px;">&nbsp;</td>
    <td width="177" style="border-right:solid 1px #9ad452;">
    	<iframe name="I2" height="100%" width="177" frameborder="0" src="<s:url action="left"/>">
		浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
		</iframe>
	</td>
    <td>
    	<iframe name="I1" src="<s:url action="welcome"/>" height="100%" width="100%" frameborder="0">
		浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
		</iframe>
	</td>
    <td background="images/main_42.gif" style="width:3px;">&nbsp;</td>
  </tr>
</table>
</body>
</html>