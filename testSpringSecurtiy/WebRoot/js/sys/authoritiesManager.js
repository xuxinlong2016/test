
//ȥ���ִ������߿ո�
String.prototype.trim = function(){ 
   return this.replace(/(^\s*)|(\s*$)/g, "");
}

//У��Ȩ��������Ȩ�ޱ�ʶ��������Ƿ�Ϊ�գ���Ϊ���򷵻�false���������ύ����
function validate(){

	//��ֵ
	var authorityNameValue = document.getElementById("authorityName").value;
	var authorityDescValue = document.getElementById("authorityDesc").value;

	//������
	var authorityName = document.getElementById("authorityName");
	var authorityDesc = document.getElementById("authorityDesc");

	//��֤δͨ�����ý���
	if( authorityDescValue.trim() == ''){
		alert('�������ɫ����!');
		authorityDesc.focus();
		return false;
	}

	if( authorityNameValue.trim() == ''){
		alert('�������ɫ��ʶ!');
		authorityName.focus();
		return false;
	}
	
}

//����ϵͳģ��������б��ı�ʱ�����б��ύ��
function OnSubmit(){
	document.form1.action="findAuthority";
	document.form1.submit();
}
