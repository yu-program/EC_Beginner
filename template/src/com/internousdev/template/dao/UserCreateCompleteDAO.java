package com.internousdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.template.util.DBConnector;
import com.internousdev.template.util.DateUtil;

public class UserCreateCompleteDAO {

	public void createUser(String loginUseId,String loginUserPassword,String userName) {
		DateUtil dateUtil = new DateUtil();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "INSERT INTO login_user_transaction(login_Id,login_pass,user_name,insert_date)values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,loginUseId);
			ps.setString(2,loginUserPassword);
			ps.setString(3,userName);
			ps.setString(4,dateUtil.getDate());

			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
