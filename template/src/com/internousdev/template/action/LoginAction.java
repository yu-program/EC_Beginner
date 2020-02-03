package com.internousdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.BuyItemDAO;
import com.internousdev.template.dao.LoginDAO;
import com.internousdev.template.dto.BuyItemDTO;
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
		//loginId,loginPassword,userNameがloginDTOの中に入ってる
		loginDTO = loginDAO.getLoginUserInfo(loginUserId,loginPassword);
		//sessionに格納
		//Mapのkey(="loginUser")は次sessionを使うときのkeyになるので、任意の名前で良い。何の情報なのかわかるように。
		session.put("loginUser", loginDTO);

		//ログインが認証されたら、商品情報を表示。
		//sessionから取り出した値をLoginDTO型に変換。
		if(((LoginDTO)session.get("loginUser")).getLoginFlg()) {
			result =SUCCESS;
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			session.put("login_user_id",loginDTO.getLoginId());
			session.put("id",buyItemDTO.getId());
			session.put("buyItem_name",buyItemDTO.getItemName());
			session.put("buyItem_price",buyItemDTO.getItemPrice());
			System.out.println("LoginActionからですよ");
			System.out.println(session.get("buyItem_price"));

			return result;
		}
		return result;

	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Map<String,Object> getSession (){
		return session;
	}
	@Override
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
}
