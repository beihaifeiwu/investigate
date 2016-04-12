<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
                <td width="100%" class="STYLE1">师生管理</td>
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
                          	<a href="<s:url action="scanStudent" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">查看学生信息</span></a>
                          </td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                    <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="menuitem">
                          	<a href="<s:url action="scanTeacher" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">查看教师信息</span></a>	
                          </td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                    <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="menuitem">
                          	<a href="<s:url action="loadTeacher" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">录入教师信息</span></a>
                          </td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                    <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="menuitem">
                          	<a href="<s:url action="loadStudent" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">录入学生信息</span></a>
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
            <td height="21" background="images/main_584.gif" id="imgmenu2" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(2)" onMouseOut="this.className='menu_title';" style="cursor:pointer"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="18%">&nbsp;</td>
                <td width="100%" class="STYLE1">教室管理</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td background="images/main_586.gif" id="submenu2">
			 <div class="sec_menu" >  
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="16%" height="25"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                    <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="menuitem">
                          	
                          	<a href="<s:url action="scanClassRoom" namespace="/admin" />" target="tabFrame"><span class="STYLE3">查看教室信息</span></a>
                          	
                          </td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                    <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="menuitem">
                          	<a href="<s:url action="loadClassRoom" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">录入教室信息</span></a>
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
            <td height="21" background="images/main_584.gif" id="imgmenu3" class="menu_title" onmouseover="this.className='menu_title2';" onclick="showsubmenu(3)" onmouseout="this.className='menu_title';" style="cursor:pointer"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="18%">&nbsp;</td>
                  <td width="100%" class="STYLE1">课程管理</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td background="images/main_586.gif" id="submenu3"><div class="sec_menu" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="16%" height="25"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="scanCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">所有课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="scanDeletedCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">已删除课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="scanUnDeletedCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">未删除课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="scanUnReadyCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">未完成课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="scanPickableCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">可选课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="loadCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">录入课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="searchCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">搜索课程</span></a>
                                </td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="menuitem">
                                	<a href="<s:url action="selectCourse" namespace="/admin"/>" target="tabFrame"><span class="STYLE3">选课管理</span></a>
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
