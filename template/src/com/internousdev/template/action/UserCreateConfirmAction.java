package com.internousdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserCreateConfirmAction extends ActionSupport implements SessionAware{

	private String loginUserId;
	private String loginPassword;
	private String userName;

	private Map<String , Object> session;

	private String errorMessage;

	public String execute() {
		String result = ERROR;

		// 入力値が空白の場合
		if(loginUserId.equals("") && loginPassword.equals("") && userName.equals("")) {
			System.out.println("いずれかの項目が空白");
			setErrorMessage("未入力の項目があります。");
			return result;
		}

		session.put("loginUserId",loginUserId);
		session.put("loginPassword",loginPassword);
		session.put("userName",userName);

		System.out.println(this.session.get("userName"));

		result = SUCCESS;

		return result;
	}


	public String getLoginUserId() {
		return loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public String getUserName() {
		return userName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setLoginUseId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public void setSession(Map<String , Object> session){
		this.session = session;
	}

}
