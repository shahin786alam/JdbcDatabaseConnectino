package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JdbcConnactionDataDriven {

	public static void main(String[] args) throws SQLException, InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Java\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();   //launch chrome
		driver.get("https://www.crmpro.com");
		driver.manage().window().maximize();
//		//driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//step 1
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orclpdb","pdbadmin","alam1234");
		//step 2.
		Statement st=con.createStatement();
		//step 3.
		String s="select USER_NAME,Password from CRMLOGING1";
		//step 4
		ResultSet rs=st.executeQuery(s);
		while(rs.next()) {
			String USER_NAME=rs.getString("USER_NAME");
			String Password=rs.getString("Password");

			driver.findElement(By.name("username")).clear();
			Thread.sleep(2000);
			driver.findElement(By.name("username")).sendKeys(USER_NAME);
			driver.findElement(By.name("password")).clear();
			Thread.sleep(3000);
			driver.findElement(By.name("password")).sendKeys(Password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(5000); 


			if(driver.getTitle().equals("CRMPRO")) {
				System.out.println("test case passed");
			}
			else {
				System.out.println("test case failed");	
			}
			//driver.findElement(By.linkText("Home")).click();
			//System.out.print(USER_NAME+ "  ");
			//System.out.println(Password);
			//driver.navigate().back();
			driver.switchTo().frame("mainpanel");
			WebElement logout=driver.findElement(By.xpath("//a[@href='https://www.crmpro.com/index.cfm?logout=1']"));
			clickElementByJs(logout, driver);
		}

		//step 5
		con.close();

		System.out.println("program is Executed");

		driver.close();

	}
	public static void clickElementByJs(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();",element);
	}

}
