<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>�ޱ����ĵ�</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index/indexJsp.css" />

</head>

<body>


<div id="header">
	<h1>���ɰ���</h1>
</div>
	
<div id="message" onDblClick="logoHideOrDisplay()">
	<div id="messageWrap">
		<strong> <sec:authentication property="principal.userName"/> : &nbsp;</strong>���ã���ӭʹ�ã�
		<img id="logoscroll" src="${pageContext.request.contextPath}/img/mainpage/up.png" onClick="logoHideOrDisplay()" alt="������ʾ������logo" />
	</div>
	
	<div id="navWrap">	
		<ul id="nav">
				<li id="t-intro"><a href="j_spring_security_logout">ע��</a></li>
				<li id="t-about"><a href="#">�޸�����</a></li>
				<li id="t-news"><a href="#">����</a></li>
				<li id="t-sponsors"><a href="#" onclick="loadmainframe('${pageContext.request.contextPath}/jsp/sys/sysConfig.jsp','${pageContext.request.contextPath}/jsp/calendar.jsp')">ϵͳ����</a></li>
				<li id="t-intro"><a href="#">�л��û�</a></li>
				<li id="t-about"><a href="#" onclick="loadmainframe('${pageContext.request.contextPath}/jsp/tree.jsp','${pageContext.request.contextPath}/jsp/calendar.jsp')">��ҳ</a></li>
				<li id="t-news"><a href="#">����ͨѶ</a></li>
				<li id="t-sponsors"><a href="#">˽��</a></li>
				<li id="t-game"><a href="#" onclick="loadworkareaframe('${pageContext.request.contextPath}/jsp/game/Game.html')">��Ϸ</a></li>
				<li id="t-intro"> 
					<s:select list="#{0:'�ڵ�1',1:'...'}"
						listKey="key"
                        listValue="value"
                        name="xtmk"
                        headerKey="-1" 
                        headerValue="������ת��..."
                        onchange=""
                        value=""/><!-- value��Ĭ��ֵ --> 
				</li>
		</ul>
	</div>
	
</div>


<div id="wrap">
	<div id="sideBar">
		<iframe id="sideFrame"  name="sideFrame" style="width:100%;" src="${pageContext.request.contextPath}/jsp/tree.jsp"></iframe>
	</div>  <!-- end sidebar -->
	
	<!-- ���ز������ť -->
	<div id="hideBar" ondblclick="sidebarHideOrDisplay()">
		<img src="${pageContext.request.contextPath}/img/mainpage/left.gif" id="hideBarImg" onClick="sidebarHideOrDisplay()" style="cursor: pointer;" alt="������ʾ�����ز����"/>
	</div>
	
	<div id="content">
		<iframe id="workArea"   name="workArea" style="width:100%;" src="${pageContext.request.contextPath}/jsp/calendar.jsp"></iframe>
		<input type="hidden" id="treeNodeId" name="treeNodeId"/>
	</div> <!--  End content  -->
</div>	
	
	
<div id="footer">
	<p>��Ȩ���� &copy; 2010 Sparta-��ɼ��</p>
</div>
	

</body>

<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/index/indexJsp.js"></SCRIPT>

<script> initFrames(); </script>

</html>

