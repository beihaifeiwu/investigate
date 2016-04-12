<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/Json2.js"></script>
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/ymPrompt.js"></script>
<link rel="stylesheet" type="text/css" href="../css/dmm-green/ymPrompt.css"  />
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

function selectAllBox(){
	 
	 var checkAll=document.getElementById("checkAll");
	 
	 var tags=document.getElementsByName("tags");

	 if(checkAll.checked==true){
	 
		 for(i = 0;i<tags.length;i++){
		 	tags[i].checked=true;
		 }
		 
	}else{
	 
	 	for(j=0;j<tags.length;j++){
	 		tags[j].checked=false;
	 	}
	 }
}

function submitForm(){
	//请求的地址
	var url = "<s:url action="pickCourse" namespace="/student"/>";
	//将表单域的值转换为请求参数
	var params = Form.serialize('pickForm');
	//创建Ajax.Request对象，对应于发送请求
	var myAjax = new Ajax.Request(
			url,
			{
				//请求方式：Post
				method:'post',
				//请求参数
				parameters:params,
				//指定回调函数
				onComplete: processResponse,
				//是否异步发送请求
				asynchronous:true
			}
		);
}

function processResponse(request){
	//使用JSON对象将服务器响应解析成JSON对象
	var res = JSON.parse(request.responseText);
	//获取JSON对象的属性
	var str = res['result'];
	if(str == "conflict"){
		ymPrompt.alert({message:'所选课程与已选课程冲突',title:'选课失败！',handler:handler});
	}else if(str == "full"){
		ymPrompt.alert({message:'所选课程人数已满',title:'选课失败！',handler:handler});		
	}else if(str == "selected"){
		ymPrompt.succeedInfo({message:'请选择下一门或退出',title:'选课成功！',handler:handler});
	}else if(str == "null"){
		ymPrompt.alert({message:'您必须选择至少一门课程！',title:'选课警告！',handler:handler});		
	}else if(str == "exist"){
		ymPrompt.alert({message:'该课程已在您的课表中！！',title:'选课警告！',handler:handler});		
	}else{
		ymPrompt.errorInfo({message:'请重新选择或退出',title:'选课出错！',handler:handler});		
	}
	
	 var tags=document.getElementsByName("tags");
	 for(var i = 0;i<tags.length;i++){
		 	tags[i].checked=false;
	}
}

function handler(tp){
	//可以处理对话框响应
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
         <span class="STYLE4">查看可选课程列表</span>
        </td>
        <td width="220" background="../images/tab_05.gif"  height="30">
        <div style="width: 220px;height: 30px; float: none;overflow: hidden;">
        	<div style="width:60px;height: 30px;float: left;overflow: hidden;text-align: center;padding-top: 8px;">
              <table width="87%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <td class="STYLE1">
                 <div align="center">
                  <input type="checkbox" name="checkAll" id="checkAll" value="checkbox" onchange="selectAllBox()"/>
                 </div>
                </td>
                <td class="STYLE1"><div align="center">全选</div></td>
               </tr>
              </table>        	
        	</div>
        	<div style="width:80px;height: 30px;float:left;overflow: hidden; text-align: center;padding-top: 10px;">
					<a href="<s:url action="prepareToSearch" namespace="/student"/>" class="STYLE1">搜索课程</a>        		
        	</div>
            <div style="width:80px;height: 30px;float:left; overflow: hidden; text-align: center;padding-top: 4px;">
			   		<button class="STYLE1" onclick="submitForm()">提交</button>
	   	   </div> 	
        </div>
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
	        <form id="pickForm" name="pickForm">
		        <table width="100%" border="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
		  		  <tr>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">选择</th>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">课程号</th>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">课序号</th>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">课程名</th>
			  		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">学分</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">任课教师</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">上课地点</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">上课时间</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">课容量</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">选课人数</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">选课限制</th>
			   		  <th scope="col" height="18" background="../images/tab_14.gif" class="STYLE1">课程状态</th>
			 	  </tr>
			 	  <s:iterator value="pageQuery.datas" id="entity">
			      <tr>
			      	  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1">
			      	  	<input type="checkbox" name="tags" value="<s:property value="#entity.ID"/>"/>
			      	  </td>
				      <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.CourseNumber"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.ClassNumber"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.CourseName"/></td>
			  		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.Credit"/></td>
			   		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.teacher.profile.Name"/></td>
			    	  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.classroom.RoomName"/></td>
			    	  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.timepoint.description"/></td>
			   		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.Capacity"/></td>
			   		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.courseState.studentNumber"/></td>
			   		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.attribute"/></td>
			   		  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:property value="#entity.courseState.stateType.value"/></td>
			  	 </tr>
			  	 </s:iterator>
		     </table>
		     </form>
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
	                  		<s:url action="%{action}" namespace="/student" var="first">
	                  			<s:param name="pageQuery.currentIndex" value="1"/>
	                  		</s:url>
	                  		<s:a href="%{first}" target="tabFrame"><img src="../images/first.gif" width="37" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="50" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="%{action}" namespace="/student" var="previous">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.previousPage}"/>
	                  		</s:url>	                  		
	                  		<s:a href="%{previous}" target="tabFrame"><img src="../images/back.gif" width="43" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="54" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="%{action}" namespace="/student" var="next">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.nextPage}"/>
	                  		</s:url>	                  			                  		
	                  		<s:a href="%{next}" target="tabFrame"><img src="../images/next.gif" width="43" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="49" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="%{action}" namespace="/student" var="last">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.lastPage}"/>
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
	                  <s:form action="%{action}" namespace="/student" method="post" id="pageJump">
	                  	  <s:hidden name="jump" value="true"/>
	                  	  <s:hidden name="pageQuery.currentIndex" value="%{pageQuery.lastPage}"/>
	                  	  <s:hidden name="pageQuery.totalNum" value="%{pageQuery.totalNum}"/>
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