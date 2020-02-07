package com.internousdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.MyPageDAO;
import com.internousdev.template.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private String deleteFlg;
	private String result;

	public String execute() {
		MyPageDAO myPageDAO = new MyPageDAO();
		MyPageDTO myPageDTO = new MyPageDTO();

		//商品履歴を削除しない場合（購入履歴参照のみ）
		System.out.println("deleteFlgは" + deleteFlg);
		if(deleteFlg == null) {
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();
			//下記の値は取れている。
			//System.out.println("値は" + item_transaction_id);
			//System.out.println("値は" + user_master_id);

			myPageDTO = myPageDAO.getMyPageUserInfo(item_transaction_id,user_master_id);

			session.put("buyItem_name",myPageDTO.getItemName());
			session.put("total_price",myPageDTO.getTotalPrice());
			session.put("total_count",myPageDTO.getTotalCount());
			session.put("total_payment",myPageDTO.getPayment());
			//"message"のvalueの""は何かしらの文字列が入るということ。
			session.put("message","");

			//以下、値はとれている。
			System.out.println("messageは"+session.get("message"));
			System.out.println("buyItem_Nameは" + session.get("buyItem_name"));
			System.out.println("total_priceは" + session.get("total_price"));
			System.out.println("total_countは" + session.get("total_count"));
			System.out.println("total_paymentは" + session.get("total_payment"));
		//商品履歴を削除する場合
		}else if(deleteFlg.equals("1")) {
			delete();
		}
		result = SUCCESS;
		return result;
	}
	public void delete() {
		MyPageDAO myPageDAO = new MyPageDAO();

		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id,user_master_id);
		System.out.println("resは" + res);
		if(res > 0) {
			session.put("message","商品情報を正しく削除しました。");
		}else if(res == 0) {
			session.put("message","商品情報の削除に失敗しました。");
		}
		//sessionの"message"に文字列を格納できていると確認。
		System.out.println("messageには" + session.get("message")+ "を格納");
	}
	public Map<String,Object> getSession(){
		return session;
	}
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	@Override
	public void setSession (Map<String,Object> session) {
		this.session = session;
	}
}
