package databaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class JdbcConnectionData {

	public static void main(String[] args) throws SQLException, InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Java\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();   //launch chrome
		driver.get("https://www.yahoo.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//step 1
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orclpdb","pdbadmin","alam1234");
		//step 2.
		Statement st=con.createStatement();
		//step 3.
		String s="select USER_NAME,Password from YAHOOLOGING";
		//step 4
		ResultSet rs=st.executeQuery(s);
		while(rs.next()) {
			String USER_NAME=rs.getString("USER_NAME");
			String Password=rs.getString("Password");
			
			driver.findElement(By.xpath("//a[@id='uh-signin']")).click();
			driver.findElement(By.xpath("//input[@id='login-username']")).clear();
			driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys(USER_NAME);
			Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@id='login-signin']")).click();
			driver.findElement(By.xpath("//input[@id='login-passwd']")).clear();
			driver.findElement(By.xpath("//input[@id='login-passwd']")).sendKeys(Password);
			driver.findElement(By.xpath("//button[@id='login-signin']")).click();
//			String str=driver.getTitle();
//			System.out.println(str);
			

			if(driver.getTitle().equals("Yahoo")) {
				System.out.println("test case passed");
			}
			else {
				System.out.println("test case failed");	
			}
			System.out.println(USER_NAME+"");
			System.out.println(Password);
		}
		//step 5
		con.close();

		System.out.println("program is Executed");

		driver.close();
	}
}
