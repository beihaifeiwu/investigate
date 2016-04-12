<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
         <span class="STYLE4">等待处理</span>
        </td>
        <td width="14"><img src="../images/tab_07.gif" width="14" height="30" /></td>
      </tr>
     </table>
    </td>
  </tr>
  <tr>
    <td>

     <table class="STYLE4" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3">
			 <div id=demo style="color: #8cc105">
						正在处理您的请求，请稍候...
			 </div>
			 <div>如果没有跳转，请点击<a href="<s:url includeParams="all" />">此处！</a></div>
			<script> 
			var i=69 ;
			var url='<s:url includeParams="all" />';       //修改为你要加载的网页路径
			function load(){ 
				if(i<249){ 
				   i+=10 ;
				   demo.innerText+="■"  ;     //每500毫秒在id为demo的元素内的文字添加一个 ■ 
				       //设置id为demo的元素的文字颜色向白色渐变 
				   demo.style.color="rgb("+[0,i,0].join(",")+")"  ;
				   setTimeout("load()",100) ;//500毫秒后再次执行load()函数 
				 } else{
					 location=url;
				 }
			} 
			load(); //运行 load 函数 
			</script>
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