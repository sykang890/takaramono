package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {
	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO() {
		try {
		String dbURL = "jdbc:mysql://127.0.0.1:3306/userDB"; //디비설정할때 해당 데이타베이스를 지정해야대 친구야 제발!! 
		String dbID = "root";       			
		String dbPassword ="1234";
		Class.forName("com.mysql.jdbc.Driver"); 
		conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	public String getDate() { // 현재의 시간을가지고오는함수
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //데이타베이스 오류
	}
	public int getNext() { // 현재의 시간을가지고오는함수
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이타베이스 오류 
	}
	
	public int write(String bbsTitle,String UserID, String bbsContent) {
			String SQL = "Insert into userDB values(?,?,?,?,?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1,getNext());
				pstmt.setString(2,bbsTitle);
				pstmt.setInt(3,getNext());
				pstmt.setString(4,getDate());
				pstmt.setString(5,bbsContent);
				pstmt.setInt(6,1);
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1; //데이타베이스 오류
		}
	
	
}