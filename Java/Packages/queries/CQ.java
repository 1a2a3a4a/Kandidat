import java.sql.*;

import java.util.Scanner;

public class CQ {
	private  Connection con = null;
	//private  Scanner scanner = new Scanner(System.in);


	public void connect(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://130.238.247.54:3307/cq", "root", "dinmormor");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//scanner.close();
	}

	public void sendQuestionToDB(String Sentence, String Answer, String Alt1, String Alt2, String Alt3){
		String command = ("INSERT INTO `Question` (Sentence, Answer, Alt1, Alt2, Alt3) " + "VALUES (?, ?, ?, ?, ?)");
		try {
			PreparedStatement add = con.prepareStatement(command);
			add.setString(1, Sentence);
			add.setString(2, Answer);
			add.setString(3, Alt1);
			add.setString(4, Alt2);
			add.setString(5, Alt3);
			add.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public  void addUser(String Name, String Password) {

		String command = ("INSERT INTO `User` (Name, Password)" + " VALUES (?, ?)");
		try {
			PreparedStatement add = con.prepareStatement(command);
			add.setString(1, Name);
			add.setString(2, Password);
			add.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	private  void RemoveUser() {


	}

	//User1 should always be the one adding the request yo user2
	public  void sendFR(int User1_ID, int User2_ID) {

		int U1 = User1_ID;
		int U2 = User2_ID;
		if(!(User1_ID < User2_ID)){
			U1 = User2_ID;
			U2 = User1_ID;
		}
		
		String command = ("INSERT INTO `Relations` (User1_ID, User2_ID, Sent_by) VALUES (?, ?, ?)");
		try {
			PreparedStatement send = con.prepareStatement(command);
			send.setString(1, "" + U1);
			send.setString(2, "" + U2);
			send.setString(3, "" + User1_ID);
			send.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//User1 accepts user2 friend req
	public  void acceptFR(int User1_ID, int User2_ID) {
		int U1 = User1_ID;
		int U2 = User2_ID;
		if(!(User1_ID < User2_ID)){
			U1 = User2_ID;
			U2 = User1_ID;
		}
		//UpdateSQL("Update `Relations` SET Rel_status = 1, Sent_by = " +  User1_ID + " WHERE User1_ID = " + U1 + " AND User2_ID = " + U2);
		String command = ("Update `Relations` SET Rel_status = 1, Sent_by = ? WHERE User1_ID = ? AND User2_ID = ? AND Sent_by = ?");
		try {
			PreparedStatement send = con.prepareStatement(command);
			send.setString(1, "" + User2_ID);
			send.setString(2, "" + U1);
			send.setString(3, "" + U2);
			send.setString(4, "" + User2_ID);
			send.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private  ResultSet fetchFriendList(int User_ID) {
		//return QuerySQL("SELECT U.USer_ID, U.Name as Friend FROM User U WHERE U.User_ID  IN (SELECT User2_ID as User_ID FROM Relations WHERE User1_ID = " + User_ID
		//		+ " AND Rel_status = 1) OR U.User_ID  IN (SELECT User1_ID as User_ID FROM Relations  WHERE User2_ID = " + User_ID + " AND Rel_status = 1)");
		String command = ("SELECT U.USer_ID, U.Name as Friend FROM User U WHERE U.User_ID  IN (SELECT User2_ID as User_ID FROM Relations WHERE User1_ID = ?"
				+ " AND Rel_status = 1) OR U.User_ID  IN (SELECT User1_ID as User_ID FROM Relations  WHERE User2_ID = ? AND Rel_status = 1)");
		try {
			PreparedStatement send = con.prepareStatement(command);
			send.setString(1, "" + User_ID);
			send.setString(2, "" + User_ID);
			return send.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private  ResultSet fetchPendingFriendRequests(int User_ID) {
		//return QuerySQL("SELECT U.User_ID, U.Name as Friend FROM User U WHERE U.User_ID  IN (SELECT User2_ID as User_ID FROM Relations WHERE User1_ID = " + User_ID
		//		+ " AND Rel_status = 0 AND Sent_by != " + User_ID + ") OR U.User_ID  IN (SELECT User1_ID as User_ID FROM Relations  WHERE User2_ID = " + User_ID + " AND Rel_status = 0 AND Sent_by != "+ User_ID + ")");
		String command = ("SELECT U.USer_ID, U.Name as Friend FROM User U WHERE U.User_ID  IN (SELECT User2_ID as User_ID FROM Relations WHERE User1_ID = ?"
				+ " AND Rel_status = 0 AND Sent_by != " + User_ID + ") OR U.User_ID  IN (SELECT User1_ID as User_ID FROM Relations  WHERE User2_ID = ? AND Rel_status = 0 AND Sent_by != ?)");
		try {
			PreparedStatement send = con.prepareStatement(command);
			send.setString(1, "" + User_ID);
			send.setString(2, "" + User_ID);
			send.setString(3, "" + User_ID);
			return send.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	/*private  void PrintFL(ResultSet friend_list) {
		try {
			while(friend_list.next()){
				System.out.println(friend_list.getInt(1) + " " + friend_list.getString(2));
				
				//rs.getInt(2);
				
			}
			System.out.println("End");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
	}*/




}
