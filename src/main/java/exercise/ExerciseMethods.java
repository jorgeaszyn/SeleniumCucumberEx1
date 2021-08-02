package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExerciseMethods extends Utilities{
	
	private WebDriver driver;
	
	Part1 p1 = new Part1();
	Part2 p2 = new Part2();
	Part3 p3 = new Part3();
	Part4 p4 = new Part4();
	Part5 p5 = new Part5();
	Part6 p6 = new Part6();
	Part7 p7 = new Part7();
	
	@BeforeTest
	public void Login() throws SQLException{
		driver = initialize();
	}
	
	//Navigate through all tabs, click "new" and "cancel"
	@Test
	public void ExcerciseP1() {
		p1.part1(driver);
	}
	
	//Create account record
	@Test
	public void ExcerciseP2(ITestContext context) throws SQLException {
		p2.part2(driver, context);
	}
	
	//Create account record with empty mandatory fields, and Assert to verify expected error 
	@Test
	public void ExcerciseP3() {
		p3.part3(driver);
	}
	
	//Create contact record on new tab, populate accountName with priorly created record 
	@Test(dependsOnMethods = "ExcerciseP2")
	public void ExcerciseP4(ITestContext context) throws SQLException {
		p4.part4(driver, context);
	}
	
	//Edit account record, save and verify
	@Test(dependsOnMethods = "ExcerciseP2")
	public void ExcerciseP5(ITestContext context) throws InterruptedException {
		p5.part5(driver, context);
	}
	
	//Edit Employee account record with long digit value, save and verify error
	@Test(dependsOnMethods = "ExcerciseP2")
	public void ExcerciseP6(ITestContext context) throws InterruptedException {
		p6.part6(driver, context);
	}
	
	//Add multiple accounts with DataProvider
	//dependsOnMethods is included here only to be run at the end
	@Test(dataProvider="getData", dependsOnMethods = "ExcerciseP6")
	public void ExcerciseP7(String accountValue, String phoneValue, String accNumbValue, String webSiteValue) throws SQLException{
		p7.part7(driver, accountValue, phoneValue, accNumbValue, webSiteValue);
	}
	
	@AfterTest
	public void teardown() {
		
		driver.quit();
	}
	
	@DataProvider
	public String[][] getData() throws SQLException {
		
		String host = "localhost";
		String port = "3306";		
		Connection con =  DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/excercise1b" , "root", "2153");
		Statement s = con.createStatement();
		ResultSet rs =  s.executeQuery("select * from account where accountName <> '2ndAccount'");

		int cols = 4;
		int rows = 3;
		String[][] data = new String[rows][cols];
		int row=0;
		
		while(rs.next()) {
			for(int j=0;j<cols;j++){
				data[row][0] = rs.getString("accountName");
				data[row][1] = rs.getString("phone");
				data[row][2] = rs.getString("accountNumber");
				data[row][3] = rs.getString("website");
			}
			row++;
		}	
		return data;
	}
	
}
