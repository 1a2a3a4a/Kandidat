import java.sql.*;

import java.util.Scanner;

public class cq {
	private static Connection con = null;
	private static Scanner scanner = new Scanner(System.in);

	private static String getString(String message){
		//open();
		String string = "";
		System.out.println(message);
		string = scanner.nextLine();
		//close();
		return string;
	}

	private static void Connect(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://130.238.247.54:3307/cq", "root", "dinmormor");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void Disconnect(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scanner.close();
	}

	private static ResultSet QuerySQL(String command){
		ResultSet rs = null;
		try {
			Statement get = con.createStatement();
			rs = get.executeQuery(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	private static void UpdateSQL(String command){
		try {
			Statement add = con.createStatement();
			add.executeUpdate(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void AddUser() {
		UpdateSQL("INSERT INTO `User` (Name, Password)" + "VALUES ('Kalle', 'PWD123')");
		UpdateSQL("INSERT INTO `User` (Name, Password)" + "VALUES ('Peter', 'PWD123')");
		
		UpdateSQL("INSERT INTO `User` (Name, Password)" + "VALUES ('Kalisa', 'PWD123')");
		UpdateSQL("INSERT INTO `User` (Name, Password)" + "VALUES ('Petra', 'PWD123')");

		UpdateSQL("INSERT INTO `User` (Name, Password)" + "VALUES ('Bengan', 'PWD123')");
		UpdateSQL("INSERT INTO `User` (Name, Password)" + "VALUES ('Birgitta', 'PWD123')");


	}


	private static void RemoveUser() {


	}

	private static void SendFR(int User1_ID, int User2_ID) {

		int U1 = User1_ID;
		int U2 = User2_ID;
		if(!(User1_ID < User2_ID)){
			U1 = User2_ID;
			U2 = User1_ID;
		}

		UpdateSQL("INSERT INTO `Relations` (User1_ID, User2_ID, Sent_by)" + "VALUES ("+ U1 +"," + U2 +"," + User1_ID + ")");

	}

	private static void AcceptFR(int User1_ID, int User2_ID) {
		int U1 = User1_ID;
		int U2 = User2_ID;
		if(!(User1_ID < User2_ID)){
			U1 = User2_ID;
			U2 = User1_ID;
		}
		UpdateSQL("Update `Relations` SET Rel_status = 1, Sent_by = " +  User1_ID + " WHERE User1_ID = " + U1 + " AND User2_ID = " + U2);

	}

	private static ResultSet FetchFriendList(int User_ID) {
		return QuerySQL("SELECT U.USer_ID, U.Name as Friend FROM User U WHERE U.User_ID  IN (SELECT User2_ID as User_ID FROM Relations WHERE User1_ID = " + User_ID
				+ " AND Rel_status = 1) OR U.User_ID  IN (SELECT User1_ID as User_ID FROM Relations  WHERE User2_ID = " + User_ID + " AND Rel_status = 1)");
	}
	
	private static ResultSet FetchPendingFriendRequests(int User_ID) {
		return QuerySQL("SELECT U.User_ID, U.Name as Friend FROM User U WHERE U.User_ID  IN (SELECT User2_ID as User_ID FROM Relations WHERE User1_ID = " + User_ID
				+ " AND Rel_status = 0 AND Sent_by != " + User_ID + ") OR U.User_ID  IN (SELECT User1_ID as User_ID FROM Relations  WHERE User2_ID = " + User_ID + " AND Rel_status = 0 AND Sent_by != "+ User_ID + ")");
	}

	public static void main(String[] args){
		Connect();


		AddUser();
		//SendFR(1 , 4);
		//SendFR(2 , 4);
		//AcceptFR(1, 4);
		//AcceptFR(4, 2);
		//ResultSet friend_list = FetchPendingFriendRequests(4);
		
		//PrintFL(friend_list);
		//AcceptFR(4, 1);
		//PrintFL(FetchPendingFriendRequests(4));
		//PrintFL(friend_list);
		//RemoveUser();



		Disconnect();

	}

	private static void PrintFL(ResultSet friend_list) {
		try {
			while(friend_list.next()){
				System.out.println(friend_list.getInt(1) + " " + friend_list.getString(2));
				
				//rs.getInt(2);
				
			}
			System.out.println("End");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
	}




}
