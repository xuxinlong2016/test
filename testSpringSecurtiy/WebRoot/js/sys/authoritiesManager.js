
//去除字串的两边空格。
String.prototype.trim = function(){ 
   return this.replace(/(^\s*)|(\s*$)/g, "");
}

//校验权限描述和权限标识的输入框是否为空，若为空则返回false，不允许提交表单。
function validate(){

	//表单值
	var authorityNameValue = document.getElementById("authorityName").value;
	var authorityDescValue = document.getElementById("authorityDesc").value;

	//表单名称
	var authorityName = document.getElementById("authorityName");
	var authorityDesc = document.getElementById("authorityDesc");

	//验证未通过则获得焦点
	if( authorityDescValue.trim() == ''){
		alert('请输入角色描述!');
		authorityDesc.focus();
		return false;
	}

	if( authorityNameValue.trim() == ''){
		alert('请输入角色标识!');
		authorityName.focus();
		return false;
	}
	
}

//当子系统模块的下拉列表框改变时，进行表单提交。
function OnSubmit(){
	document.form1.action="findAuthority";
	document.form1.submit();
}
