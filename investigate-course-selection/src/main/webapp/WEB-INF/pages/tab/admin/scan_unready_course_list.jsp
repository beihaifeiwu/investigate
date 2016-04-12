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

<script type="text/javascript" src="../js/ymPrompt.js"></script>
<link rel="stylesheet" type="text/css" href="../css/dmm-green/ymPrompt.css"  />
<script type="text/javascript">
function handler(tp){
	//可以处理对话框响应
	if(tp == "ok"){
		submitForm();
	}
}
function emptyHandler(tp){
	//可以处理对话框响应
}
function confirmDelete(){
	 var tags=document.getElementsByName("tags");
	 var checked = false;
	 for(var i = 0;i<tags.length;i++){
		 	if(tags[i].checked == true){
		 		checked = true;
		 	};
		}
	 if(checked == false){
		ymPrompt.alert({message:'您至少要选择一门课程！',title:"删除警告！",handler:emptyHandler});
		 
	 }else{
		ymPrompt.confirmInfo({message:'您确认要删除此课程？',title:"确认删除！",handler:handler});
	 }
}
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
	document.getElementById("deleteForm").submit();
}

function modify(){
	var tag = event.srcElement;
	var aImg = document.getElementById("modifyImage");
	var aTxt = document.getElementById("modifyText");
	
	var aImgHref = aImg.getAttribute("href");
	var aTxtHref = aTxt.getAttribute("href");
	
	var index = aImgHref.lastIndexOf("?");
	if(index != -1){
		aImgHref = aImgHref.substring(0,index);
	}
	index = aTxtHref.lastIndexOf("?");
	if(index != -1){
		aTxtHref = aTxtHref.substring(0,index);
	}
	if(tag.checked == true){
		aImgHref += "?courseID=" + tag.value;
		aTxtHref += "?courseID=" + tag.value;	
	}else{
		 var tags=document.getElementsByName("tags");
		 if(aImgHref.lastIndexOf("?") == -1){
			 for(var i = 0; i < tags.length; i++){
				 if(tags[i].checked == true){
					aImgHref += "?courseID=" + tags[i].value;
					aTxtHref += "?courseID=" + tags[i].value;
					break;
				 }
			 }
		 }
	}
	aImg.setAttribute("href",aImgHref);
	aTxt.setAttribute("href",aTxtHref);
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
        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16" height="16" />
         <span class="STYLE4">查看课程列表</span>
        </td>
        <td width="281" background="../images/tab_05.gif">
         <table border="0" align="right" cellpadding="0" cellspacing="0">
           <tr>
             <td width="60">
              <table width="87%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <td class="STYLE1">
                 <div align="center">
                  <input type="checkbox" name="checkAll" id="checkAll" value="checkbox" onchange="selectAllBox()"/>
                 </div></td>
                <td class="STYLE1"><div align="center">全选</div></td>
               </tr>
              </table>
             </td>
             <td width="60">
              <table width="90%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <td class="STYLE1"><div align="center"><a href="<s:url action="addCourse" namespace="/admin"/>"><img src="../images/001.gif" width="14" height="14" /></a></div></td>
                <td class="STYLE1"><div align="center"><a href="<s:url action="addCourse" namespace="/admin"/>">新增</a></div></td>
               </tr>
              </table>
             </td>
             <td width="60">
              <table width="90%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <td class="STYLE1"><div align="center"><a id="modifyImage" href="<s:url action="jumpToModifyCourse" namespace="/admin"/>" target="tabFrame"><img src="../images/114.gif" width="14" height="14" /></a></div></td>
                <td class="STYLE1"><div align="center"><a id="modifyText" href="<s:url action="jumpToModifyCourse" namespace="/admin"/>" target="tabFrame">修改</a></div></td>
               </tr>
              </table>
             </td>
             <td width="52">
              <table width="88%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <td class="STYLE1"><div align="center"><a onclick="confirmDelete()"><img src="../images/083.gif" width="14" height="14" /></a></div></td>
                 <td class="STYLE1"><div align="center"><a onclick="confirmDelete()">删除</a></div></td>
               </tr>
              </table>
             </td>
           </tr>
         </table>
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
			 	  <s:form id="deleteForm" action="deleteCourse" namespace="/admin" method="post" theme="simple">
			 	  <s:iterator value="pageQuery.datas" id="entity">
			      <tr>
			      	  <td bgcolor="#FFFFFF" align="center" height="18" class="STYLE1"><s:checkbox name="tags" fieldValue="%{#entity.ID}" onchange="modify()"/></td>
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
			  	 </s:form>
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
	                  		<s:url action="scanUnReadyCourse" namespace="/admin" var="first">
	                  			<s:param name="pageQuery.currentIndex" value="1"/>
	                  		</s:url>
	                  		<s:a href="%{first}" target="tabFrame"><img src="../images/first.gif" width="37" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="50" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="scanUnReadyCourse" namespace="/admin" var="previous">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.previousPage}"/>
	                  		</s:url>	                  		
	                  		<s:a href="%{previous}" target="tabFrame"><img src="../images/back.gif" width="43" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="54" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="scanUnReadyCourse" namespace="/admin" var="next">
	                  			<s:param name="pageQuery.currentIndex" value="%{pageQuery.nextPage}"/>
	                  		</s:url>	                  			                  		
	                  		<s:a href="%{next}" target="tabFrame"><img src="../images/next.gif" width="43" height="15" /></s:a>
	                  	</div>
	                  </td>
	                  <td width="49" height="22" valign="middle">
	                  	<div align="right">
	                  		<s:url action="scanUnReadyCourse" namespace="/admin" var="last">
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
	                  <s:form action="scanUnReadyCourse" namespace="/admin" method="post" id="pageJump">
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