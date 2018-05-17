<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<%@ taglib prefix="s" uri="/struts-tags" %> 

  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  <html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      <!-- Copyright 2010 dongying soft LTD.  All rights reserved. -->
	  
	  <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
      
 
<style type="text/css" media="all">
  @import "${pageContext.request.contextPath}/css/frontpage/master.css?87655";
</style>
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontpage/layout-ie7.css?87655"/><![endif]-->
<!--[if IE 7]><script src="${pageContext.request.contextPath}/js/frontpage/ietweaks.js?87655" type="text/javascript"></script><![endif]-->
<link rel="stylesheet" type="text/css" media="all"
    href="${pageContext.request.contextPath}/css/frontpage/account.css?87655" />
<!--[if IE]><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontpage/account-ie.css?87655"/><![endif]-->
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/img/frontpage/favicon.ico"
    type="image/x-icon" />



      <meta name="pageLayoutName" content="web" />
<title>欢迎体验七巧板框架-密码重设</title>


      
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

       <a href=""><img src="${pageContext.request.contextPath}/img/frontpage/logo.png" alt="基于东软公司七巧板框架开发" border="0" /></a>

</div>
                 
                
                 
                
                <!-- navLinks -->
                
                  
                
                <!-- /navLinks -->
            
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
              <img src="${pageContext.request.contextPath}/img/frontpage/forgotpassword.gif" alt="欢迎体验胜采成本精细化管理平台" /> 
              <div id="banner_links" style='white-space: nowrap;'>
                <a class="icon arrow" href="/Registration.action">用户注册</a>
				 <a class="icon arrow" href="${pageContext.request.contextPath}/login.jsp">重新登录</a>
				 <a class="icon arrow" href="http://sighttp.qq.com/cgi-bin/check?sigkey=d121a5dbf5ac0a6f2c04cba040d1d748f7f4965c6f6d6ba4a14d57c4eb66b1d7"; target=_blank; onclick="var tempSrc='http://sighttp.qq.com/wpa.js?rantime='+Math.random()+'&sigkey=d121a5dbf5ac0a6f2c04cba040d1d748f7f4965c6f6d6ba4a14d57c4eb66b1d7';var oldscript=document.getElementById('testJs');var newscript=document.createElement('script');newscript.setAttribute('type','text/javascript'); newscript.setAttribute('id', 'testJs');newscript.setAttribute('src',tempSrc);if(oldscript == null){document.body.appendChild(newscript);}else{oldscript.parentNode.replaceChild(newscript, oldscript);}return false;">在线支持</a>
              </div>
          </div>
        
        
        


<div id="content">
    <div class="column" >
    <div class="colx2"> 

        <h2>重新设置密码</h2>
        <div><p>请输入新的密码及确认密码进行重新设置！</p>
        </div>    
    
    </div>
    </div>

    <div class="column">
    <div class="colx2">
        <div class="box">
        <div class="box2">
	
			<h2>请输入新密码</h2>
			 
			<!-- 显示修改密码请求已发送的信息 -->
    	    <s:if test='message != null && !message.equals("")'>
    	    	<img src="${pageContext.request.contextPath}/img/frontpage/images/icon_message.gif"/>
    	    	<s:property value ="message"/>
    	    </s:if>
			 
			<form name="resetPasswordForm" action="${pageContext.request.contextPath}/ResetPassword" method="POST">
                      
                      <input name="username" value="ruddermass" type="hidden" />
					  <!--
                      <input name="sig" value="84985170a6ef7009fef8f7589dd84dcd" type="hidden" />
                      <input name="expire" value="21292881" type="hidden" />
					  -->
                      
                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <th><label for="password" class="required">新的密码</label></th>
                            <td>
                                <input id="password" maxlength="64" name="password" class="text" type="password" />
                            </td>
                          </tr>
                          <tr>
                            <th><label for="confirmPassword" class="required">确认密码</label></th>
                            <td>
                                <input id="confirmPassword" maxlength="64" name="confirmPassword" class="text" type="password" />
                            </td>
                          </tr>
                          <tr>
                            <td colspan="2" style="text-align: right;"><br/>
                                
                                <input name="reset" value="Reset" type="submit" />
                            </td>
                          </tr>
                        </table>
              </form>
     </div>
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
    &copy; 2010, Sparta-紫杉
    </td>
    </tr></table>
	
	
    
    </div>
    <!--/footer_inner3--></div>
    <!--/footer_inner2--></div>
    <!--/footer_inner--></div>
    <!--/footer-->

      
      <!-- /FOOTER -->
  
  </div><!--/container-->
  
  <!-- URCHIN -->
  <!-- Google Analytics -->
<script type="text/javascript">
//var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
//document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
//var pageTracker = _gat._getTracker("UA-285778-5");
//pageTracker._initData();
//pageTracker._trackPageview();
</script>
<!-- End of Google Analytics -->
  <!-- /URCHIN -->
  

  
  </body>
  </html>

