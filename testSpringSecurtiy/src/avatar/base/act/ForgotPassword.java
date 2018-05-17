/*
 * @(#) ForgotPassword.java  2010-6-26 ����11:13:32
 *
 * Copyright 2010 by Sparta 
 */

package avatar.base.act;



import java.io.UnsupportedEncodingException;

import avatar.base.buz.SendMail;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 */
public class ForgotPassword extends ActionSupport {

	private String message;
	private String username;
	private String email;

	public String Send() {

		/*
		 * ���û�����Ϊ�ջ������ַ����Ϊ�գ����û�����Ϊ�գ�������û���ȡ����Ӧ�� �����ַ�����з��͡�
		 * ������д�����䣬���������ȡ���û����������з���(�û�������������û����������URL��)��
		 */


		/*
		 * ����
		 */
		if (sendEmail()) {
			message = "���������ָ���Ѿ����͵���ָ���������ַ������գ�";
		} else {
			message = "���������ַ���û�����Ч��";
			return INPUT;
		}

		return SUCCESS;
	}

	/*
	 * �����ʼ�����
	 */
	public boolean sendEmail() {
		try {
			SendMail cn = new SendMail();
			// ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
			cn.setAddress("ruddermass@163.com", "drgslxb@163.com", "Դ����");

			StringBuffer sb = new StringBuffer();
			sb.append("\n\r");
			sb.append("���ǽ��յ�һ������ruddermass�˺ŵ���Ҫ�����������������!");
			sb.append("\n\r");
			sb.append("������Ӧ�����󣬲�����Ҫ�ı��������룬������������ӣ�");
			sb.append("\n\r");
			sb
					.append("http://192.168.0.72:7001/webapp/ResetPassword.action?username='ruddermass'");
			sb.append("\n\r");
			sb.append("������ӻ����2Сʱ��ֱ�����������������룡");
			sb.append("\n\r");
			sb.append("��������Ҫ�������룬�����������˴�����ύ����������������£�����Ժ��Ը�email!");
			sb.append("\n\r");
			sb.append("- ʤ�ɳɱ���ϸ���������Ŷ�");

			cn.send(sb.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	// ���سɹ���ʧ�ܵ���Ϣ
	public String getMessage() {
		return message;
	}

	// �õ�ҳ�洫�����û���������
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
