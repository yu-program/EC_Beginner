package com.internousdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware{

	private int stock;
	private String pay;
	private Map<String,Object> session;
	private String result;

	public String execute() {
		result = SUCCESS;

		session.put("stock", stock);
		//Integer.toString(対象となる整数)　は整数を文字列に変換する。

		//Integer.parseInt(対象となる文字列)　は文字列を整数に変換する。
		//toString　は数値などを文字列に変換して、String型の文字列を返す。
		int intStock = Integer.parseInt(session.get("stock").toString());
		int intPrice = Integer.parseInt(session.get("buyItem_price").toString());
		//buyItem_priceというkeyでintStock * intPriceした値をvalueとする。
		session.put("buyItem_price",intStock * intPrice);
		System.out.println("BuyItemActionからです");
		System.out.println(session.get("buyItem_price"));

		String payment;
		if(pay.equals("1")) {
			payment ="現金払い";
			session.put("pay", payment);
		}else {
			payment ="クレジットカード";
			session.put("pay", payment);
		}
		return result;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public Map<String,Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
}
