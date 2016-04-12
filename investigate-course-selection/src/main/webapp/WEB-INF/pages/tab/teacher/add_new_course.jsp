<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"  />
<meta http-equiv="content-type" content="no-cache, must-revalidate" />
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
<title>无标题文档</title>
<script type="text/javascript" src="../js/Json2.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="../css/timetable.css"/>
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
         <span class="STYLE4">提交新课程</span>
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

				<s:form action="addNewCourse" namespace="/teacher" method="post" validate="true">
				    <s:token></s:token>
					<s:textfield label="课程号" requiredLabel="true" name="courseNumber" value="%{courseNumber}"></s:textfield>
					<s:textfield label="课序号" requiredLabel="true" name="classNumber" value="%{classNumber}"></s:textfield>
					<s:textfield label="课程名" requiredLabel="true" name="courseName" value="%{courseName}"></s:textfield>
					<s:textfield label="学分" requiredLabel="true" name="credit" value="%{credit}"></s:textfield>
					<s:textfield label="教职工号" requiredLabel="true" disabled="true" name="teacherAccount" value="%{teacherAccount}"></s:textfield>
					<s:textfield label="课容量" requiredLabel="true" name="capacity" value="%{capacity}"></s:textfield>
						<s:url action="getRoomNames" namespace="/teacher" var="name"></s:url>
						<s:url action="getRoomEmptyTime" namespace="/teacher" var="time"></s:url>
					<s:textfield label="上课地点" requiredLabel="true" id="time_table_select_room" onclick="time_table_onclick('%{name}','%{time}')"></s:textfield>
					<s:textfield label="上课时间" requiredLabel="true" id="time_table_select_time" onclick="time_table_onclick('%{name}','%{time}')"></s:textfield>
					<s:combobox label="选课限制" requiredLabel="true" labelposition="left" list="{'必修','选修'}" name="attribute" value="%{attribute}"/>
					<s:submit value="提交"></s:submit>
				</s:form>
				<script type="text/javascript" src="../js/timetable-0.2.4.js"></script>
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