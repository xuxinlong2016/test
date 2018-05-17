<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<html><head>
<meta http-equiv="content-type" content="text/html;charset=gbk">
<title></title>

<style>
body,td,.p1,.p2,.i{font-family:arial}
body{margin:6px 0 0 0;background-color:#fff;color:#000;position:relative}
input{padding-top:0;padding-bottom:0;-moz-box-sizing:border-box;
	-webkit-box-sizing:border-box;box-sizing:border-box;}
table{border:0}
TD{FONT-SIZE:9pt;LINE-HEIGHT:18px;}
em{font-style:normal;color:#c60a00}
a em{text-decoration:underline;}
.f14{FONT-SIZE:14px}
.f10{font-size:10.5pt}
.f16{font-size:16px;font-family:Arial}
.c{color:#7777CC;}
.p1{LINE-HEIGHT:120%;margin-left:-12pt}
.p2{width:100%;LINE-HEIGHT:120%;margin-left:-12pt}
.i{font-size:16px;line-height:1.4em;height:28px;*height:24px}
.t{COLOR:#0000cc;TEXT-DECORATION:none}
a.t:hover{TEXT-DECORATION:underline}
.p{padding-left:18px;margin:0 0 20px 0;font-size:14px;word-spacing:4px;}
.f{line-height:115%;*line-height:120%;font-size:100%;width:33.7em;padding-left:15px;word-break:break-all;
	word-wrap:break-word;}
.h{margin-left:8px;width:100%}
.s{width:8%;padding-left:10px; height:25px;}
.m,a.m:link{COLOR:#666;font-size:100%;}
a.m:visited{COLOR:#660066;}
.g{color:#008000; font-size:12px;}
.r{ word-break:break-all;cursor:pointer;width:238px;}
.bi {background-color:#D9E1F7;height:20px;margin-bottom:12px}
.pl{padding-left:3px;height:8px;padding-right:2px;font-size:14px;}
.Tit{height:21px; font-size:14px;}
.Tit a{color:#0000cc}
.fB{ font-weight:bold;}
.mo,a.mo:link,a.mo:visited{COLOR:#666666;font-size:100%;line-height:10px;}
.htb{margin-bottom:5px;}
#ft{clear:both;line-height:20px;background:#e6e6e6;text-align:center}
#ft,#ft *{color:#77C;font-size:12px;font-family:Arial}
#ft span{color:#666}
form{margin:0;position:relative;z-index:9}
.jc a{color:#c60a00}
.btn{font-size:14px;height:2em;*padding-top:2px;width:5.6em;margin-left:3px}
.i,.btn{vertical-align:middle;*vertical-align:baseline}
a font[size="3"] font, font[size="3"] a font { text-decoration: underline}

#navigation {
	margin-bottom: 0;
	margin-top: 0;
	padding:3px 0 3px 20px;
	font-size: 100%;
	font-weight: bold;
	color: #009720;  /* #009900; #77985c;*/
/*	background: #C7C7C7;*/
	position: relative;
	top: -4px;
	left: -4px;
	}
	
#nav {
	background: #c7c7c7;
	margin:0;
	padding: 5px 0 5px 5px;
	}

/* 导航栏的圆角 */
#navigation #r1 {
	height:1px;
	overflow:hidden;
	margin:0 5px;
	background:#C7C7C7;
}
#navigation #r2 {
	height:1px;
	border-left:2px solid #C7C7C7;
	border-right:2px solid #C7C7C7;
	margin:0 3px;
	background:#C7C7C7;
	overflow:hidden;
}
#navigation #r3 {
	height:1px;
	border-left:1px solid #C7C7C7;
	border-right:1px solid #C7C7C7;
	margin:0 2px;
	background:#C7C7C7;
	overflow:hidden;
}
#navigation #r4 {
	height:2px;
	border-left:1px solid #C7C7C7;
	border-right:1px solid #C7C7C7;
	margin:0 1px;
	background:#C7C7C7;
	overflow:hidden;
}


</style>

</head>

<body link="#0000cc">

<!-- 导航 -->
<div id="navigation">
	<div id="r11"></div>
	<div id="r2"></div>
	<div id="r3"></div>
	<div id="r4"></div>
    <p id="nav">首页面&nbsp;&gt; &nbsp;日历</p>
    <div id="r4"></div>
	<div id="r3"></div>
	<div id="r2"></div>
	<div id="r1"></div>
</div>

<table cellpadding="0" cellspacing="0" id="1"><tr><td>
<style>
	#cal{width:434px;border:1px solid #c3d9ff;font-size:12px;margin:8px 0 0 15px;}
	#cal #top{height:29px;line-height:29px;background:#e7eef8;color:#003784;padding-left:70px;}
	#cal #top select{font-size:12px;}
	#cal #top input{padding:0;}
	#cal ul#wk{margin:0;padding:0;height:25px;}
	#cal ul#wk li{float:left;width:60px;text-align:center;line-height:25px;list-style:none;}
	#cal ul#wk li b{font-weight:normal;color:#c60b02;}
	#cal #cm{clear:left;border-top:1px solid #ddd;border-bottom:1px dotted #ddd;position:relative;}
	#cal #cm .cell{position:absolute;width:42px;height:36px;text-align:center;margin:0 0 0 9px;}
	#cal #cm .cell .so{font:bold 16px arial;}
	#cal #bm{text-align:right;height:24px;line-height:24px;padding:0 13px 0 0;}
	#cal #bm a{color:7977ce;}
	#cal #fd{display:none;position:absolute;border:1px solid #dddddf;background:#feffcd;
		padding:10px;line-height:21px;width:150px;}
	#cal #fd b{font-weight:normal;color:#c60a00;}
</style>
<!--[if IE]>
<style>#cal #top{padding-top:4px;}#cal #top input{width:65px;}#cal #fd{width:170px;}</style>
<![endif]-->
<div id="cal">
	<div id="top">公元&nbsp;
		<select></select>&nbsp;年&nbsp;
		<select></select>&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;
		农历<span></span>年&nbsp;[&nbsp;<span></span>年&nbsp;]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="回到今天" title="点击后跳转回今天" style="padding:0px;font-size:12px;">
	</div>
	<ul id="wk"><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li><b>六</b></li><li><b>日</b></li></ul>
	<div id="cm"></div>
	<div id="bm"></div>
</div>
<script src="../js/calendar.js"></script></td></tr></table><br>

</body>



<script>
function h(obj,url){obj.style.behavior='url(#default#homepage)';obj.setHomePage(url);}
function G(id){return document.getElementById(id);}
var location,al_arr=[];
function ss(w){window.status=w;return true;}
function cs(){window.status='';}

function al_c(A){while(A.tagName!="TABLE")A=A.parentNode;return A.getAttribute("id")}
function al_c2(n,c){while(c--){while((n=n.parentNode).tagName!="TABLE");};return n.getAttribute("id");}
function c(q){var p = window.document.location.href, sQ = '', sV = '', mu='', img = window["BD_PS_C" + (new Date()).getTime()] = new Image();for (v in q) {switch (v) {case "title":sV = encodeURIComponent(q[v].replace(/<[^<>]+>/g, ""));break;case "url":sV = escape(q[v]);break;default:sV = q[v]}sQ += v + "=" + sV + "&"}if (("p2" in q)&&G(q["p1"]).getAttribute("mu") && q["fm"]!="pl") { mu= "&mu=" + escape(G(q["p1"]).getAttribute("mu"));}img.src = "http://s.baidu.com/w.gif?q=%C8%D5%C0%FA&" + sQ + "path=" + p + mu + "&cid=7&qid=c51fc690005ddf03&t="+new Date().getTime();return true;}

</script>

</html>