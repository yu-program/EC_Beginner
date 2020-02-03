package sample;

public class Sample {

	public static void main(String[] args) {
		//javaアプリケーションで実行
		String str1 = "Hello World";
		String str2 ="Hello ";
//str2に追加　+=で。
		str2 += "World";
		//str2には　"Hello " + "World"となってるからfalse。

		System.out.println(str2);
		System.out.println(str1 == str2);
		System.out.println(str1);


	}
}
