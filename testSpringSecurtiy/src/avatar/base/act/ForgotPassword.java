/*
 * @(#) ForgotPassword.java  2010-6-26 下午11:13:32
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
		 * 若用户名不为空或邮箱地址均不为空，或用户名不为空，则根据用户名取出相应的 邮箱地址来进行发送。
		 * 若仅填写了邮箱，则根据邮箱取出用户名，来进行发送(用户名是用来组成用户点击的链接URL的)。
		 */


		/*
		 * 发送
		 */
		if (sendEmail()) {
			message = "重设密码的指令已经发送到你指定的邮箱地址，请查收！";
		} else {
			message = "您的邮箱地址或用户名无效！";
			return INPUT;
		}

		return SUCCESS;
	}

	/*
	 * 发送邮件内容
	 */
	public boolean sendEmail() {
		try {
			SendMail cn = new SendMail();
			// 设置发件人地址、收件人地址和邮件标题
			cn.setAddress("ruddermass@163.com", "drgslxb@163.com", "源代码");

			StringBuffer sb = new StringBuffer();
			sb.append("\n\r");
			sb.append("我们接收到一个来自ruddermass账号的需要重新设置密码的请求!");
			sb.append("\n\r");
			sb.append("若你响应该请求，并且需要改变您的密码，请点击下面的链接：");
			sb.append("\n\r");
			sb
					.append("http://192.168.0.72:7001/webapp/ResetPassword.action?username='ruddermass'");
			sb.append("\n\r");
			sb.append("这个链接会持续2小时或直到重新设置您的密码！");
			sb.append("\n\r");
			sb.append("若您不需要更改密码，可能是其他人错误的提交了请求。在这种情况下，你可以忽略该email!");
			sb.append("\n\r");
			sb.append("- 胜采成本精细化管理开发团队");

			cn.send(sb.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	// 返回成功或失败的消息
	public String getMessage() {
		return message;
	}

	// 得到页面传来的用户名和邮箱
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
