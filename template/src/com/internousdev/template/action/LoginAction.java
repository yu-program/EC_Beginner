package com.internousdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.BuyItemDAO;
import com.internousdev.template.dao.LoginDAO;
import com.internousdev.template.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{

	private String loginUserId;
	private String loginPassword;
	private String result;
	private Map<String , Object> session;

	public String execute() {
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = new LoginDTO();
		BuyItemDAO buyItemDAO = new BuyItemDAO();

		result = ERROR;
		//getLoginUserInfoを呼び出す。
		loginDTO = loginDAO.getLoginUserInfo(loginUserId,loginPassword);
		//sessionに格納
		//Mapのkey(="loginUser")は次sessionを使うときのkeyになるので、任意の名前で良い。
		session.put("loginUser", loginDTO);

	}


}
