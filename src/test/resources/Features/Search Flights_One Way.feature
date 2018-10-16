Feature: Automation round trip multi-city flight reservation ticket, using the ClearTrip Application.
Background:
    Given User has landed on login page of application
    
 Scenario: Search Flights One-Way
    Then User enters data on input page
    And user enters Travellers details
    And clicks continue
    Then verify page title in verification Page
    