package com.unip.tcc.scarblade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unip.tcc.scarblade.factory.HikariCP3ConnectionFactory;

public class FacesDAO {
	
	public List<String> selectFaces(String id) {
		
		
		try (Connection connection = HikariCP3ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<String> f = new ArrayList<String>();

			
			final String selectUser = "SELECT * FROM imagens where uuid_usuario = ?";
			try {
				pstmt = connection.prepareStatement(selectUser);
				pstmt.setInt(1, Integer.parseInt(id));
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					f.add(rs.getString("img1"));
					f.add(rs.getString("img2"));
					f.add(rs.getString("img3"));
					f.add(rs.getString("img4"));
					f.add(rs.getString("img5"));
					f.add(rs.getString("img6"));
					f.add(rs.getString("img7"));
					f.add(rs.getString("img8"));
					f.add(rs.getString("img9"));
					f.add(rs.getString("img10"));
					f.add(rs.getString("img11"));
					f.add(rs.getString("img12"));
					f.add(rs.getString("img13"));
					f.add(rs.getString("img14"));
					f.add(rs.getString("img15"));
				}
				
				return f;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally {
				if (connection != null) {
					connection.close();
					pstmt.close();
					rs.close();
				}
			}
	} catch (SQLException e1) {
		e1.printStackTrace();
		return null;
	}
}
	
}
