
//ȥ���ִ������߿ո�
String.prototype.trim = function(){ 
   return this.replace(/(^\s*)|(\s*$)/g, "");
}

//У���ɫ�����ͽ�ɫ��ʶ��������Ƿ�Ϊ�գ���Ϊ���򷵻�false���������ύ����
function validate(){

	//��ֵ
	var roleNameValue = document.getElementById("roleName").value;
	var roleDescValue = document.getElementById("roleDesc").value;

	//������
	var roleName = document.getElementById("roleName");
	var roleDesc = document.getElementById("roleDesc");

	//��֤δͨ�����ý���
	if( roleDescValue.trim() == ''){
		alert('�������ɫ����!');
		roleDesc.focus();
		return false;
	}

	if( roleNameValue.trim() == ''){
		alert('�������ɫ��ʶ!');
		roleName.focus();
		return false;
	}
	
}

//����ϵͳģ��������б��ı�ʱ�����б��ύ��
function OnSubmit(){
	document.form1.action="findRole";
	document.form1.submit();
}
