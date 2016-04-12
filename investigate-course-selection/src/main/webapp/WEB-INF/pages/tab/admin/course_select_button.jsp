<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}
fieldset {
    font-size: 12px;
    color: #1F4A65;
    font-weight: bold;
    border-color: #f2fee2;
}
-->
</style>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30" /></td>
        <td width="100%" height="30" background="../images/tab_05.gif">
         <img src="../images/311.gif" width="16" height="16" />
         <span class="STYLE4">选课管理</span>
        </td>
        <td width="14"><img src="../images/tab_07.gif" width="14" height="30" /></td>
      </tr>
     </table>
    </td>
  </tr>
  <tr>
    <td>

     <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3">
        	<div align="left" style="margin-left: 20px;">
        	<div style="margin:20px;" class="STYLE4">
        		<s:fielderror/>
        		<fieldset>
        		<legend>开放选课</legend>
					 <p>点击开放选课后，数据库中所有状态为“已存储”的课程都将面向学生开放，直至结束选课</p>
					 <s:url action="openCourseSelect" namespace="/admin" var="url"></s:url>
					 <s:a href="%{url}"><button>开放选课</button></s:a>				        
		        </fieldset>
	        </div>
	        <div class="STYLE4" style="margin: 20px;">
	        	<fieldset>
	        	<legend>结束选课</legend>
	        		<p>点击结束选课后，数据库中所有状态为“可选”的课程都将转换状态为“已存储”</p>
					 <s:url action="closeCourseSelect" namespace="/admin" var="url"></s:url>
					 <s:a href="%{url}"><button>结束选课</button></s:a>				        
	        	</fieldset>
	        </div>
	        <div class="STYLE4" style="margin: 20px;">
	        	<fieldset>
	        	<legend>删除非选课</legend>
	        		<p>点击删除非选课后，数据库中所有状态为“已删除”的课程都将从数据库中移除</p>
					 <s:url action="removeCourse" namespace="/admin" var="url"></s:url>
					 <s:a href="%{url}"><button>删除非选课</button></s:a>				        
	        	</fieldset>
	        </div>
	        </div>
        </td>
        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
      </tr>
    </table>
   </td>
  </tr>
   
  <tr>
    <td height="29">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29" /></td>
	        <td width="100%" background="../images/tab_21.gif">&nbsp;</td>
	        <td width="14"><img src="../images/tab_22.gif" width="14" height="29" /></td>
	      </tr>
	    </table>
    </td>
  </tr>
</table>
</body>
</html>