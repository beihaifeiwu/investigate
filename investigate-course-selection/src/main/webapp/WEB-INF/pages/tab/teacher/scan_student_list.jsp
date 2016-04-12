<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
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
a {
    cursor: pointer;
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
<script>

var  highlightcolor='#eafcd5';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	return;
	while(source.tagName!="TD")
	source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor=highlightcolor;
	}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	//source.style.backgroundColor=originalcolor
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor="";
	}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	return;
	while(source.tagName!="TD")
	source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor=clickcolor;
	}
	else
	for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor="";
	}
}

</script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30" /></td>
        <td width="100%" background="../images/tab_05.gif"><img src="../images/311.gif" width="16" height="16" />
         <span class="STYLE4">查看学生列表（<s:property value="course.CourseName"/>）</span>
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
        <div>
	        <div>
		        <table width="100%" border="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
		  		  <tr>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">学生学号</th>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">姓名</th>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">班级</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">所属院系</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">邮箱</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">电话号码</th>
			 	  </tr>
			 	  <s:iterator value="pageQuery.datas" id="entity">
			      <tr>
				      <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.account"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.profile.name"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.clazz"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.profile.department"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.profile.email"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.profile.PhoneNumber"/></td>
			  	 </tr>
			  	 </s:iterator>
		     </table>
	     </div>
        </div>
        </td>
        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29" /></td>
        <td background="../images/tab_21.gif">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap">
            	<span class="STYLE1">共<s:property value="pageQuery.totalNum"/>条纪录，当前第<s:property value="pageQuery.currentIndex"/>/<s:property value="pageQuery.totalIndex"/>页，每页<s:property value="pageQuery.pageContentNum"/>条纪录</span>
            </td>
            <td width="75%" valign="top" class="STYLE1">
            	<div align="right">
	              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                  <td width="62" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="%{action}" namespace="/teacher" var="first">
	                  			<s:param name="pageQuery.currentIndex" value="1"/>
	                  			<s:param name="courseID" value="%{courseID}"></s:param>
	                  		</s:url>
	                  		<s:a href="%{first}" target="tabFrame"><img src="../images/first.gif" width="37" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="50" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="%{action}" namespace="/teacher" var="previous">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.previousPage}"/>
	                  			<s:param name="courseID" value="%{courseID}"></s:param>
	                  		</s:url>	                  		
	                  		<s:a href="%{previous}" target="tabFrame"><img src="../images/back.gif" width="43" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="54" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="%{action}" namespace="/teacher" var="next">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.nextPage}"/>
	                  			<s:param name="courseID" value="%{courseID}"></s:param>
	                  		</s:url>	                  			                  		
	                  		<s:a href="%{next}" target="tabFrame"><img src="../images/next.gif" width="43" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="49" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="%{action}" namespace="/teacher" var="last">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.lastPage}"/>
	                  			<s:param name="courseID" value="%{courseID}"></s:param>
	                  		</s:url>	                  			                  			                  		
	                  		<s:a href="%{last}" target="tabFrame"><img src="../images/last.gif" width="37" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="150" height="22">
	                  	<script type="text/javascript">
	                  		function jumpOnClick(){
	                  			document.getElementById("pageJump").submit();
	                  		}
	                  	</script>
	                  <s:form action="%{action}" namespace="/teacher" method="post" id="pageJump">
	                  	  <s:hidden name="jump" value="true"/>
	                  	  <s:hidden name="pageQuery.currentIndex" value="%{pageQuery.lastPage}"/>
	                  	  <s:hidden name="pageQuery.totalNum" value="%{pageQuery.totalNum}"/>
	                  	  <s:hidden name="courseID" value="%{courseID}"></s:hidden>
		                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
		                  <td width="25" height="22" valign="middle">
		                  	<span class="STYLE7">
		                    	<input name="gotoPage" type="text" class="STYLE1" style="height:10px; width:25px;" size="5" />
		                  	</span>
		                  </td>
		                  <td width="23" height="22" valign="middle">页</td>
		                  <td width="30" height="22" valign="middle"><img onclick="jumpOnClick()" style="cursor: pointer;" src="../images/go.gif" width="37" height="15" /></td>
	                 </s:form>
	                 </td>
	                </tr>
	              </table>
               </div>
           </td>
          </tr>
        </table>
       </td>
       <td width="14"><img src="../images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>