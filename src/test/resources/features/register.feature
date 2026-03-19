Feature: Register new user

  #Background: Set up website
   # Given Opening the website using <browser> as browser

  Scenario Outline: Register user successfully
    Given I have <browser> as a browser
    Given I enter a date of birth <day> <month> <year>
    And Enter first name <first>
    And Enter last name <last>
    And Enter email <email> plus matching confirmation
    And Enter password <password> plus matching confirmation
    And I accept terms and conditions
    And I assure that I am an adult
    And I agree on Code of Ethics and Conduct
    When I submit the form
    Then I register successfully



    Examples:
      | browser | day | month | year | first  | last      | email             | password     |
      | chrome  | 12  | 12    | 2000 | Alina  | Deininger | aldei@gmail.com   | pannkakor123 |
      | edge    | 24  | 7     | 1987 | Martin | Svensson  | masve@outlook.com | hejsan78     |
      | firefox | 8   | 1     | 1996 | Elsa   | Möller    | elmo@yahoo.com    | frozen_5     |


  Scenario: Missing last name
    Given I have chrome as a browser
    Given I enter a date of birth 12 11 1952
    And Enter first name Alma
    And Enter email alma12@gmail.com plus matching confirmation
    And Enter password hellohello plus matching confirmation
    And I accept terms and conditions
    And I assure that I am an adult
    And I agree on Code of Ethics and Conduct
    When I submit the form
    Then I should receive error message "Last Name is required"


  Scenario: Password not matching
    Given I have firefox as a browser
    Given I enter a date of birth 18 3 1972
    And Enter first name Magnus
    And Enter last name Nordström
    And Enter email nord72@gmail.com plus matching confirmation
    And Add password hej
    And Enter wrong password confirmation hejsan
    And I accept terms and conditions
    And I assure that I am an adult
    And I agree on Code of Ethics and Conduct
    When I submit the form
    Then I should receive error message "Password did not match"


  Scenario: Terms and conditions not accepted
    Given I have edge as a browser
    Given I enter a date of birth 27 8 2003
    And Enter first name Sofie
    And Enter last name Salt
    And Enter email soff@outlook.com plus matching confirmation
    And Enter password sallad_789 plus matching confirmation
    And I assure that I am an adult
    And I agree on Code of Ethics and Conduct
    When I submit the form
    Then I should receive error message "You must confirm that you have read and accepted our Terms and Conditions"

