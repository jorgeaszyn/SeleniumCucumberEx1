Feature: Automation into Salesforce Application

Scenario: Initialize tests
Given Salesforce website is opens
When Enter username and password and log in
Then Navigate to Service app

Scenario: Validate service app tabs
Given The diferent Service app tabs 
Then Navigate through all tabs, click new and cancel

Scenario: Create account record
Given A new account is opened
When The database connects
Then Populate data
And Save the record

Scenario: Create account record with empty mandatory fields
Given A new account is opened again
When Mandatory fields are kept empty
Then Save record
And Verify error

Scenario: Create contact record on new tab
Given Contacts is opened in new tab
When Switch to Contacts tab
And Open new contact
And The database connects again
Then Populate last name
And Populate account name with priorly created account
Then Save new contact
And Go back to main tab

Scenario: Edit account record
Given Accounts is opened
When Open Edit account priorly created
Then Update two values
And Scroll down
Then Update another value
And Save update
Then Verify changes

Scenario: Edit Employee account record with long digit value
Given Accounts is opened again
When Open Edit account priorly created again
Then Edit Employee field with long digit value
And Save update again
Then Verify message error

Scenario Outline: Add multiple accounts
Given Each new account is opened
Then Populate and save data with "<accName>", "<accPhone>", "<accNumber>" and "<accWebsite>"


Examples:
	|accName        |accPhone    |accNumber     |accWebsite     |
	|3rdAccount			|3333333     |003           |account.com    |
	|4thAccount     |4444444     |004           |account.com    |
	|5thAccount     |5555555     |005           |account.com.uy | 

Scenario: Close browser	
Given Close all browsers	