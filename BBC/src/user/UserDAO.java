package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
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
	
	//SQL문 제대로확
	public int login(String userID, String userPassword ) {
		String SQL = "SELECT userPassword FROM userInformation WHERE userID = ?"; //
		System.out.println("트라이캐치문전");
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs =pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) 
					return 1; //로그인성공
				else 
					return 0; //비밀번호불일치
				}
				
			return -1;//id x
			
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		System.out.println("디비오류후");
		
		return -2;//DB error
	}

	public int join(User user) {
		String SQL = "INSERT INTO userInformation VALUES (?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,user.getUserID());
			pstmt.setString(2,user.getUserPassword());
			pstmt.setString(3,user.getUserName());
			pstmt.setString(4,user.getUserGender());
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //DB 오류
	}
	
	

}
