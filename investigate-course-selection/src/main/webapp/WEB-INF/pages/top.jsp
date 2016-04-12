<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #43860c;
	font-size: 12px;
}
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
  <tr>
    <td height="9" style="line-height:9px; background-image:url(images/main_04.gif)">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="97" height="9" background="images/main_01.gif">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="47" background="images/main_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="38" height="47" background="images/main_06.gif">&nbsp;</td>
        <td width="59"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="29" background="images/main_07.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="18" background="images/main_14.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <tr>
                <td  style="width:1px;">&nbsp;</td>
                <td >
                	<s:if test="#session.role == 'Admin'">
                	<span class="STYLE1">Admin</span>
                	</s:if>
                	<s:else>
                	<span class="STYLE1"><s:property value="#session.username"/></span>                	
                	</s:else>
                </td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="155" background="images/main_08.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" valign="bottom"><img src="images/main_12.gif" width="367" height="23" border="0" usemap="#Map" /></td>
          </tr>
        </table></td>
        <td width="200" background="images/main_11.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="11%" height="23">&nbsp;</td>
            <%
            	Date now = new Date();
            	pageContext.setAttribute("now", now);
            %>
            <td width="89%" valign="bottom"><span class="STYLE1">日期：<s:date name="#attr.now" format="yyyy年MM月dd日 E"/></span></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" style="line-height:5px; background-image:url(images/main_18.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="165" background="images/main_16.gif"  style="line-height:5px;">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>

<map name="Map" id="Map">
	<area shape="rect" coords="3,1,49,22" href="<s:url action="welcome" namespace="/"/>" target="tabFrame" />
	<area shape="rect" coords="52,2,95,21" href="javascript:history.back()" />
	<area shape="rect" coords="102,2,144,21" href="javascript:history.forward()" />
	<area shape="rect" coords="150,1,197,22" href="javascript:parent.centerFrame.tabFrame.location.reload()" />
	<s:if test="#session.role == 'Student'">
		<s:url action="prepareToModifyStudent" namespace="/student" var="modify"></s:url>
	</s:if>
	<s:elseif test="#session.role == 'Admin'">
		<s:url action="prepareToModifyAdmin" namespace="/admin" var="modify"></s:url>
	</s:elseif>
	<s:else>
		<s:url action="prepareToModifyTeacher" namespace="/teacher" var="modify"></s:url>		
	</s:else>
	<area shape="rect" coords="210,2,304,20" href="<s:property value="modify"/>" target="tabFrame"/>
	<area shape="rect" coords="314,1,361,23" href="<s:url action="logout" namespace="/"/>" target="_top"/>
</map>
</body>
</html>