<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理平台</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {color: #528311; font-size: 12px; }
.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style>
<script type="text/javascript">
	function onSubmit(){
		document.getElementById('loginForm').submit();
	}
	function onReset(){
		document.getElementById('loginForm').reset();		
	}
</script>
</head>

<body>
<s:form action="login" namespace="/" method="post" id="loginForm">
<s:token></s:token>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf">&nbsp;</td>
  </tr>
  <tr>
    <td height="608" background="images/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="images/login_06.gif">&nbsp;</td>
            <td width="183" background="images/login_07.gif">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
            	  <tr><td colspan="2" style="color: red;">
            	  		<div align="center" style="font-size: 10px;margin: 0;padding: 0;">
            	  			<s:property value="#message"/></div>
            	  	</td></tr>
                  <tr>
	                <td width="21%" height="25"><div align="center"><span class="STYLE3">用户</span></div></td>
	                <td width="79%" height="25"><input type="text" name="username"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
	              </tr>
	              <tr>
	                <td height="25"><div align="center"><span class="STYLE3">密码</span></div></td>
	                <td height="25"><input type="password" name="passwd"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
	              </tr>
	              <tr>
	                <td height="25">&nbsp;</td>
	                <td height="25"><img src="images/dl.gif" width="81" height="22" border="0" usemap="#Map"></td>
	              </tr>
	           </table>
	        </td>
            <td width="255" background="images/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="247" valign="top" background="images/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%">&nbsp;</td>
            <td width="22%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;</td>
                <td width="56%" class="STYLE4">版本 2013V1.0 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#a2d962">&nbsp;</td>
  </tr>
</table>
</s:form>
<map name="Map"><area shape="rect" coords="3,3,36,19" href="#" onclick="onSubmit()"><area shape="rect" coords="40,3,78,18" href="#" onclick="onReset()"></map>
</body>
</html>