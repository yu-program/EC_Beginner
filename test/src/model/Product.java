package model;

public class Product {
//・属性（フィールド、変数のこと）と振る舞い（メソッド）を記述できる。ex,class名はproduct。 (変数:name,price)
//DTOに近い。属性（フィールドの定義）
//<何を持っていて何をできるのか>
	private String name;
	private int price;

//振る舞い。メソッド。setterは自クラスにsetする。
	public void setName(String name) {
		this.name = name;
	}
//getterは他クラスから取得できるように定義する。
	public String getName() {
		return name;
	}
	public void setPrice(int price) {
		this.price = price;}
//getterは他クラスから取得できるように定義する。
	public int getPrice() {
		return price;
	}
}
