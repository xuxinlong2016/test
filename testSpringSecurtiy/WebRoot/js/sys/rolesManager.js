
//去除字串的两边空格。
String.prototype.trim = function(){ 
   return this.replace(/(^\s*)|(\s*$)/g, "");
}

//校验角色描述和角色标识的输入框是否为空，若为空则返回false，不允许提交表单。
function validate(){

	//表单值
	var roleNameValue = document.getElementById("roleName").value;
	var roleDescValue = document.getElementById("roleDesc").value;

	//表单名称
	var roleName = document.getElementById("roleName");
	var roleDesc = document.getElementById("roleDesc");

	//验证未通过则获得焦点
	if( roleDescValue.trim() == ''){
		alert('请输入角色描述!');
		roleDesc.focus();
		return false;
	}

	if( roleNameValue.trim() == ''){
		alert('请输入角色标识!');
		roleName.focus();
		return false;
	}
	
}

//当子系统模块的下拉列表框改变时，进行表单提交。
function OnSubmit(){
	document.form1.action="findRole";
	document.form1.submit();
}
