<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
}
.STYLE1 {
	font-size: 12px;
	color:black;
}
.STYLE3 {
	font-size: 12px;
	color: #033d61;
}
a:link {
	color: #033d61;text-decoration: none;
}
a:visited {
	color: #033d61;text-decoration: none;
}
-->
</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #ffffff; POSITION: relative; TOP: 2px 
}
.menu_title2 SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #FFCC00; POSITION: relative; TOP: 2px
}
.menuitem{
 height: 20px;
 cursor: pointer;
 border: none;
}
.menuitem:HOVER {
 border: solid 1px #82CF33;
}
</style>
<script>
var he=document.body.clientHeight-105
document.write("<div id=tt style=height:"+he+";overflow:hidden>")
</script>
</head>
<body>
<s:if test="#session.role == 'Admin'">
	<s:include value="left_admin.jsp"></s:include>
</s:if>
<s:elseif test="#session.role == 'Teacher'">
	<s:include value="left_teacher.jsp"></s:include>
</s:elseif>
<s:else>
	<s:include value="left_student.jsp"></s:include>
</s:else>

<script>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
imgmenu = eval("imgmenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
imgmenu.background="images/main_582.jpg";
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
imgmenu.background="images/main_582.jpg";
}
}
</script>
</body>
</html>
