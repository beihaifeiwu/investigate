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
         <span class="STYLE4">添加新教师</span>
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
        	<div align="left" style="margin-left: 50px;" class="STYLE4">

				<s:form action="addNewTeacher" namespace="/admin" method="post" validate="true" enctype="multipart/form-data">
				    <s:token></s:token>
					<s:textfield label="教职工号" requiredLabel="true" name="teacher.account" disabled="false"></s:textfield>
					<s:password label="密码" requiredLabel="true" name="newPassword"></s:password>
					<s:password label="确认密码" requiredLabel="true" name="reNewPassword"></s:password>
					<s:textfield label="职称" requiredLabel="true" name="teacher.title"></s:textfield>
					<s:textfield label="姓名" requiredLabel="true" name="teacher.profile.name"></s:textfield>
					<s:textfield label="手机号码" requiredLabel="false" name="teacher.profile.phoneNumber"></s:textfield>
					<s:textfield label="电子邮件" name="teacher.profile.email"></s:textfield>
					<s:textfield label="所属院系" requiredLabel="true" name="teacher.profile.department"></s:textfield>
					<s:textfield label="籍贯" name="teacher.profile.BirthPlace"></s:textfield>
					<s:textfield label="出生日期" name="teacher.profile.BirthTime" id="EntTime" 
									onclick="return showCalendar('EntTime', 'y-mm-dd');"></s:textfield>
					<s:file label="照片" name="upload"></s:file>

					<s:submit value="提交"></s:submit>
				</s:form>
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