<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>

<link type="text/css" rel="stylesheet" href="../css/calendar.css" />
<script type="text/javascript" src="../js/calendar.js" ></script>  
<script type="text/javascript" src="../js/calendar-zh.js" ></script>
<script type="text/javascript" src="../js/calendar-setup.js"></script>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-center: 0px;
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

.errorMessage {
   font-weight: normal;
   color:red;
}

.required {
   color:red;
}
fieldset {
    font-size: 12px;
    color: #1F4A65;
    font-weight: bold;
    border-color: #f2fee2;
    margin-bottom: 5px;
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
         <span class="STYLE4">教师详细信息</span>
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
        	<div align="left" style="margin-left: 50px; margin: 50px;" class="STYLE4">
				<table width="100%" bgcolor="#c0de98" border="0" cellspacing="2" cellpadding="5px">
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>教职工号</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:property value="teacher.account" /></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>职称</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:property value="teacher.title" /></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>姓名</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:property value="teacher.profile.name" /></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>照片</label></td>
						<td bgcolor="#FFFFFF" align="center">
						<s:if test="teacher.profile.Photo != null">
							<s:url var="url" action="image" namespace="/">
								<s:param name="userID"><s:property value="ID"/></s:param>
							</s:url>
							<img src="<s:property value="#url"/>" width="60" height="80"></img>
						</s:if>
						<s:else>
							<span>暂无照片</span>
						</s:else>
						</td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>手机号码</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:property value="teacher.profile.phoneNumber" /></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>电子邮件</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:property value="teacher.profile.email" /></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>所属院系</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:property value="teacher.profile.department" /></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>籍贯</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:property value="teacher.profile.birthPalce" /></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" align="center"><label>出生日期</label></td>
						<td bgcolor="#FFFFFF" align="center"><s:date name="teacher.profile.birthTime" /></td>
					</tr>
				</table>
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