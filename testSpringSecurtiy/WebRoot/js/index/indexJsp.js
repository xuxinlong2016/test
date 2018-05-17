
  //判断浏览器类别
	var Sys = {}; 
	var ua = navigator.userAgent.toLowerCase(); 
	var s; 
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
	(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
	(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
	(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0; 
	


//隐藏或显示侧边栏
function sidebarHideOrDisplay(){
	var sid = document.getElementById('sideBar');
	var con = document.getElementById('content');
	var img = document.getElementById('hideBarImg');
	
	sid.style.display = sid.style.display == ''?'none':'';
  con.style.width = sid.style.display ==''?'77.4%':'99.5%';
	img.src = sid.style.display==''?'${pageContext.request.contextPath}/img/mainpage/left.gif':'${pageContext.request.contextPath}/img/mainpage/right.gif';
	
}
//隐藏或显示logo
function logoHideOrDisplay(){
	var header = document.getElementById('header');
	header.style.display = header.style.display==''?'none':'';
	logoscroll.src = header.style.display==''?'${pageContext.request.contextPath}/img/mainpage/up.png':'${pageContext.request.contextPath}/img/mainpage/down.png';
	
}

//加载所有节点树
function loadmainframe(pageName,pageName2){
	window.parent.window.document.getElementById('sideFrame').src = pageName;
	window.parent.window.document.getElementById('workArea').src = pageName2;
}

//将页面显示在工作区
function loadworkareaframe(pageName){
	window.parent.window.document.getElementById('workArea').src = pageName;
}

//初始化iframs的高度(默认充满屏幕)
function initFrames(){
    //增加单位“px”，可兼容FireFox。
		document.getElementById('workArea').style.height = document.documentElement.clientHeight+'px';
		document.getElementById('sideFrame').style.height = document.documentElement.clientHeight+'px';
}


function gourl(id){
	document.getElementById('sideFrame').src = id;
}

