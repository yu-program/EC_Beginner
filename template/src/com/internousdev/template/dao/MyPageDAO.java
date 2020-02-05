package com.internousdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.template.dto.MyPageDTO;
import com.internousdev.template.util.DBConnector;

public class MyPageDAO {

	public MyPageDTO getMyPageUserInfo(String item_transaction_id,String user_master_id) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		MyPageDTO myPageDTO = new MyPageDTO();

		//iitは、item_info_transactionの略。テーブルを再定義している。
		//ubitはuser_item_transactionの略。テーブルを再定義している。
		String sql="SELECT iit.item_name,ubit.total_price,ubit.total_count,ubit.pay FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction iit ON ubit.item_transaction_id = iit.id WHERE ubit.item_transaction_id = ? AND ubit.user_master_id = ? ORDER BY ubit.insert_date DESC";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,item_transaction_id);
			ps.setString(2,user_master_id);
			ResultSet rs =ps.executeQuery();

			if(rs.next()) {
				myPageDTO.setItemName(rs.getString("item_name"));
				myPageDTO.setTotalPrice(rs.getString("total_price"));
				myPageDTO.setTotalCount(rs.getString("total_count"));
				myPageDTO.setPayment(rs.getString("pay"));
				System.out.println("MyPageDAOからですよ" + myPageDTO.getPayment());
			}
		}catch(Exception e) {
			e.printStackTrace();
			//finallyは例外処理が発生しないに関わらず実行するので、必ず実行したい処理を記述する。
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return myPageDTO;
	}
	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id) {
		DBConnector db = new DBConnector();
		Connection con= db.getConnection();

		String sql ="DELETE FROM user_buy_item_transaction WHERE item_transaction_id =? AND user_master_id=?";
		PreparedStatement ps;
		int result =0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, item_transaction_id);
			ps.setString(2,user_master_id);
			result= ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
