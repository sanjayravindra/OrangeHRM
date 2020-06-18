OrangeHRM Project
					 
Scenario:  Verify the user is able to login with valid credentials
Given user should navigate to login page
When user enters valid credentials
Then user should redirect to dashboard
					 
Scenario:  Verify the user is able to add an employee
Given user should navigate to add employee page  
And user should able to add an employee
And user should able to add employee without middle name
And user should able to add employee without image
Then user should able to add employee without creating login details

Scenario:  Verify the user is able to add personal details of an employee
Given user should navigate to personal details page
Then user should able to add personal details of an employee

Scenario:  Verify the user is able to search and edit an employee
Given user should navigate to employee information page
And user should able to search an employee with valid employee name
And user should able to search an employee from suggestion list
And user should able to search an employee with partial name and valid employee id
And user should able to search an employee with valid employee id
Then user should able to search an employee with valid employee id and name

Scenario: verify the user is able to logout
Given user should able to logout
then user should able close the broswer