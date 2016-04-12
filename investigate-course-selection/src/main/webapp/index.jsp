<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>系统首页</title>
    <link rel="stylesheet" href="css/default/default.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>
<body>
    <div id="wrapper">
        <a href="<s:url action="login" namespace="/"/>" id="loginlink" title="login the course select system">dev7studios</a>

        <div class="slider-wrapper theme-default">
            <div id="slider" class="nivoSlider">
                <a href="http://ssdut.dlut.edu.cn/"><img src="images/slider0.jpg" data-thumb="images/slider0.jpg" alt="" /></a>
                <a href="http://www.dlut.edu.cn/"><img src="images/slider1.jpg" data-thumb="images/slider1.jpg" alt=""/></a>
                <a href="http://www.dlut.edu.cn/"><img src="images/slider2.jpg" data-thumb="images/slider2.jpg" alt="" data-transition="slideInLeft" /></a>
                <a href="http://ssdut.dlut.edu.cn/"><img src="images/slider3.jpg" data-thumb="images/slider3.jpg" alt="" /></a>
                <a href="http://210.30.96.38:8021/"><img src="images/slider4.jpg" data-thumb="images/slider4.jpg" alt="" /></a>
            </div>
            <div id="content" class="clearfix shadow">
				<h2>大连理工大学简介		</h2>
				<p>大连理工大学 1960年确定为教育部直属全国重点大学，国家&quot;211工程&quot;、&quot;985工程&quot;和&quot;111计划&quot;首批重点建设高校和自主招生&quot;卓越<br>
				</p>
				<p>联盟&quot;成员。教育部“援疆学科建设计划”40所重点高校之一。学校已形成以理工为主，经、管、文、法、哲等学科协调发展的多学<br>
			  </p>
				<p>科体系。大学国际化是学校的重要发展战略目标之一，现与24个国家和地区的168所大学和研究机建立了交流合作关系；建立2所孔<br>
			  </p>
				<p>子学院；名誉教授、客座教授320人。大连理工大学秉承“海纳百川、自强不息、厚德笃学、知行合一”的大工精神，以“985工程<br>
			  </p>
				<p>”、“211工程”、“领军型大学建设工程”为牵引，努力把大连理工大学建设成为国内高水平大学第一方阵的领军大学，建设成为<br>
			  </p>
				<p>国际知名的高水平研究型大学。</p>
			</div>
			<div id="footer" class="shadow">
				<p>&copy; 2013 JH&amp;LP</p>
			</div>
            
        </div>
    </div>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.nivo.slider.js"></script>
    <script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
</body>
</html>