<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<%@ taglib prefix="s" uri="/struts-tags" %> 

  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  <html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <!-- Copyright 2010 dongying soft LTD.  All rights reserved. -->
    <title>欢迎体验七巧板框架</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<meta name="pageLayoutName" content="web" />
      
	<style type="text/css" media="all">
  		@import "${pageContext.request.contextPath}/css/frontpage/master.css?87655";
	</style>
	
	<!-- 显示错误信息 -->
	<style>
   		div.error {
    		width: 260px;
    		border: 2px solid green;
    		background-color: yellow;
    		text-align: left;
			background: url(/img/frontpage/images/icon_message.gif) no-repeat -24px 0px;
		}

		div.hide {
    		display: none;
		}
	
   </style>
   
    <!-- 适应各浏览器的css -->
	<!--[if IE 7]><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontpage/layout-ie7.css?87655"/><![endif]-->
	<!--[if IE 7]><script src="${pageContext.request.contextPath}/js/frontpage/ietweaks.js?87655" type="text/javascript"></script><![endif]-->
	<link rel="stylesheet" type="text/css" media="all"
    	href="${pageContext.request.contextPath}/css/frontpage/account.css?87655" />
	<!--[if IE]><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontpage/account-ie.css?87655"/><![endif]-->
	<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/img/frontpage/favicon.ico" type="image/x-icon" />
   
    <!-- 用户名及密码框闪动 -->
    <script src="${pageContext.request.contextPath}/js/shake/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shake/jquery-ui-personalized-1.6rc1.packed.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/shake/jquery.form.js"></script> 
	<script>
	var $j = jQuery.noConflict();
	
	$j(document).ready(function() {
	
		function processing() {
			document.getElementById('login_form').action = '${pageContext.request.contextPath}/j_spring_security_check';
			document.getElementById('login_form').submit();
		}
	
		function login(responseText) {
				$j(".login_form .field, .login_form .buttons, .login_form .text").effect("shake", { distance: 5 }, 100);
		}
	
		$j(".login_form").ajaxForm({
			beforeSubmit: processing,
			success: login
		});
		
	});

	</script>
      
  </head>
  
  <body class="account blue" >
  
  <div id="container">
      <!-- HEADER -->
      
        <div id="top" class="corner_box">
            <div class="corner bottom_left"></div>
            <div class="corner bottom_right"></div>
            <div id="top_inner">
                <div id="skip">
                  <a href="#content" title="skip navigation" accesskey="2">Skip to main content</a>
                </div>
 
			<div id="logo">
       			<a href=""><img src="${pageContext.request.contextPath}/img/frontpage/logo.png" 
       				alt="基于东软公司七巧板框架开发" border="0" /></a>
			</div>
           
            </div><!--/top_inner-->
         </div><!--/top-->
         <!-- END OF HEADER -->
         <hr />
      
      <!-- /HEADER -->
      
      <!-- CONTENT --> 
      <div id="col_wrap">
      <div id="col_wrap2">
      <div id="col_wrap3">
      <div id="col_wrap4">
        
          <div id="banner" >
              <img src="${pageContext.request.contextPath}/img/frontpage/bnr_welcome.gif" alt="欢迎体验七巧板框架" />
              <div id="banner_links" style='white-space: nowrap;'>
                <a class="icon arrow" href="/Registration.action">用户注册</a>

				 
				 <a class="icon arrow"  href="http://sighttp.qq.com/cgi-bin/check?sigkey=d121a5dbf5ac0a6f2c04cba040d1d748f7f4965c6f6d6ba4a14d57c4eb66b1d7"; target=_blank; onclick="var tempSrc='http://sighttp.qq.com/wpa.js?rantime='+Math.random()+'&sigkey=d121a5dbf5ac0a6f2c04cba040d1d748f7f4965c6f6d6ba4a14d57c4eb66b1d7';var oldscript=document.getElementById('testJs');var newscript=document.createElement('script');newscript.setAttribute('type','text/javascript'); newscript.setAttribute('id', 'testJs');newscript.setAttribute('src',tempSrc);if(oldscript == null){document.body.appendChild(newscript);}else{oldscript.parentNode.replaceChild(newscript, oldscript);}return false;">在线支持</a>
              </div>
          </div>
      

<div id="content">
    <div class="column" >
    <div class="colx2"> 

        <h2>七巧板框架</h2>
        <div class="col">
        	<p>Jdk1.6.0_13,
        	Struts2.1.8,
        	Spring3.0.1,
        	Hibernate3.3.1,
        	Security3.0.2,
        	Weblogic10.3,
        	oracle9i,
        	C3P0-0.9.1</p>
        <div style="height: 60px"></div>
        </div>
        <!--col-->
        <div class="col omega">
        <div class="indent spacious">
			<A class="icon download" href="">特性1</A><BR>
			<A class="icon download" href="">特性2</A><BR>
			<A class="icon download" href="">特性3</A><BR>
			<A class="icon download" href=""></A><BR>
			<A class="icon download" href=""></A><BR>
        </div>
		<A title="Clip to Evernote" onClick="window.alert('右键点击选择“添加到收藏夹”菜单……');return false;" 
										href="javascript:(function(){EN_CLIP_HOST='http://www.evernote.com';try{var%20x=document.createElement('SCRIPT');x.type='text/javascript';x.src=EN_CLIP_HOST+'/public/bookmarkClipper.js?'+(newDate().getTime()/100000);document.getElementsByTagName('head')[0].appendChild(x);}catch(e){location.href=EN_CLIP_HOST+'/clip.action?url='+encodeURIComponent(location.href)+'&amp;title='+encodeURIComponent(document.title);}})();"><IMG class=inline alt="添加到收藏夹" src="img/frontpage/clipper_bookmarklet.gif">
										</A>
</div>
    
    
    </div>
    </div>

    <div class="column">
    <div class="colx2">
        <div class="box">
        <div class="box2">
     
        <h2>平台登录</h2>
        
        <form class="login_form" id="login_form" name="login_form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
		    <!-- 显示登录失败信息 -->
			<div class="error ${param.error == true ? '' : 'hide'}">登录失败<br/>
				${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
    	    </div>
    	    <!-- 显示修改密码请求已发送的信息 -->
    	    <s:if test='message != null && !message.equals("")'>
    	    	<img src="${pageContext.request.contextPath}/img/frontpage/images/icon_message.gif"/>
    	    	<s:property value ="message"/>
    	    </s:if>

    	    
            <table width="100%" border="0" cellspacing="0" cellpadding="0" summary="Sign in form">
                <tr>
                    <th><label for="username" class="field">用户名称</label></th>
                    <td><input id="username" maxlength="64" style="width: 170px" name="j_username" 
					     class="text" type="text" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" />
						<script type="text/javascript">setTimeout(function(){try{var z=document.getElementById('j_username');z.focus();z.select();}catch(e){}},1);</script>
					</td>
                </tr>
                <tr>
                    <th><label for="password" class="field">用户密码</label></th>
                    <td><input id="password" maxlength="64" style="width: 170px" name="j_password" class="text" type="password" /></td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td><input id="remember" name="_spring_security_remember_me" value="true" type="checkbox" class="field" />
                    <label for="remember" class="field">两周之内记住我！</label></td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td align="right"><input name="login" value="登 录" type="submit" class="buttons" /></td>
                </tr>
            </table>           
 

        </form>
     </div>
    </div>
    
    
        <div class="box_footer_link">
            <a class="icon arrow" href="${pageContext.request.contextPath}/jsp/forgotpassword.jsp">忘记密码？</a>
        </div>
    
    </div>
    </div>
</div>

            
        
    
      </div> <!-- /col_wrap4 -->
      </div> <!-- /col_wrap3 -->
      </div> <!-- /col_wrap2 -->
      </div> <!-- /col_wrap -->
      <!-- /CONTENT -->
      
      <!-- FOOTER -->
      
        
    <div id="footer" class="corner_box">
      <div class="corner top_left"></div>
      <div class="corner top_right"></div>
      <div class="corner bottom_left"></div>
      <div class="corner bottom_right"></div>
    <div id="footer_inner">
    <div id="footer_inner2">
    <div id="footer_inner3">
    
    <table><tr>
    <td>
    	<A href="" target=_top>相关下载</A>  |  <A href="http://www.blogjava.net/SpartaYew">我的WEB</A>  |  
		<A href="mailto:SpartaYew@163.com" target=_top>联系我们</A>  |  <A href="http://webqq.qq.com" target="_blank">技术支持</A>
    </td>
    <td style="text-align: right;">
    &copy; 2010, sparta-紫杉
    </td>
    </tr></table>
	
    
    </div>
    <!--/footer_inner3--></div>
    <!--/footer_inner2--></div>
    <!--/footer_inner--></div>
    <!--/footer-->

      
      <!-- /FOOTER -->
  
  </div><!--/container-->
  
  </body>
  

  
  
  </html>

