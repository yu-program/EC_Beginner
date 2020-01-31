package com.internousdev.template.dto;

public class BuyItemDTO {

	//テーブルのカラム名と同じ変数を用意"_"がカラム名になっているなら後者の頭文字を大文字にする。
	private int id;
	private String itemName;
	private String itemPrice;

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
