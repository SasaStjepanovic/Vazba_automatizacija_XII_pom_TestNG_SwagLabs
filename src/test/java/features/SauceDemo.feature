Feature: Login

  Scenario: Login with valid credentials

    When I enter username
    And I enter password
    And I click login button
    Then I should be logged in

  Scenario: Login with invalid credentials

    When I enter username "test username"
    And I enter password
    And I click login button
    Then I should see an error message "error message"

  Scenario: Buy product

    Given I am logged in
    And I click product

  @RunAllways
  Scenario Outline:

    When I enter username "<username>"
    And I enter password
    And I click login button
    Then I should see an error message "<errorMessage>"

    @Run
    Examples:
      | username     | errorMessage |
      | asdasd       | neki error   |
      | standarduser | d            |

    @RunSometimes
    Examples:
      | username      | errorMessage |
      | standard_user |              |
      | standard_user |              |
      | standard_user |              |