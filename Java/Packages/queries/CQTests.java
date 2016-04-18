import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
//import java.sql.ResultSet;
//import java.sql.SQLException;

import java.io.InputStream;
//import org.omg.CORBA.portable.OutputStream;

public class CQTests {
	public static void main(String[] args){
		String operationURL = "http://130.238.250.231/getquestionsfromdb.php";
		
		
		try {
			URL url = new URL(operationURL);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");

			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
			//StringBuilder sb = new StringBuilder();
			String result = "";
			String line = "";
			
			while((line = bufferedReader.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
			bufferedReader.close();
			inputStream.close();
			httpURLConnection.disconnect();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
/*
	private static void printQuestions(ResultSet rs) {
		try {
			System.out.println("Q_ID\t|Sentence\t\t|Answer\t|Alt1\t|Alt2\t|Alt3");
			while(rs.next()){
				System.out.println(rs.getString(1) + "\t|" + rs.getString(2)+ "\t|" + rs.getString(3)+ "\t|" + rs.getString(4)+ "\t|" + rs.getString(5)+ "\t|" + rs.getString(6));

				//rs.getInt(2);

			}
			System.out.println("End");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
*/