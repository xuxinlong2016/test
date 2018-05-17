/*
用于open一个窗口
*/

function changepara(){
		window.close();
		if ( !window.opener.closed && window.opener ) {
			window.opener.location.reload();
		}
    }

var newWindow = null;
function windowopen1(loadpos){
   if (! newWindow|| newWindow.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=0,width=680,height=500,top=100,left=200";
    newWindow = window.open(loadpos,"contentdetails",strwindow);
  }else{
    newWindow.focus();
  }
}
var newWindow2 = null;
function windowopen2(loadpos){
   if (! newWindow2 || newWindow2.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=0,width=1280,height=1024,top=0,left=0";
    newWindow2 = window.open(loadpos,"showinform",strwindow);
  }else{
    newWindow2.focus();
  }
}

var newWindow3 = null;
function windowopen3(loadpos){
   if (! newWindow3 || newWindow3.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0,width=450,height=500,top=150,left=240";
    newWindow3 = window.open(loadpos,"modifyinform",strwindow);
  }else{
    newWindow3.focus();
  }
}

var newWindow4 = null;
function windowopen4(loadpos){
   if (! newWindow4 || newWindow4.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,scrollbars=yes,resizable=0,width=700,height=500,top=150,left=240";
    newWindow4 = window.open(loadpos,"modifyinform",strwindow);
  }else{
    newWindow4.focus();
  }
}

var newWindow5 = null;
function windowopen5(loadpos,winname){
   if (! newWindow5 || newWindow5.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,scrollbars=yes,resizable=0,width=520,height=400,top=150,left=240";
    newWindow5 = window.open(loadpos,winname,strwindow);
  }else{
    newWindow5.focus();
  }
}
var newWindow6 = null;
function windowopen6(loadpos,winname){
   if (! newWindow6 || newWindow6.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,scrollbars=yes,resizable=0,width=760,height=450,top=150,left=60";
    newWindow6 = window.open(loadpos,winname,strwindow);
  }else{
    newWindow6.focus();
  }
}

var newWindow7= null;
function windowopen7(loadpos,winname){
   if (! newWindow7 || newWindow7.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,scrollbars=yes,resizable=0,width=600,height=600,top=10,left=10";
    newWindow7 = window.open(loadpos,winname,strwindow);
  }else{
    newWindow7.focus();
    newWindow7.location.reload();
  }
}
var newWindow8= null;
function windowopen8(loadpos,winname){
   if (! newWindow8 || newWindow8.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,scrollbars=yes,resizable=0,width=770,height=500,top=10,left=10";
    newWindow8 = window.open(loadpos,winname,strwindow);
  }else{
    newWindow8.focus();
    newWindow8.location.reload();
  }
} 
var newWindow9 = null;
function windowopen9(loadpos){
   if (! newWindow9 || newWindow9.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,scrollbars=yes,resizable=0,width=850,height=650,top=150,left=240";
    newWindow9 = window.open(loadpos,"modifyinform",strwindow);
  }else{
    newWindow9.focus();
  }
}
function windowopen10(loadpos){
   if (! newWindow9 || newWindow9.closed)
  {
    var strwindow="toolbar=0,location=0,directories=0,status=0,scrollbars=yes,resizable=0,width=1000,height=700,top=150,left=240";
    newWindow9 = window.open(loadpos,"modifyinform",strwindow);
  }else{
    newWindow9.focus();
  }
}
//--操作checkbox

