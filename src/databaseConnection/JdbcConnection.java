package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

	public static void main(String[] args) throws SQLException {
		
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orclpdb","pdbadmin","alam1234");
		//step 2.
		Statement st=con.createStatement();
		//step 3.
		String s="insert into YAHOOLOGING values(101, 'shahin786alam@yahoo.com','nushratjahan')";
		//String s="update CRMLOGING1 set CRMLOGING_ID='50'where Last_name='alam'";
		//String s="delete customer where CUSTOMER_ID=102";
		st.executeQuery(s);
		
		//step 4
		con.close();
		
		System.out.println("program is over");
	}

}
