package com.internousdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.template.dto.LoginDTO;
import com.internousdev.template.util.DBConnector;

public class LoginDAO {

	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword) {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		LoginDTO loginDTO = new LoginDTO();

		String sql ="SELECT * FROM login_user_transaction where login_id =? AND login_pass =?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,loginUserId);
			ps.setString(2,loginPassword);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				loginDTO.setLoginId(rs.getString("login_id"));
				loginDTO.setLoginPassword(rs.getString("Login_Pass"));
				loginDTO.setUserName(rs.getString("User_name"));
			}
			if(rs.getString("login_id") != null){
					loginDTO.setLoginFlg(true);
			}

			}catch(SQLException e) {
				e.printStackTrace();
			}
			return loginDTO;
	}
}
