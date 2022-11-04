#Author: Divya Bharathi A
@tag
Feature: Validate React JS webpage
  To validate the elements, subelements and links in the react js webpage

  @Assignment1
  Scenario: To navigate to React JS site and validate the DOCS tab.
    Given User is on Home page of the React JS webpage.
    When User clicks on Docs tab
    Then Verify that sub elements and links are displayed and save in file

  @Assignment2
  Scenario: To Verify Scrolling Functionality in Tutorial Tab
   	Given User is on Home page of the React JS webpage.
   	When User clicks on Tutorial tab
   	Then Verify the scrolling functionality in tutorials tab
   	
  @APIAssignment
  Scenario: To fetch the API and modify the responses 
  	When User send an API request
  	Then User get the count of the categories and prints the details
  	And Print the name of Food category and geo location
 