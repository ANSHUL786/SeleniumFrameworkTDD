@tag
Feature: Check Submit a order 
	Want to check whether user able to submit an order


 Background:
	Given Go to ecommerce website landing page 
	
  @HappyPath
  Scenario Outline: Submit order check
    Given user login to application with email <email> and pass <password>
    When select an product with <productName> and add to cart
    And go to cart to proceed further with product <productName>
    And proceed to checkout and fill all details with country <country>
    Then then "THANKYOU FOR THE ORDER." message will be displayed

    Examples: 
      | email  						 | password 	| productName  			| country						|
      | test@anshul.mtp		 | 987654@Mtp |	ADIDAS ORIGINAL 	|	French Polynesia	|
     
