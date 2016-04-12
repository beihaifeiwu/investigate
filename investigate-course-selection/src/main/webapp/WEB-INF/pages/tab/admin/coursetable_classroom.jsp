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

.STYLE5 {
  font-family:"宋体";
   font-size: 10px;
   font-weight: normal;
   padding: 5px;
   width: 100%;
   height: 100%;
}

.STYLE5 p {
  font-family:"宋体";
  font-size:10px;
}

.coursetable {
  padding-right: 5px; 
  border:solid 1px #eafcd5; 
  table-layout: fixed;
  margin-bottom: 5px;
}

.errorMessage {
   font-weight: normal;
   color:red;
}

.required {
   color:red;
}

fieldset {
    font-size: 12px;
    color: red;
    font-weight: bold;
    border-color: #f2fee2;
    width: 100%;
}
-->
</style>
<script type="text/javascript">
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

function onConflictCourseHover(){
	source=event.srcElement;
	var key = source.id;
	var ids = new Array;
	ids = key.split(",");
	var tr = document.getElementById(ids[1]);
	var td = tr.childNodes[ids[0].parseInt()];
	td.bgcolor = "#FFE3B7";
}
function onConflictCourseOut(){
	source=event.srcElement;
	var key = source.id;
	var ids = new Array;
	ids = key.split(",");
	var tr = document.getElementById(ids[1]);
	var td = tr.childNodes[ids[0].parseInt()];
	td.bgcolor = "#FFFFFF";
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
        <td width="100%" height="30" background="../images/tab_05.gif">
         <img src="../images/311.gif" width="16" height="16" />
         <span class="STYLE4">查看教室（<s:property value="classRoom.RoomName" />）课表</span>
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
        	<div class="STYLE5">
				<div>
		        <table width="100%" class="coursetable" cellspacing="1" bgcolor="#c0de98" 
		        	   onmouseover="changeto()"  onmouseout="changeback()" cellpadding="3px">
		          <tr>
		           <th bgcolor="#eafcd5" width="2%" scope="col">节次</th>
		           <th bgcolor="#eafcd5" width="14%" scope="col">星期一</th>
		           <th bgcolor="#eafcd5" width="14%" scope="col">星期二</th>
		           <th bgcolor="#eafcd5" width="14%" scope="col">星期三</th>
		           <th bgcolor="#eafcd5" width="14%" scope="col">星期四</th>
		           <th bgcolor="#eafcd5" width="14%" scope="col">星期五</th>
		           <th bgcolor="#eafcd5" width="14%" scope="col">星期六</th>
		           <th bgcolor="#eafcd5" width="14%" scope="col">星期日</th>
		          </tr>
		          <tr id="1">
		            <th bgcolor="#eafcd5" scope="row">1</th>
		            <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,1']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,1']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,1']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,1']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,1']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,1']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,1']"/></p></td>
		          </tr>
		          <tr id="2">
		            <th bgcolor="#eafcd5" scope="row">2</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,2']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,2']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,2']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,2']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,2']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,2']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,2']"/></p></td>
		          </tr>
		          <tr id="3">
		            <th bgcolor="#eafcd5" scope="row">3</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,3']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,3']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,3']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,3']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,3']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,3']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,3']"/></p></td>
		          </tr>
		          <tr id="4">
		            <th bgcolor="#eafcd5" scope="row">4</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,4']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,4']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,4']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,4']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,4']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,4']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,4']"/></p></td>
		          </tr>
		          <tr>
		            <th bgcolor="#eafcd5" scope="row">午休</th>
		            <td bgcolor="#eafcd5" colspan="7">&nbsp;</td>
		          </tr>
		          <tr id="5">
		            <th bgcolor="#eafcd5" scope="row">5</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,5']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,5']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,5']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,5']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,5']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,5']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,5']"/></p></td>
		          </tr>
		          <tr id="6">
		            <th bgcolor="#eafcd5" scope="row">6</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,6']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,6']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,6']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,6']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,6']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,6']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,6']"/></p></td>
		          </tr>
		          <tr id="7">
		            <th bgcolor="#eafcd5" scope="row">7</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,7']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,7']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,7']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,7']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,7']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,7']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,7']"/></p></td>
		          </tr>
		          <tr id="8">
		            <th bgcolor="#eafcd5" scope="row">8</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,8']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,8']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,8']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,8']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,8']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,8']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,8']"/></p></td>
		          </tr>
		          <tr>
		            <th bgcolor="#eafcd5" scope="row">晚餐</th>
		            <td bgcolor="#eafcd5" colspan="7">&nbsp;</td>
		          </tr>
		          <tr id="9">
		            <th bgcolor="#eafcd5" scope="row">9</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,9']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,9']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,9']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,9']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,9']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,9']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,9']"/></p></td>
		          </tr>
		          <tr id="10">
		            <th bgcolor="#eafcd5" scope="row">10</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,10']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,10']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,10']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,10']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,10']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,10']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,10']"/></p></td>
		          </tr>
		          <tr id="11">
		            <th bgcolor="#eafcd5" scope="row">11</th>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['1,11']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['2,11']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['3,11']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['4,11']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['5,11']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['6,11']"/></p></td>
		             <td bgcolor="#FFFFFF" align="center"><p><s:property value="courseTable['7,11']"/></p></td>
		          </tr>
		        </table>
		        </div>
		        <s:if test="courseTable.conflictMap.isEmpty()">
				</s:if>
				<s:else>
		        <div>
		        	<fieldset>
		        		<legend>与课表冲突的课程</legend>
		        		<s:iterator value="courseTable.conflictMap" var="entry" status="s">
		        			<div id="<s:property value="#entry.key"/>" onmouseover="onConflictCourseHover()" onmouseout="onConflictCourseOut()">
		        			<s:iterator value="entry.value.list" var="course">
		        				<div><s:property value="#course"/></div>
		        			</s:iterator>
		        			</div>
		        		</s:iterator>
		        	</fieldset>
		        </div>				
				</s:else>
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