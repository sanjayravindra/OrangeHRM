package com.stories;

import java.io.IOException;
import org.jbehave.core.annotations.*;
import com.manage.Employee;

public class Stepdef extends Employee
{
	
	
	@Given("user should navigate to login page")
	
	public void givenUserShouldNavigateToLoginPage() throws IOException 
	{
		super.report();
		super.navigate();
	}

	@When("user enters valid credentials")

	public void whenUserEntersValidCredentials() throws IOException 
	{
		super.credentials();
	}

	@Then("user should redirect to dashboard")

	public void thenUserShouldRedirectToDashboard() throws IOException 
	{
		super.login();
		
	}

	@Given("user should navigate to add employee page")

	public void givenUserShouldNavigateToAddEmployeePage() throws InterruptedException 
	{
		super.ptest("SC_02");
		super.pim();
	}

	@Given("user should able to add an employee")

	public void givenUserShouldAbleToAddAnEmployee() throws Throwable 
	{

		super.addemployee("Sheet1","SC_02","TC_01");
		
	}

	@Given("user should able to add employee without middle name")

	public void givenUserShouldAbleToAddEmployeeWithoutMiddleName() throws Throwable 
	{
		super.addemployee("Sheet1","SC_02","TC_02");
	}

	@Given("user should able to add employee without image")

	public void givenUserShouldAbleToAddEmployeeWithoutImage() throws Throwable 
	{
		super.addemployee("Sheet1","SC_02","TC_03");
	}

	@Then("user should able to add employee without creating login details")

	public void thenUserShouldAbleToAddEmployeeWithoutCreatingLoginDetails() throws Throwable 
	{
		super.addemployee("Sheet1","SC_02","TC_04");
	}


	@Given("user should navigate to personal details page")

	public void givenUserShouldNavigateToPersonalDetailsPage() throws Throwable 
	{
		super.ptest("SC_03");	
		super.pim();
	}

	@Then("user should able to add personal details of an employee")

	public void thenUserShouldAbleToAddPersonalDetailsOfAnEmployee() throws Throwable 
	{
		super.addemployee("Sheet1","SC_03","TC_01");
	}



	@Given("user should navigate to employee information page")

	public void givenUserShouldNavigateToEmployeeInformationPage() 
	{
		super.ptest("SC_04");
		super.pim();
	}

	@Given("user should able to search an employee with valid employee name")

	public void givenUserShouldAbleToSearchAnEmployeeWithValidEmployeeName() throws Throwable 
	{
		super.search("Sheet1","SC_04","TC_01");
	}

	@Given("user should able to search an employee from suggestion list")

	public void givenUserShouldAbleToSearchAnEmployeeFromSuggestionList() throws Throwable
	{
		super.pim();
		super.search("Sheet1","SC_04","TC_02");
	}

	@Given("user should able to search an employee with partial name and valid employee id")

	public void givenUserShouldAbleToSearchAnEmployeeWithPartialNameAndValidEmployeeId() throws Throwable
	{
		super.pim();
		super.search("Sheet1","SC_04","TC_03");
	}

	@Given("user should able to search an employee with valid employee id")

	public void givenUserShouldAbleToSearchAnEmployeeWithValidEmployeeId() throws Throwable
	{
		super.pim();
		super.search("Sheet1","SC_04","TC_04");
	}

	@Then("user should able to search an employee with valid employee id and name")

	public void thenUserShouldAbleToSearchAnEmployeeWithValidEmployeeIdAndName() throws Throwable
	{
		super.pim();
		super.search("Sheet1","SC_04","TC_05");
	}
	@Given("user should able to logout\nthen user should able close the broswer")

	public void givenUserShouldAbleToLogoutthenUserShouldAbleCloseTheBroswer() 
	{
		super.ptest("SC_05");
		super.logout("TC_01");		
			
	}
}
