<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="165" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="20" background="images/main_585.gif" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="19%">&nbsp;</td>
        <td width="81%" height="20"><span class="STYLE1">管理菜单</span></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
    <table width="151" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="21" background="images/main_584.gif" id="imgmenu1" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(1)" onMouseOut="this.className='menu_title';" style="cursor:pointer"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="18%">&nbsp;</td>
                <td width="100%" class="STYLE1">个人中心</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td background="images/main_586.gif" id="submenu1">
			 <div class="sec_menu" >  
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="16%" height="25"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                    <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="menuitem">
                          	<a href="<s:url action="showDetail" namespace="/teacher"/>" target="tabFrame"><span class="STYLE3">个人信息</span></a>
                          </td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                    <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="menuitem">
                          	<a href="<s:url action="prepareToModifyTeacher" namespace="/teacher"/>" target="tabFrame"><span class="STYLE3">修改信息</span></a>
                          </td>
                        </tr>
                    </table></td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td height="5"><img src="images/main_587.gif" width="151" height="5" /></td>
              </tr>
            </table></div></td>
          </tr>
          
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="21" background="images/main_584.gif" id="imgmenu2" class="menu_title" onmouseover="this.className='menu_title2';" onclick="showsubmenu(2)" onmouseout="this.className='menu_title';" style="cursor:pointer"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="18%">&nbsp;</td>
                  <td width="100%" class="STYLE1">课程管理</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td background="images/main_586.gif" id="submenu2"><div class="sec_menu" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="16%" height="25"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="showCourse" namespace="/teacher"/>" target="tabFrame"><span class="STYLE3">查看课程信息</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="showCourseTable" namespace="/teacher"/>" target="tabFrame"><span class="STYLE3">查看课表</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="prepareToAdd" namespace="/teacher" />" target="tabFrame"><span class="STYLE3">提交课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="5"><img src="images/main_587.gif" width="151" height="5" /></td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table>
        </td>
      </tr>
	</table>
	</td>
  </tr>
</table>
