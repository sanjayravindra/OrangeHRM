package com.manage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Employee extends Property
{
	
	static WebDriver driver;
	public static Logger log = Logger.getLogger(Employee.class);
	public static ExtentReports extent;
	public static ExtentHtmlReporter rep;
	public static ExtentTest parenttest;
	public static ExtentTest logger;
	DbValidation db = new DbValidation();
	
	
	public void report()
	{
	 rep = new ExtentHtmlReporter("C:\\Users\\sanjay.ravindra\\eclipse-workspace\\OrangeHRM_Project\\src\\test\\resources\\Reports\\Extent.html");
	 extent = new ExtentReports();
	extent.attachReporter(rep);
	}	
	
	public void ptest(String scen)
	{
		parenttest = extent.createTest("Scenario : "+scen);
	}
	
	public void navigate() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sanjay.ravindra\\eclipse-workspace\\OrangeHRM\\src\\main\\resources\\Driver\\chromedriver.exe"); 
		driver = new ChromeDriver(); 
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("browser launched");
		this.ptest("SC_01");
		logger =  parenttest.createNode("Test ID : TC_01");
		driver.get("http://127.0.0.1/orangehrm/symfony/web/index.php/auth/login");
		logger.log(Status.PASS, MarkupHelper.createLabel("Navigated to login page", ExtentColor.BLUE));
		log.info("Login page");
		super.screenshot(driver, "login Page");
		
	}
	
	public void credentials() throws IOException
	{
		driver.findElement(By.id("txtUsername")).sendKeys(super.getProperty("username"));
		
		driver.findElement(By.id("txtPassword")).sendKeys(super.decode(super.getProperty("password")));
		log.info("Credentials are entered");
		logger.log(Status.PASS, MarkupHelper.createLabel("Crendentials are entered", ExtentColor.BLUE));
	}
	public void login() throws IOException
	{
		driver.findElement(By.id("btnLogin")).click();
		try 
	      {
				driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]"));
				log.info("Login Successful");
				logger.log(Status.INFO, MarkupHelper.createLabel("Login successful", ExtentColor.GREY));
				logger.log(Status.PASS, MarkupHelper.createLabel("Navigated to Dashboard", ExtentColor.BLUE));
			super.screenshot(driver, "Dashboard");
	      }
			catch(NoSuchElementException no)
		  {
				log.info("invalid credentials");
				logger.log(Status.FAIL, MarkupHelper.createLabel("Invalid credentials", ExtentColor.RED));
			super.screenshot(driver, "login failed");
		  }
		
	}
	
	public void pim()
	{
		try
		{
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		log.info("navigated to personal information page");
		}
		catch(NoSuchElementException e)
		{
			log.fatal("Failed to navigate to personal information page");
		}
	}

	public void addemployee(String sheetname,String scen,String edit) throws Throwable
	{
		String mid ="middlename";
		String photo ="photo";
		String cred ="credentials";
		String ed="Yes";
Thread.sleep(1000);
	
		for(int i=1;i<=super.getrow(sheetname);i++)
		{
			if(scen.equalsIgnoreCase(super.getcelldata(sheetname, "SC_ID", i)) && edit.equalsIgnoreCase(super.getcelldata(sheetname, "TC_ID", i)))
			{	
				logger =  parenttest.createNode("Test ID : "+edit);
				
				logger.log(Status.PASS,MarkupHelper.createLabel("Employee information page is displayed", ExtentColor.BLUE));
				
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		Thread.sleep(1000);
		try
		{
		String addemp = driver.findElement(By.xpath("//h1[contains(text(),'Add Employee')]")).getText();
		log.info(addemp+" page");
		logger.log(Status.PASS,MarkupHelper.createLabel("Redirect to "+addemp+" page", ExtentColor.BLUE));
		super.screenshot(driver, addemp+" page");
		Thread.sleep(500);
		
		driver.findElement(By.id("firstName")).sendKeys(super.getcelldata(sheetname, "First Name", i));
		if(!mid.equalsIgnoreCase(super.getcelldata(sheetname, "Missing", i)))
		{
		driver.findElement(By.id("middleName")).sendKeys(super.getcelldata(sheetname, "Middle Name", i));
		}
		driver.findElement(By.id("lastName")).sendKeys(super.getcelldata(sheetname, "Last Name", i));
		Thread.sleep(1000);
		 WebElement emp_id = driver.findElement(By.id("employeeId"));
		emp_id.clear(); 
		emp_id.sendKeys(super.getcelldata(sheetname, "Employee ID", i));
		logger.log(Status.PASS, MarkupHelper.createLabel("Employee details are entered", ExtentColor.BLUE));
		Thread.sleep(1000);
		if(!photo.equalsIgnoreCase(super.getcelldata(sheetname, "Missing", i)))
		{
		driver.findElement(By.id("photofile")).sendKeys("C:\\Users\\sanjay.ravindra\\Pictures\\Employees\\Emp_"+i+".jpg");
		logger.log(Status.PASS, MarkupHelper.createLabel("Image is selected", ExtentColor.BLUE));
		}
		Thread.sleep(1000);
		WebElement cbox = driver.findElement(By.id("chkLogin"));
		if(cbox.isSelected())
		{
			cbox.click();
		}
		if(!cred.equalsIgnoreCase(super.getcelldata(sheetname, "Missing", i)))
		{	
		if(!cbox.isSelected())
		{
			cbox.click();
		}
		driver.findElement(By.id("user_name")).sendKeys(super.getcelldata(sheetname, "Username", i));
		driver.findElement(By.id("user_password")).sendKeys(super.getcelldata(sheetname, "Password", i));
		driver.findElement(By.id("re_password")).sendKeys(super.getcelldata(sheetname, "Confirm pass", i));
		logger.log(Status.PASS, MarkupHelper.createLabel("Credentials are entered", ExtentColor.BLUE));
		logger.log(Status.PASS,	 MarkupHelper.createLabel("Status is selected", ExtentColor.BLUE));
		}
		driver.findElement(By.id("btnSave")).click();
		
		try
		{
			driver.findElement(By.xpath("//div[@class='message warning fadable']"));
			
			log.fatal("failed employee already exists");
			logger.log(Status.FAIL, MarkupHelper.createLabel("Failed employee already exists", ExtentColor.RED));
			super.screenshot(driver, "Failed employee already exists- Employee"+i);
			super.writeExcel(sheetname, "Result", i, "Fail");
		}
		catch(NoSuchElementException exc)
		{
			log.info("Redirect to personal details page");
			
		}
		try
		{
			driver.findElement(By.xpath("//h1[contains(text(),'Personal Details')]"));
			logger.log(Status.PASS, MarkupHelper.createLabel("Redirect to personal details page", ExtentColor.BLUE));
			super.screenshot(driver, "Employee"+i);
			log.info("Employee added page");
			super.writeExcel(sheetname, "Result", i, "Pass");
			
		}

			catch(NoSuchElementException ex)
			{
				log.fatal("Adding Employee failed");
				logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to redirect to personal details page", ExtentColor.RED));
				super.screenshot(driver, "Personal Details page-Employee "+ i + " failed");
				super.writeExcel(sheetname, "Result", i, "Fail");
				
			}
		
		
			}
			catch(NoSuchElementException a)
			{
				log.fatal("Add Employee page failed");
				logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to redirect to add employee page", ExtentColor.RED));
				logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to enter employee details", ExtentColor.RED));
				logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to select image", ExtentColor.RED));
				logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to enter credentials", ExtentColor.RED));
				logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to select status", ExtentColor.RED));
				logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to add an employee", ExtentColor.RED));
				
				super.screenshot(driver, "Add Employee page-Employee "+ i + " failed");
				super.writeExcel(sheetname, "Result", i, "Fail");
			}
		if(ed.equalsIgnoreCase(super.getcelldata(sheetname, "Edit", i)))
		{
		
			this.editEmployee(sheetname,i);
			try
			{
			db.test(sheetname, i, super.getcelldata(sheetname, "Employee ID", i));
			log.info("Validation passed");
			logger.log(Status.PASS, MarkupHelper.createLabel("Database validation passed", ExtentColor.BLUE));
			super.writeExcel(sheetname, "Validation", i, "Pass");
			}
			catch(AssertionError ae)
			{
				log.fatal("Validation failed");
				logger.log(Status.FAIL, MarkupHelper.createLabel("Database validation failed", ExtentColor.RED));
				super.writeExcel(sheetname, "Validation", i, "Fail");
			}
		
		}
		else
		{
			try
			{
			db.test(sheetname, i, super.getcelldata(sheetname, "Employee ID", i));

			log.info("Validation passed");
			logger.log(Status.PASS, MarkupHelper.createLabel("Database validation passed", ExtentColor.BLUE));
			super.writeExcel(sheetname, "Validation", i, "Pass");
			}
			catch(AssertionError e)
			{
				String str = e.getMessage();
				log.fatal("Validation failed-"+str);
				logger.log(Status.FAIL, MarkupHelper.createLabel("Database validation failed", ExtentColor.RED));
				super.writeExcel(sheetname, "Validation", i, "Fail");
			}
		}
			}
	}
	}
	
	
	public void editEmployee(String sheetname, int i) throws Throwable
	{
		String gender="Male";
		driver.findElement(By.id("btnSave")).click();
		log.info("textbox are enable to edit");
		logger.log(Status.PASS, MarkupHelper.createLabel("Disable textbox are enabled to add or modify employee details", ExtentColor.BLUE));
		super.screenshot(driver, "Edit Page");

		if(gender.equalsIgnoreCase(super.getcelldata(sheetname, "Gender", i)))
		{
			 WebElement male = driver.findElement(By.id("personal_optGender_1"));			 
				String maleValue = male.getAttribute("value");
				male.click();
				super.writeExcel(sheetname, "Gender code", i, maleValue);
		}
		else
		{
			WebElement female = driver.findElement(By.id("personal_optGender_2"));
			String femaleValue = female.getAttribute("value");
			super.writeExcel(sheetname, "Gender code", i, femaleValue);
			female.click();
		}
		
		logger.log(Status.PASS, MarkupHelper.createLabel("Gender is selected", ExtentColor.BLUE));
		
		WebElement marital_status = driver.findElement(By.id("personal_cmbMarital"));
		marital_status.click();
		marital_status.sendKeys(super.getcelldata(sheetname, "Marital Status", i));
		logger.log(Status.PASS, MarkupHelper.createLabel("Marital Status is selected", ExtentColor.BLUE));
		
		Select drop = new Select (driver.findElement(By.id("personal_cmbNation")));
		drop.selectByVisibleText(super.getcelldata(sheetname, "Nationality", i));
		logger.log(Status.PASS, MarkupHelper.createLabel("Nationality is selected", ExtentColor.BLUE));
		String value = drop.getFirstSelectedOption().getAttribute("value");
		super.writeExcel(sheetname, "Nation code", i, value);
		
		WebElement dob = driver.findElement(By.id("personal_DOB"));
		dob.click();
		dob.clear();
		dob.sendKeys(super.getcelldata(sheetname, "Date of Birth", i));
		logger.log(Status.PASS, MarkupHelper.createLabel("Date of Birth is entered", ExtentColor.BLUE));

		driver.findElement(By.id("btnSave")).click();		
		try
		{
			driver.findElement(By.xpath("//div[@class='message success fadable']"));
			logger.log(Status.PASS, MarkupHelper.createLabel("Saved Successfully", ExtentColor.BLUE));
		super.screenshot(driver,"Employee"+i+"Saved");
		log.info("Saved Successfully");
		super.writeExcel(sheetname, "Result", i, "Pass");
		Thread.sleep(200);
		}
		catch(NoSuchElementException e)
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to add personal details", ExtentColor.RED));
			super.writeExcel(sheetname, "Result", i, "Fail");
			log.fatal("failed");
			super.screenshot(driver, "Failed to add personal Details- Employee"+i);
			
		}
		try 
		{
			driver.findElement(By.xpath("//span[contains(text(),'Should be a valid date in yyyy-mm-dd format')]"));
			logger.log(Status.INFO, MarkupHelper.createLabel("Invalid date format", ExtentColor.GREY));			
		}
		catch(NoSuchElementException e)
		{
			logger.log(Status.INFO, MarkupHelper.createLabel("Employee is added", ExtentColor.GREY));

		}
		
	}
	
	public void search(String sheetname,String scen,String ctest) throws Throwable
	{
		String eid = null;
		
		for(int i=1;i<=super.getrow(sheetname);i++)
		{
			if(scen.equalsIgnoreCase(super.getcelldata(sheetname, "SC_ID", i)) && ctest.equalsIgnoreCase(super.getcelldata(sheetname, "TC_ID", i)))
			{
				
				logger =  parenttest.createNode(ctest);
				
				try
				{
					
				driver.findElement(By.xpath("//a[@class='toggle tiptip']"));
				
				logger.log(Status.PASS, MarkupHelper.createLabel("Redirect to Employee information page", ExtentColor.BLUE));
				
				super.screenshot(driver, "Search page");
				log.info("Redirect to search page");
				
				Thread.sleep(1000);
				try 
				{
		WebElement empname = driver.findElement(By.id("empsearch_employee_name_empName"));
		empname.clear();
		empname.sendKeys(super.getcelldata(sheetname, "First Name", i)+" ",super.getcelldata(sheetname, "Middle Name", i)+" ",super.getcelldata(sheetname, "Last Name", i));
		Thread.sleep(1000);
		WebElement empid = driver.findElement(By.id("empsearch_id"));
		empid.clear();
		empid.sendKeys(super.getcelldata(sheetname, "Employee ID", i));
		
		logger.log(Status.PASS, MarkupHelper.createLabel("Employee details are entered", ExtentColor.BLUE));
		Thread.sleep(1000);
		driver.findElement(By.id("searchBtn")).click();
		Thread.sleep(1000);
		
		  eid = driver.findElement(By.partialLinkText("10")).getText();
		
		if(!super.getcelldata(sheetname, "First Name", i).isEmpty())
		{
		driver.findElement(By.xpath("//a[contains(text(),'"+super.getcelldata(sheetname, "First Name", i)+"')]")).click();
		}
		else if(super.getcelldata(sheetname, "First Name", i).isEmpty())
		{
			driver.findElement(By.xpath("//a[contains(text(),'"+super.getcelldata(sheetname, "Employee ID", i)+"')]")).click();
		}
		log.info("Employee found");
		logger.log(Status.PASS, MarkupHelper.createLabel("Employee found", ExtentColor.BLUE));
		super.writeExcel(sheetname, "Result", i, "Pass");
		Thread.sleep(1000);
		this.editEmployee(sheetname, i);
		Thread.sleep(500);
		
		try
		{
		db.test(sheetname, i, eid);
		log.info("Validation passed");
		logger.log(Status.PASS, MarkupHelper.createLabel("Database validation passed", ExtentColor.BLUE));
		super.writeExcel(sheetname, "Validation", i, "Pass");
		}
		catch(AssertionError e)
		{
			String str = e.getMessage();	
			log.fatal("Validation failed-"+str);
			logger.log(Status.FAIL, MarkupHelper.createLabel("Database validation failed", ExtentColor.RED));
			super.writeExcel(sheetname, "Validation", i, "Fail");
		}
	}
				catch(Exception exc)
				{
					log.fatal("No Record found");
					logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to search employee", ExtentColor.RED));
					logger.log(Status.INFO, MarkupHelper.createLabel("No Record found", ExtentColor.GREY));
					super.screenshot(driver, "No Records");
					super.writeExcel(sheetname, "Result", i, "Fail");
						
				}
		}
				catch(NoSuchElementException e)
				{
					logger.log(Status.FAIL, MarkupHelper.createLabel("Failed to redirect employee information page", ExtentColor.RED));
				}
		  }
	}
}
	
	public void logout(String ctest)
	{

		logger =  parenttest.createNode("Test ID : "+ctest);
		
		try
		{
		driver.findElement(By.id("welcome")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();	
		log.info("logout sucessful");
		logger.log(Status.PASS, MarkupHelper.createLabel("list is displayed", ExtentColor.BLUE));
		logger.log(Status.PASS, MarkupHelper.createLabel("Redirect to login page", ExtentColor.BLUE));
		logger.log(Status.INFO, MarkupHelper.createLabel("Logout successful", ExtentColor.GREY));
		extent.flush();
		driver.close();
		}
		catch(Exception e)
		{
			log.fatal("logout failed");
			logger.log(Status.FAIL, MarkupHelper.createLabel("Logout failed", ExtentColor.RED));
			extent.flush();
		}
	}
}
